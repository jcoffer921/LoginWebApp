package core;

/**
 * Simple database utility for obtaining JDBC connections to the embedded Derby DB
 * and ensuring required schema objects exist.
 *
 * - Uses an embedded Derby database
 * - Automatically creates the database and tables on first run
 * - Uses a portable path (works on any machine)
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

    /**
     * Portable Derby DB location.
     * Creates LoginWebAppDB inside the user's home directory
     * (works on Windows, macOS, Linux).
     */
    private static final String URL =
            "jdbc:derby:" + System.getProperty("user.home") + "/LoginWebAppDB;create=true";

    static {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load Derby JDBC Driver", e);
        }
    }

    /**
     * Returns a NEW database connection per call (safe for web apps).
     * Ensures required tables exist.
     */
    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(URL);
        ensureSchema(conn);
        return conn;
    }

    /**
     * Creates required tables if they do not exist.
     * Only ignores the Derby "table already exists" error.
     */
    private static void ensureSchema(Connection conn) throws SQLException {

        createTable(conn, """
            CREATE TABLE product (
                product_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                product_name VARCHAR(120) NOT NULL,
                product_description VARCHAR(500),
                product_color VARCHAR(50),
                product_size VARCHAR(20),
                product_price DECIMAL(10,2) NOT NULL
            )
        """);

        createTable(conn, """
            CREATE TABLE customer (
                id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                first_name VARCHAR(100),
                last_name VARCHAR(100),
                favorite_meal VARCHAR(100)
            )
        """);
    }

    /**
     * Executes a CREATE TABLE statement.
     * Ignores Derby SQLState X0Y32 (table already exists).
     * Throws all other SQL errors.
     */
    private static void createTable(Connection conn, String sql) throws SQLException {
        try (Statement st = conn.createStatement()) {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            // Derby SQLState for "Table/View already exists"
            if (!"X0Y32".equals(e.getSQLState())) {
                throw e;
            }
        }
    }
}
