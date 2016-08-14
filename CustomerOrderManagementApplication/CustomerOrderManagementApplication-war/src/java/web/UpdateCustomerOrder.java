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
import javax.ejb.EJB;
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
@WebServlet(name = "UpdateCustomerOrder", urlPatterns = {"/UpdateCustomerOrder"})
public class UpdateCustomerOrder extends HttpServlet {
    @EJB
    private CustomerFacade customerFacade;
    @EJB
    private CustomerOrderFacade customerOrderFacade;

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
            out.println("<title>Servlet UpdateCustomerOrder</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateCustomerOrder at " + request.getContextPath() + "</h1>");
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
        String customerId = request.getParameter("customerIdd");
        String orderId = request.getParameter("orderId");
        String dueDate = request.getParameter("orderDueDate");
        String comment = request.getParameter("orderComment");
        String amount = request.getParameter("orderAmount");
        String message;
        CustomerOrderEntity customerOrder;
        
        if((orderId != null) && (orderId != null) && (dueDate != null) && (comment != null) && (amount != null)){
            customerOrder = customerOrderFacade.find(orderId);
            customerOrder.setCustomer(customerFacade.find(Long.parseLong(customerId)));
            customerOrder.setDueDate(dueDate);
            customerOrder.setComment(comment);
            customerOrder.setAmount(Double.parseDouble(amount));
            customerOrderFacade.edit(customerOrder);
            message = "Customer Order updated successfully!";
            request.getSession().setAttribute("messageSuccess", message);
	    response.sendRedirect("ListCustomerOrder");
        }
        else{
            message = "Failed to update, try filling all fields!";
            request.getSession().setAttribute("messageFailure", message);
	    response.sendRedirect("ListCustomerOrder");
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
