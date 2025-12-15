package com.example.login;

/**
 * Displays and creates customers using {@link entity.CustomerDAO}.
 */

import entity.Customer;
import entity.CustomerDAO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/customers")
public class CustomerServlet extends HttpServlet {

    private CustomerDAO customerDAO;

    /** Initialize the DAO (simple manual wiring). */
    @Override
    public void init() throws ServletException {
        // use your existing DAO
        customerDAO = new CustomerDAO();
    }

    /** Shows the customers page with all customers listed. */
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // Get all customers from the DB via DAO
        List<Customer> customers = null;
        try {
            customers = customerDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("customers", customers);

        // Forward to JSP (file is customer.jsp)
        RequestDispatcher rd = request.getRequestDispatcher("/customer.jsp");
        rd.forward(request, response);
    }

    /** Handles simple customer creation via form POST. */
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // Simple “create” operation
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String favoriteMeal = request.getParameter("favoriteMeal");

        if (firstName != null && lastName != null && favoriteMeal != null
                && !firstName.isBlank() && !lastName.isBlank()) {

            Customer c = new Customer();
            c.setFirstName(firstName);
            c.setLastName(lastName);
            c.setFavoriteMeal(favoriteMeal);

            try {
                customerDAO.create(c);    // uses your existing DAO
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        // Redirect to avoid form resubmission
        response.sendRedirect(request.getContextPath() + "/customers");
    }
}

