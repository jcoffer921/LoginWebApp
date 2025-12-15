package com.example.login;

/**
 * Simple login controller for demo purposes.
 * Accepts hard-coded admin/password and creates a session attribute "user".
 */

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    /** Redirects to the JSP login page when GET /login is requested. */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // If someone hits /login directly, just show the login page
        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }

    /** Handles login form submission and creates a session on success. */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // Simple hard-coded check like in the slide: admin / password
        if ("admin".equals(username) && "password".equals(password)) {
            HttpSession session = req.getSession();
            session.setAttribute("user", username);

            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        } else {
            req.setAttribute("error", "Invalid username or password");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
