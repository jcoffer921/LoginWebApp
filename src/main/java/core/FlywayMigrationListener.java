package core;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.flywaydb.core.Flyway;

/**
 * Applies database migrations on application startup using Flyway 3.2.1.
 * Looks for SQL migrations under classpath:db.migrations
 */
public class FlywayMigrationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Use the same embedded Derby URL as DB.java
        String url = "jdbc:derby:LoginWebAppDB;create=true";

        // Flyway 3.x API usage
        try {
            Flyway flyway = new Flyway();
            flyway.setLocations("classpath:db.migrations");
            // Derby embedded typically doesn't require username/password
            flyway.setDataSource(url, "", "");
            flyway.migrate();
            sce.getServletContext().log("Flyway migrations applied successfully.");
        } catch (Exception e) {
            // Log but do not block app startup
            sce.getServletContext().log("Flyway migration failed: " + e.getMessage(), e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // no-op
    }
}
