/**
 * The {@code ReliefWorkerInterface} class represents an interface for relief workers
 * interacting with a disaster management system. It allows both central and location-based
 * relief workers to perform specific tasks related to disaster response and management.
 * <p>
 * This class provides functionalities such as logging in and authenticating relief workers
 * based on their roles, adding, searching, and managing disaster victims, managing supplies
 * and inventory for disaster response, and maintaining records for individuals involved in
 * disaster situations.
 * <p>
 * The interface utilizes JDBC for database connectivity and is designed to handle
 * interactions with a PostgreSQL database for data storage and retrieval.
 */


package edu.ucalgary.oop;
import java.sql.*;
import java.util.Scanner;
public class ReliefWorkerInterface {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/ensf380project";
    private static final String USER = "oop";
    private static final String PASSWORD = "ucalgary";

    private static Connection connection;

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            try {
                connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                System.out.println("Database connection established successfully.\n");
                System.out.println("Are you entering as a central or location-based relief worker?");
                System.out.print("Enter 'central' or 'location': ");
                String mode = scanner.nextLine();
                if (mode.equalsIgnoreCase("central")) {
                    displayCentralReliefWorkerInterface(scanner);
                } else if (mode.equalsIgnoreCase("location")) {
                    displayLocationBasedReliefWorkerInterface(scanner);
                } else {
                    System.out.println("Invalid mode. Please enter 'central' or 'location'.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    private static void displayCentralReliefWorkerInterface(Scanner scanner) {
        System.out.println("Welcome to the Central Relief Worker Interface");
        System.out.println("=============================================");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        // Perform login authentication

        System.out.println("\nLogged in as Central Relief Worker: " + username);
        System.out.println("=============================================");
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Log Inquirer Query");
            System.out.println("2. Search for DisasterVictims");
            System.out.println("3. Display all the logs related to inquirer id");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    logInquirerQuery(scanner);
                    break;
                case 2:
                    searchDisasterVictims(scanner);
                    break;
                case 3:
                    displayInquiriesForInquirer(scanner);
                    break;
                case 4:
                    System.out.println("Exiting the application...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayLocationBasedReliefWorkerInterface(Scanner scanner) {
        System.out.println("Welcome to the Location-Based Relief Worker Interface");
        System.out.println("=====================================================");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        // Perform login authentication

        System.out.println("\nLogged in as Location-Based Relief Worker: " + username);
        System.out.println("=====================================================");
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Disaster Victim");
            System.out.println("2. Manage Supplies");
            System.out.println("3. Maintain Records for Individuals");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    addDisasterVictim(scanner);
                    break;
                case 2:
                    manageSupplies(scanner);
                    break;
                case 3:
                    maintainRecordsForIndividuals(scanner);
                    break;
                case 4:
                    System.out.println("Exiting the application...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addDisasterVictim(Scanner scanner) {
        System.out.println("Adding a new Disaster Victim...");
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter date of birth (YYYY-MM-DD) or enter 'A' for approximate age: ");
        String dobOrAge = scanner.nextLine();

        if (dobOrAge.equalsIgnoreCase("A")) {
            System.out.print("Enter approximate age: ");
            int approximateAge = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                String sql = "INSERT INTO disaster_victims (first_name, last_name, approximate_age) VALUES (?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, firstName);
                statement.setString(2, lastName);
                statement.setInt(3, approximateAge);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("New Disaster Victim added successfully.");
                } else {
                    System.out.println("Failed to add new Disaster Victim.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                String sql = "INSERT INTO disaster_victims (first_name, last_name, date_of_birth) VALUES (?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, firstName);
                statement.setString(2, lastName);
                statement.setDate(3, Date.valueOf(dobOrAge));

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("New Disaster Victim added successfully.");
                } else {
                    System.out.println("Failed to add new Disaster Victim.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    private static void manageSupplies(Scanner scanner) {
        System.out.println("\nManaging supplies...");
        while (true) {
            System.out.println("\nSupply Management Menu:");
            System.out.println("1. Add New Supply Item");
            System.out.println("2. Update Supply Item Quantity");
            System.out.println("3. View Supplies List");
            System.out.println("4. Exit Supply Management");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addNewSupplyItem(scanner);
                    break;
                case 2:
                    updateSupplyItemQuantity(scanner);
                    break;
                case 3:
                    viewSuppliesList(scanner);
                    break;
                case 4:
                    System.out.println("Exiting supply management...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }



    private static void addNewSupplyItem(Scanner scanner) {
        System.out.println("\nAdding a new supply item...");
        System.out.print("Enter item name: ");
        String itemName = scanner.nextLine();

        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline after reading int

        String sql = "INSERT INTO supplies (item_name, quantity) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, itemName);
            statement.setInt(2, quantity);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("New supply item added successfully.");
            } else {
                System.out.println("Failed to add new supply item.");
            }
        } catch (SQLException e) {
            System.err.println("Error adding new supply item: " + e.getMessage());
        }
    }


    private static void updateSupplyItemQuantity(Scanner scanner) {
        System.out.println("\nUpdating supply item quantity...");
        System.out.print("Enter item name to update: ");
        String itemName = scanner.nextLine();

        System.out.print("Enter new quantity: ");
        int newQuantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline after reading int

        String sql = "UPDATE supplies SET quantity = ? WHERE item_name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, newQuantity);
            statement.setString(2, itemName);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Supply item quantity updated successfully.");
            } else {
                System.out.println("Failed to update supply item quantity. Item not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating supply item quantity: " + e.getMessage());
        }
    }


    private static void viewSuppliesList(Scanner scanner) {
        System.out.println("\nViewing supplies list...");
        String sql = "SELECT * FROM supplies";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                String itemName = resultSet.getString("item_name");
                int quantity = resultSet.getInt("quantity");
                System.out.println(itemName + ": " + quantity);
            }
        } catch (SQLException e) {
            System.err.println("Error viewing supplies list: " + e.getMessage());
        }
    }




    private static void maintainRecordsForIndividuals(Scanner scanner) {
        System.out.println("Maintaining records for individuals...");
        System.out.println("Please enter details for the new individual record:");

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter date of birth (YYYY-MM-DD) or enter 'A' for approximate age: ");
        String dobOrAge = scanner.nextLine();

        if (dobOrAge.equalsIgnoreCase("A")) {
            System.out.print("Enter approximate age: ");
            int approximateAge = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Enter details: ");
            String details = scanner.nextLine();

            try {
                // Insert individual record with approximate age and details into database
                String sql = "INSERT INTO individual_records (first_name, last_name, approximate_age, details) VALUES (?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, firstName);
                statement.setString(2, lastName);
                statement.setInt(3, approximateAge);
                statement.setString(4, details);

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("New record added successfully for " + firstName + " " + lastName);
                } else {
                    System.out.println("Failed to add new record for " + firstName + " " + lastName);
                }
            } catch (SQLException e) {
                System.err.println("Error adding new individual record: " + e.getMessage());
            }
        } else {
            try {
                System.out.print("Enter details: ");
                String details = scanner.nextLine();

                // Insert individual record with date of birth and details into database
                String sql = "INSERT INTO individual_records (first_name, last_name, date_of_birth, details) VALUES (?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, firstName);
                statement.setString(2, lastName);
                statement.setDate(3, Date.valueOf(dobOrAge));
                statement.setString(4, details);

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("New record added successfully for " + firstName + " " + lastName);
                } else {
                    System.out.println("Failed to add new record for " + firstName + " " + lastName);
                }
            } catch (SQLException e) {
                System.err.println("Error adding new individual record: " + e.getMessage());
            }
        }
    }


    public static void logInquirerQuery(Scanner scanner) {
        System.out.println("\nLogging Inquirer Query");

        try {
            // Get input from the user
            System.out.print("Enter inquirer ID: ");
            int inquirerId = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Check if the inquirer exists
            if (!inquirerExists(inquirerId)) {
                // Inquirer does not exist, prompt to create the inquirer
                System.out.println("Inquirer with ID " + inquirerId + " does not exist.");
                System.out.println("Please provide inquirer details to create:");
                System.out.print("Enter first name: ");
                String firstName = scanner.nextLine();
                System.out.print("Enter last name: ");
                String lastName = scanner.nextLine();
                System.out.print("Enter phone number: ");
                String phoneNumber = scanner.nextLine();

                // Create the inquirer
                createInquirer(inquirerId, firstName, lastName, phoneNumber);
            }

            // Proceed to log the inquiry query
            int queryLogId = getNextInquiryLogId(); // Get next available query log ID
            System.out.print("Enter call date (YYYY-MM-DD): ");
            String callDateStr = scanner.nextLine();
            Date callDate = Date.valueOf(callDateStr); // Convert String to java.sql.Date

            System.out.print("Enter query details: ");
            String details = scanner.nextLine();

            // Prepare SQL statement to insert into INQUIRY_LOG table
            String sql = "INSERT INTO INQUIRY_LOG (id, inquirer, callDate, details) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, queryLogId);
            statement.setInt(2, inquirerId);
            statement.setDate(3, callDate);
            statement.setString(4, details);

            // Execute the SQL INSERT statement
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Inquirer query logged successfully.");
            } else {
                System.out.println("Failed to log inquirer query.");
            }

        } catch (SQLException e) {
            System.out.println("Error logging inquirer query: " + e.getMessage());
        }
    }

    private static boolean inquirerExists(int inquirerId) throws SQLException {
        String sql = "SELECT id FROM INQUIRER WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, inquirerId);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next(); // Return true if inquirer exists
    }

    private static void createInquirer(int inquirerId, String firstName, String lastName, String phoneNumber) throws SQLException {
        String sql = "INSERT INTO INQUIRER (id, firstName, lastName, phoneNumber) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, inquirerId);
        statement.setString(2, firstName);
        statement.setString(3, lastName);
        statement.setString(4, phoneNumber);
        int rowsAffected = statement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Inquirer created successfully.");
        } else {
            System.out.println("Failed to create inquirer.");
        }
    }

    private static int getNextInquiryLogId() throws SQLException {
        String sql = "SELECT COALESCE(MAX(id), 0) + 1 AS next_id FROM INQUIRY_LOG";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("next_id");
        }
        return 1; // Default to 1 if no records exist (unlikely in a properly set up table)
    }



    private static void searchDisasterVictims(Scanner scanner) {
        System.out.println("\nSearching for DisasterVictims");
        System.out.print("Enter part of the DisasterVictim's name: ");
        String partialName = scanner.nextLine().toLowerCase();

        try {
            String sql = "SELECT * FROM disaster_victims WHERE LOWER(first_name) LIKE ? OR LOWER(last_name) LIKE ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + partialName + "%");
            statement.setString(2, "%" + partialName + "%");
            ResultSet resultSet = statement.executeQuery();

            int count = 0;
            while (resultSet.next()) {
                count++;
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                System.out.println(count + ". " + firstName + " " + lastName);
                // Additional details or formatting can be added here
            }

            if (count == 0) {
                System.out.println("No matching DisasterVictims found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static void displayInquiriesForInquirer(Scanner scanner) {
        System.out.println("\nEnter the Inquirer ID to display inquiries:");
        int inquirerId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        try {
            String sql = "SELECT i.callDate, i.details, inq.firstName, inq.lastName " +
                    "FROM inquiry_log i " +
                    "INNER JOIN inquirer inq ON i.inquirer = inq.id " +
                    "WHERE inq.id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, inquirerId);
            ResultSet resultSet = statement.executeQuery();

            System.out.println("\nInquiries for Inquirer ID: " + inquirerId);
            System.out.println("=================================================");

            boolean foundInquiries = false;

            while (resultSet.next()) {
                String callDate = resultSet.getString("callDate");
                String details = resultSet.getString("details");
                String inquirerFirstName = resultSet.getString("firstName");
                String inquirerLastName = resultSet.getString("lastName");

                System.out.println("Inquiry Date: " + callDate);
                System.out.println("Inquirer Name: " + inquirerFirstName + " " + inquirerLastName);
                System.out.println("Details: " + details);
                System.out.println("---------------------------------------------");

                foundInquiries = true; // Set flag to true since at least one inquiry was found
            }

            if (!foundInquiries) {
                System.out.println("No inquiries found for the inquirer ID: " + inquirerId);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}