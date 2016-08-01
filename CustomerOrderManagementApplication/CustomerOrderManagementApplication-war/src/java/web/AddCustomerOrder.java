/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.CustomerOrder;
import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.jms.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Arunalu Hasakelum
 */
@WebServlet(name = "AddCustomerOrder", urlPatterns = {"/AddCustomerOrder"})
public class AddCustomerOrder extends HttpServlet {
    
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
        String dueDate = request.getParameter("dueDate");
        String comment = request.getParameter("comment");
        String amount = request.getParameter("amount");
        String statusMessage;
        
        if((dueDate != null) && (comment != null) && (amount != null)){
            try {
                Connection connection = connectionFactory.createConnection();
                Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
                        
                MessageProducer messageProducer = session.createProducer(queue);

                ObjectMessage message = session.createObjectMessage();
                // here we create NewsEntity, that will be sent in JMS message
                CustomerOrder customerOrder = new CustomerOrder();
                customerOrder.setDueDate(dueDate);
                customerOrder.setComment(comment);
                customerOrder.setAmount(Double.parseDouble(amount));

                message.setObject(customerOrder);
                messageProducer.send(message);
                messageProducer.close();
                connection.close();
                //response.sendRedirect("ListNews");
                statusMessage = "Order added succesfully";

            } catch (JMSException ex) {
                ex.printStackTrace();
            }
            finally{
                //request.setAttribute("message", statusMessage);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/editCustomerOrder.jsp");
                rd.forward(request, response);
            }
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
