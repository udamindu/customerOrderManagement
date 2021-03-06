/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.CustomerEntity;
import ejb.CustomerFacade;
import ejb.CustomerOrderEntity;
import ejb.CustomerOrderFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "RemoveCustomer", urlPatterns = {"/RemoveCustomer"})
public class RemoveCustomer extends HttpServlet {
    @EJB
    private CustomerOrderFacade customerOrderFacade;
    @EJB
    private CustomerFacade customerFacade;

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
            out.println("<title>Servlet RemoveCustomer</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RemoveCustomer at " + request.getContextPath() + "</h1>");
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
        String customerId = request.getParameter("id");
        String message;
        CustomerEntity customer;
        boolean state = false;
        
        if(customerId != null){ 
            
            List<CustomerOrderEntity> customerOrderList = customerOrderFacade.findAll();
            for (CustomerOrderEntity customerOrderEntity : customerOrderList) {
                if(customerId.equalsIgnoreCase(customerOrderEntity.getCustomer().getId()+"")){
                    state = true;
                }
            }
            if(state){
                message = "Cannot remove the Customer. Orders exist for the Customer";
                request.getSession().setAttribute("messageFailure", message);
            }
            else{
                customer = customerFacade.find(Long.parseLong(customerId));
                customerFacade.remove(customer);
                message = "Customer removed successfully!";
                request.getSession().setAttribute("messageSuccess", message);             
            }
            response.sendRedirect("ListCustomers");
        }
        else{
            message = "Failed to remove the customer";
            request.getSession().setAttribute("messageFailure", message);
	    response.sendRedirect("ListCustomers");
        }
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
