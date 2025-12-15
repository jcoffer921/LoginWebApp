package entity;

import core.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) for {@link Customer} entities.
 */
public class CustomerDAO {

    /**
     * Retrieves all customers ordered by id.
     *
     * @return list of customers (possibly empty)
     * @throws SQLException if a database access error occurs
     */
    public List<Customer> findAll() throws SQLException {
        List<Customer> customers = new ArrayList<>();

        String sql = "SELECT * FROM customer ORDER BY id";

        try (Connection conn = DB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Customer c = new Customer(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("favorite_meal")
                );
                customers.add(c);
            }
        }
        return customers;
    }

    /**
     * Inserts a new customer.
     *
     * @param c customer to create (without id)
     * @throws SQLException if a database access error occurs
     */
    public void create(Customer c) throws SQLException {
        String sql = "INSERT INTO customer (first_name, last_name, favorite_meal) " +
                "VALUES (?, ?, ?)";

        try (Connection conn = DB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getFirstName());
            ps.setString(2, c.getLastName());
            ps.setString(3, c.getFavoriteMeal());

            ps.executeUpdate();
        }
    }

}
