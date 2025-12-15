package core;

/**
 * Simple database utility for obtaining JDBC connections to the embedded Derby DB
 * and ensuring required schema objects exist. Returns a NEW connection per call.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

    // Use an absolute path so GlassFish always hits the SAME database
    private static final String URL =
            "jdbc:derby:C:/Users/jcoff/derby/LoginWebAppDB;create=true";

    static {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load Derby JDBC Driver", e);
        }
    }

    /**
     * Returns a NEW connection each call (safe for web apps).
     * Ensures the required tables exist.
     */
    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(URL);
        ensureSchema(conn);
        return conn;
    }

    /**
     * Creates required tables if they don't exist.
     * Safe to call repeatedly because we ignore "already exists" errors.
     */
    private static void ensureSchema(Connection conn) throws SQLException {

        // PRODUCT table required by assignment
        try (Statement st = conn.createStatement()) {
            st.executeUpdate("""
                CREATE TABLE product (
                    product_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                    product_name VARCHAR(120) NOT NULL,
                    product_description VARCHAR(500),
                    product_color VARCHAR(50),
                    product_size VARCHAR(20),
                    product_price DECIMAL(10,2) NOT NULL
                )
            """);
        } catch (SQLException ignore) {
            // table already exists -> ignore
        }

        // CUSTOMER table (since you already use it)
        try (Statement st = conn.createStatement()) {
            st.executeUpdate("""
                CREATE TABLE customer (
                    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                    first_name VARCHAR(100),
                    last_name VARCHAR(100),
                    favorite_meal VARCHAR(100)
                )
            """);
        } catch (SQLException ignore) {
            // table already exists -> ignore
        }
    }
}
