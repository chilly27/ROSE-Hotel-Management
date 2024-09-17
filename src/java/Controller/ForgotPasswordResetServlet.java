/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dal.CustomerDB;
import Model.Customer;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;
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
@WebServlet(name = "ForgotPasswordResetServlet", urlPatterns = {"/forgot-password-reset"})
public class ForgotPasswordResetServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        if (session.getAttribute("forgotConfirm") == null) {
            response.sendRedirect("/forgot-password");
            return;
        }
        request.getRequestDispatcher("forgotPasswordReset.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        if (session.getAttribute("forgotConfirm") == null) {
            response.sendRedirect("/forgot-password");
        }
        String get_password = request.getParameter("password");
        String get_confirmPassword = request.getParameter("confirmPassword");
        String passwordPattern = "^(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$";

        if (!Pattern.matches(passwordPattern, get_password)) {
            request.setAttribute("error", "Password must be at least 8 characters long, contain at least one uppercase letter, one special character, and must not contain the username.");
            request.getRequestDispatcher("forgotPasswordReset.jsp").forward(request, response);
            return;
        }
        if (!get_password.equals(get_confirmPassword)) {
            request.setAttribute("error", "Password and confirm password not match");
            request.getRequestDispatcher("forgotPasswordReset.jsp").forward(request, response);
            return;
        }
        CustomerDB customerDB = new CustomerDB();
        Customer customer = new Customer();
        customer.setEmail((String) session.getAttribute("emailForgot"));
        String hash = BCrypt.hashpw(get_password, BCrypt.gensalt());
        customer.setPassword(hash);
        if (customerDB.updatePassword(customer.getEmail(), customer.getPassword())) {
            session.setAttribute("forgotConfirm", null);
            session.setAttribute("emailForgot", null);
            response.sendRedirect("/login");
        } else {
            request.setAttribute("error", "Failed to update password");
            request.getRequestDispatcher("forgotPasswordReset.jsp").forward(request, response);
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
