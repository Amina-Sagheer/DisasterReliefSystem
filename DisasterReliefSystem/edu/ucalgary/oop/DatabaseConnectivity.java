/**
 * The `DatabaseConnectivity` class provides methods for establishing and testing connectivity
 * with a PostgreSQL database.
 * Requirements met by this class:
 * 1. Database Connection: The `getConnection` method establishes a connection to the specified database
 *    using JDBC (Java Database Connectivity).
 * 2. Connection Testing: The `testConnection` method attempts to connect to the database and prints
 *    a success message if the connection is established, or an error message if the connection fails.
 * 3. Main Method: The `main` method in this class tests the database connection by calling `testConnection`
 *    and prints the appropriate message based on the result.
 * This class encapsulates database connectivity functionality and demonstrates how to use JDBC
 * to interact with a PostgreSQL database.
 */

package edu.ucalgary.oop;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectivity {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/ensf380project";
    private static final String USER = "oop";
    private static final String PASSWORD = "ucalgary";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
    public static boolean testConnection() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            System.out.println("Database connection successful.");
            return true;
        } catch (SQLException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        // Test the database connection
        if (testConnection()) {
            System.out.println("Database connection is active.");
        } else {
            System.out.println("Database connection failed.");
        }
    }

}

