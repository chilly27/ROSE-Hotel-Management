/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dal.CustomerDB;
import Model.Customer;
import Utils.Permission;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ChangeAvatarServlet", urlPatterns = {"/change-avatar"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class ChangeAvatarServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "uploads";

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
            response.sendRedirect("login");
            return;
        }
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("account");
        Part filePart = request.getPart("avatarFile");
        String fileName = extractFileName(filePart);
        String avatarPath = customer.getAvatar();

        if (fileName != null && !fileName.isEmpty()) {
            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR + File.separator + "avatar";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String fileNameRandom = String.valueOf(System.currentTimeMillis());
            fileName = fileNameRandom + customer.getCustomerID() + fileName;
            String filePath = uploadPath + File.separator + fileName;
            filePart.write(filePath);
            avatarPath = UPLOAD_DIR + "/avatar/" + fileName;
        }

        customer.setAvatar(avatarPath);
        CustomerDB customerDB = new CustomerDB();
        if (customerDB.updateAvatar(customer)) {
            System.out.println(customer.getCustomerID());
            session.setAttribute("customer", customer);
            response.sendRedirect("profile");
        } else {
            response.sendRedirect("profile?error=1");
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

    private String extractFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        for (String cd : contentDisposition.split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
