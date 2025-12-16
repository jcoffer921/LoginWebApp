package com.example.login;

/**
 * Servlet that shows and processes the product edit form.
 *
 * GET  /products/update?id=123  -> loads product and forwards to product_update.jsp
 * POST /products/update         -> applies updates and redirects back to products.jsp
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

    private final ProductDAO productsDAO = new ProductDAO();

    /** Checks admin login status. */
    private boolean isLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null && session.getAttribute("adminUser") != null;
    }

    /**
     * Loads the product by id and forwards to the update form.
     * If id is missing/invalid or product not found, redirects to products.jsp.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!isLoggedIn(request)) {
            response.sendRedirect(request.getContextPath() + "/admin/login");
            return;
        }

        // Validate query param
        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isBlank()) {
            response.sendRedirect(request.getContextPath() + "/products.jsp");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException ex) {
            response.sendRedirect(request.getContextPath() + "/products.jsp");
            return;
        }

        try {
            Product product = productsDAO.findById(id);

            // Prevent forwarding with a null product -> avoids JSP NPE
            if (product == null) {
                response.sendRedirect(request.getContextPath() + "/products.jsp");
                return;
            }

            request.setAttribute("product", product);
            request.getRequestDispatcher("/product_update.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    /**
     * Applies updates submitted from the form.
     * Redirects back to products.jsp after success.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!isLoggedIn(request)) {
            response.sendRedirect(request.getContextPath() + "/admin/login");
            return;
        }

        // Validate required params
        String idParam = request.getParameter("product_id");
        String priceParam = request.getParameter("product_price");

        if (idParam == null || idParam.isBlank() || priceParam == null || priceParam.isBlank()) {
            response.sendRedirect(request.getContextPath() + "/products.jsp");
            return;
        }

        int id;
        double price;

        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException ex) {
            response.sendRedirect(request.getContextPath() + "/products.jsp");
            return;
        }

        try {
            price = Double.parseDouble(priceParam);
        } catch (NumberFormatException ex) {
            response.sendRedirect(request.getContextPath() + "/products.jsp");
            return;
        }

        String name = request.getParameter("product_name");
        String description = request.getParameter("product_description");
        String color = request.getParameter("product_color");
        String size = request.getParameter("product_size");

        // Name is required in your JSP; still guard just in case
        if (name == null || name.isBlank()) {
            response.sendRedirect(request.getContextPath() + "/products.jsp");
            return;
        }

        Product product = new Product(id, name, description, color, size, price);

        try {
            productsDAO.update(product);
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        // You asked to return to products.jsp
        response.sendRedirect(request.getContextPath() + "/products.jsp");
    }
}
