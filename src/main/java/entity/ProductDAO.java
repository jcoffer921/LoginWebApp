package entity;

import core.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) for {@link Product} entities.
 * <p>
 * Provides CRUD operations against the {@code product} table using
 * JDBC with try-with-resources for proper resource management.
 */
public class ProductDAO {

    /**
     * Inserts a new product row into the database.
     *
     * @param p product to create (without id)
     * @throws SQLException if a database access error occurs
     */
    public void create(Product p) throws SQLException {
        String sql = "INSERT INTO product " +
                "(product_name, product_description, product_color, product_size, product_price) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getProductName());
            ps.setString(2, p.getProductDescription());
            ps.setString(3, p.getProductColor());
            ps.setString(4, p.getProductSize());
            ps.setDouble(5, p.getProductPrice());

            ps.executeUpdate();
        }
    }

    /**
     * Reads all products ordered by id.
     *
     * @return list of products (possibly empty)
     * @throws SQLException if a database access error occurs
     */
    public List<Product> findAll() throws SQLException {
        List<Product> list = new ArrayList<>();

        String sql = "SELECT * FROM product ORDER BY product_id";

        try (Connection conn = DB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }
        }
        return list;
    }

    /**
     * Finds a single product by its id.
     *
     * @param id product_id
     * @return the product if found, otherwise {@code null}
     * @throws SQLException if a database access error occurs
     */
    public Product findById(int id) throws SQLException {
        String sql = "SELECT * FROM product WHERE product_id = ?";

        try (Connection conn = DB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    /**
     * Updates an existing product by id.
     *
     * @param product product with updated fields (must contain id)
     * @throws SQLException if a database access error occurs
     */
    public void update(Product product) throws SQLException {
        String sql = "UPDATE product SET " +
                "product_name = ?, " +
                "product_description = ?, " +
                "product_color = ?, " +
                "product_size = ?, " +
                "product_price = ? " +
                "WHERE product_id = ?";

        try (Connection conn = DB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, product.getProductName());
            ps.setString(2, product.getProductDescription());
            ps.setString(3, product.getProductColor());
            ps.setString(4, product.getProductSize());
            ps.setDouble(5, product.getProductPrice());
            ps.setInt(6, product.getProductId());

            ps.executeUpdate();
        }
    }

    /**
     * Deletes a product by id.
     *
     * @param id product_id to delete
     * @throws SQLException if a database access error occurs
     */
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM product WHERE product_id = ?";

        try (Connection conn = DB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    /**
     * Maps the current {@link ResultSet} row to a {@link Product}.
     */
    private Product mapRow(ResultSet rs) throws SQLException {
        return new Product(
                rs.getInt("product_id"),
                rs.getString("product_name"),
                rs.getString("product_description"),
                rs.getString("product_color"),
                rs.getString("product_size"),
                rs.getDouble("product_price")
        );
    }
}
