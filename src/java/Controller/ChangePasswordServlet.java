/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dal.CustomerDB;
import Model.Customer;
import Utils.Permission;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ChangePasswordServlet", urlPatterns = {"/change-password"})
public class ChangePasswordServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!Permission.checkLogin(request, response)) {
            response.sendRedirect("/login");
            return;
        }
        request.getRequestDispatcher("changePassword.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!Permission.checkLogin(request, response)) {
            response.sendRedirect("/login");
            return;
        }
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        // Check if old password is correct
        if (!BCrypt.checkpw(oldPassword, customer.getPassword())) {
            request.setAttribute("error", "Old password is incorrect");
            request.getRequestDispatcher("changePassword.jsp").forward(request, response);
            return;
        }

        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
        if (!newPassword.matches(passwordPattern)) {
            request.setAttribute("error", "Password must contain at least 8 characters, including UPPER/lowercase and numbers");
            request.getRequestDispatcher("changePassword.jsp").forward(request, response);
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "New password and confirm password not match");
            request.getRequestDispatcher("changePassword.jsp").forward(request, response);
            return;
        }

        CustomerDB customerDB = new CustomerDB();
        if (customerDB.updatePassword(customer.getEmail(), newPassword)) {
            request.setAttribute("success", "Password updated successfully, please login again");
            session.invalidate();
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "An error occurred while updating password");
            request.getRequestDispatcher("changePassword.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
