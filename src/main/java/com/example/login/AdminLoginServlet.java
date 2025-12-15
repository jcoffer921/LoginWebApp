package com.example.login;

/**
 * Admin login controller protecting product management pages.
 * Stores "adminUser" in the session on successful authentication.
 */

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/admin/login")
public class AdminLoginServlet extends HttpServlet {

    private static final String ADMIN_USER = "admin";
    private static final String ADMIN_PASS = "password"; // change if needed

    /** Shows the admin login form. */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/admin_login.jsp").forward(request, response);
    }

    /** Processes admin login and redirects to the products list on success. */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (ADMIN_USER.equals(username) && ADMIN_PASS.equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("adminUser", username);

            response.sendRedirect(request.getContextPath() + "/products/read");
        } else {
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("/admin_login.jsp").forward(request, response);
        }
    }
}
