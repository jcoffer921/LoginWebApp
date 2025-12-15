package com.example.login;

/**
 * Servlet that confirms and deletes a product by id.
 */

import entity.ProductDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/products/delete")
public class ProductDeleteServlet extends HttpServlet {

    private ProductDAO productsDAO = new ProductDAO();

    /** Checks admin login status. */
    private boolean isLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null && session.getAttribute("adminUser") != null;
    }

    // Show confirmation page
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!isLoggedIn(request)) {
            response.sendRedirect(request.getContextPath() + "/admin/login");
            return;
        }

        String idStr = request.getParameter("id");
        request.setAttribute("productId", idStr);
        request.getRequestDispatcher("/product_delete.jsp").forward(request, response);
    }

    // Perform delete via POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!isLoggedIn(request)) {
            response.sendRedirect(request.getContextPath() + "/admin/login");
            return;
        }

        int id = Integer.parseInt(request.getParameter("product_id"));

        try {
            productsDAO.delete(id);
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        response.sendRedirect(request.getContextPath() + "/products/read");
    }
}
