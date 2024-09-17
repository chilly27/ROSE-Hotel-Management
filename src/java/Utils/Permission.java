package Utils;

import Model.Customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

public class Permission {

    public static boolean checkAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("account") != null) {
            Customer customer = (Customer) session.getAttribute("account");
            if(!Objects.equals(customer.getRole(), "admin")) {
                response.sendRedirect("/");
                return false;
            }
        } else {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }

    public static boolean checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        return session.getAttribute("account") != null;
    }

    public static boolean checkEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("account") != null) {
            Customer customer = (Customer) session.getAttribute("account");
            if(!Objects.equals(customer.getRole(), "employee")) {
                response.sendRedirect("/");
                return false;
            }
        } else {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }

    public static boolean checkManager(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("account") != null) {
            Customer customer = (Customer) session.getAttribute("account");
            if(!Objects.equals(customer.getRole(), "admin") && !Objects.equals(customer.getRole(), "employee")) {
                response.sendRedirect("/");
                return false;
            }
        } else {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}