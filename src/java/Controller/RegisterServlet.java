/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dal.CustomerDB;
import Model.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class RegisterServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegisterServet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterServet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       request.getRequestDispatcher("register.jsp").forward(request, response);
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
      String get_userName = request.getParameter("username");
    String get_email = request.getParameter("email");
    String get_password = request.getParameter("password");
    String get_repeat = request.getParameter("confirm-password");
    String get_dob = request.getParameter("dob");
    String get_sex = request.getParameter("gender");
    String get_phone = request.getParameter("phone");
    

    // Password validation regex: at least 8 characters, one uppercase letter, one special character
    String passwordPattern = "^(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$";
    
    // Check if password and confirm password match
    if (!get_password.equals(get_repeat)) {
        request.setAttribute("error", "Please make sure your passwords match");
        request.getRequestDispatcher("register.jsp").forward(request, response);
        return;
    }

    // Check password complexity
    if (!Pattern.matches(passwordPattern, get_password) || get_password.contains(get_userName)) {
        request.setAttribute("error", "Password must be at least 8 characters long, contain at least one uppercase letter, one special character, and must not contain the username.");
        request.getRequestDispatcher("register.jsp").forward(request, response);
        return;
    }

    // Check phone number length
    if (get_phone.length() != 10) {
        request.setAttribute("error", "Phone number must be exactly 10 digits.");
        request.getRequestDispatcher("register.jsp").forward(request, response);
        return;
    }

    try {
        Date dob = Date.valueOf(get_dob);
        
        // Calculate age
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dob);
        int year = calendar.get(Calendar.YEAR);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int age = currentYear - year;

        // Check if age is at least 14
        if (age < 14) {
            request.setAttribute("error", "You must be at least 14 years old to register.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        char gender = get_sex.charAt(0);

        CustomerDB customerDB = new CustomerDB();

        if (customerDB.checkUserNameExist(get_userName) != null) {
            request.setAttribute("exist", "Username already exists");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else if (customerDB.checkEmailExist(get_email) != null) {
            request.setAttribute("exist", "Email already exists");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            Customer customer = new Customer(get_email, get_userName, "uploads/avatar/default.png",get_password, get_phone, dob,"customer", gender);
            SendEmail sendEmail = new SendEmail();
            String otp = sendEmail.getRandom();

            if (sendEmail.sendEmail(customer, otp)) {
                HttpSession session = request.getSession();
                customerDB.register(customer.getEmail(), customer.getUserName(), customer.getPassword(), customer.getCusPhone(), customer.getDob(), customer.getGender());
                session.setAttribute("generatedOTP", otp);
                session.setAttribute("customer", customer);
                response.sendRedirect("VerifyOtp.jsp");
            } else {
                request.setAttribute("error", "Failed to send OTP. Try again.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        request.setAttribute("error", "An error occurred: " + e.getMessage());
        request.getRequestDispatcher("register.jsp").forward(request, response);
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