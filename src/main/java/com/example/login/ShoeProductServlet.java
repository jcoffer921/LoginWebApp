package com.example.login;

/**
 * Public-facing products servlet: lists products and handles simple adds.
 */

import entity.Product;
import entity.ProductDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/products")
public class ShoeProductServlet extends HttpServlet {

    private ProductDAO productDAO = new ProductDAO();

    /** Shows the list of products. */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            List<Product> products = productDAO.findAll();
            req.setAttribute("products", products);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        req.getRequestDispatcher("/products.jsp").forward(req, resp);
    }

    /** Handles basic product creation via POST and redirects back to list. */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name  = req.getParameter("productName");
        String desc  = req.getParameter("productDescription");
        String color = req.getParameter("productColor");
        String size  = req.getParameter("productSize");
        double price = Double.parseDouble(req.getParameter("productPrice"));

        Product p = new Product(name, desc, color, size, price);

        try {
            productDAO.create(p);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Redirect to avoid form resubmission
        resp.sendRedirect(req.getContextPath() + "/products");
    }
}
