/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.CustomerFacade;
import ejb.CustomerOrderEntity;
import ejb.CustomerOrderFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author Arunalu Hasakelum
 */
@WebServlet(name = "AddCustomerOrder", urlPatterns = {"/AddCustomerOrder"})
public class AddCustomerOrder extends HttpServlet {
    @EJB
    private CustomerOrderFacade customerOrderFacade;
    
    @EJB
    private CustomerFacade customerFacade;  
    
    @Resource(mappedName="jms/NewCustomerOrderFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName="jms/NewCustomerOrder")
    private Queue queue;
    
    
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /*
             * TODO output your page here. You may use following sample code.
             */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddCustomerOrder</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddCustomerOrder at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        String customerId = request.getParameter("customerId");
        String orderId = request.getParameter("orderId");
        String dueDate = request.getParameter("dueDate");
        String comment = request.getParameter("comment");
        String amount = request.getParameter("amount");
        String statusMessage;
        
        if((orderId != null) && (dueDate != null) && (comment != null) && (amount != null)){
            try {                
                Connection connection = connectionFactory.createConnection();
                Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
                        
                MessageProducer messageProducer = session.createProducer(queue);

                ObjectMessage message = session.createObjectMessage();
                // here we create CustomerOrderEntity entity, that will be sent in JMS message
                CustomerOrderEntity customerOrder = new CustomerOrderEntity();
                customerOrder.setDueDate(dueDate);
                customerOrder.setComment(comment);
                customerOrder.setAmount(Double.parseDouble(amount));
                customerOrder.setCustomer(customerFacade.find(Long.parseLong(customerId)));
                
                ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
                Validator validator = factory.getValidator();
                Set<ConstraintViolation<CustomerOrderEntity>> constraints = validator.validate(customerOrder);
                String errors = "";
                for (ConstraintViolation<CustomerOrderEntity> constraint : constraints) {
                        errors += constraint.getMessage();
                }
                
                if(errors.isEmpty()) {
                    message.setObject(customerOrder);
                    messageProducer.send(message);
                    messageProducer.close();
                    connection.close();
                    statusMessage = "Order will be added to the database";
                    request.setAttribute("message", statusMessage);
                }else{              
                    statusMessage = "Errors detected, Try a different Order Id";
                    request.setAttribute("message", statusMessage);
                }           
                
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/JSP/listCustomerOrders.jsp");
                rd.forward(request, response);

            } catch (JMSException ex) {
                ex.printStackTrace();
            }
        }
        else {
            statusMessage = "Adding failed, try filling all fields!";
            request.setAttribute("message", statusMessage);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/JSP/listCustomerOrders.jsp");
            rd.forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
