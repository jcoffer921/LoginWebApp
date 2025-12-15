package com.example.login;

/**
 * Servlet that lists all products for admins.
 */

import entity.Product;
import entity.ProductDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/products/read")
public class ProductReadServlet extends HttpServlet {

    private ProductDAO productsDAO = new ProductDAO();

    /** Checks admin login status. */
    private boolean isLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null && session.getAttribute("adminUser") != null;
    }

    /**
     * Fetches products and forwards to the JSP for rendering.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!isLoggedIn(request)) {
            response.sendRedirect(request.getContextPath() + "/admin/login");
            return;
        }

        try {
            List<Product> products = productsDAO.findAll();
            request.setAttribute("products", products);
            request.getRequestDispatcher("/product_read.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
