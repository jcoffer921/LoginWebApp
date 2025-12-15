package com.example.login;

/**
 * Servlet that shows and processes the product edit form.
 */

import entity.Product;
import entity.ProductDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/products/update")
public class ProductUpdateServlet extends HttpServlet {

    private ProductDAO productsDAO = new ProductDAO();

    /** Checks admin login status. */
    private boolean isLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null && session.getAttribute("adminUser") != null;
    }

    /**
     * Loads the product by id and forwards to the update form.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!isLoggedIn(request)) {
            response.sendRedirect(request.getContextPath() + "/admin/login");
            return;
        }

        int id = Integer.parseInt(request.getParameter("id"));

        try {
            Product product = productsDAO.findById(id);
            request.setAttribute("product", product);
            request.getRequestDispatcher("/product_update.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    /**
     * Applies updates submitted from the form.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!isLoggedIn(request)) {
            response.sendRedirect(request.getContextPath() + "/admin/login");
            return;
        }

        int id = Integer.parseInt(request.getParameter("product_id"));
        String name = request.getParameter("product_name");
        String description = request.getParameter("product_description");
        String color = request.getParameter("product_color");
        String size = request.getParameter("product_size");
        double price = Double.parseDouble(request.getParameter("product_price"));

        Product product = new Product(id, name, description, color, size, price);

        try {
            productsDAO.update(product);
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        response.sendRedirect(request.getContextPath() + "/products/read");
    }
}
