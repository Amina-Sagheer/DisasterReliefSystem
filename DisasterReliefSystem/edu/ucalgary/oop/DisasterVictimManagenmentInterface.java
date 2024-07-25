/**
 * The {@code DisasterVictimManagenmentInterface} class represents an interface for managing
 * disaster victims and related information within a disaster management system.
 * <p>
 * This class enables users to perform various operations related to disaster victims,
 * including adding new victims, managing relationships between victims, adding medical records,
 * searching for and viewing detailed information about specific victims stored in a PostgreSQL database.
 * <p>
 * The interface provides a menu-driven system that allows users to interactively select
 * different operations to perform on disaster victims and their associated data.
 * <p>
 * This class utilizes JDBC for database connectivity and leverages SQL queries to interact
 * with the underlying PostgreSQL database containing information about disaster victims,
 * relationships, and medical records.
 */


package edu.ucalgary.oop;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.sql.Date;

public class DisasterVictimManagenmentInterface {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/ensf380project";
    private static final String USER = "oop";
    private static final String PASSWORD = "ucalgary";

    private static Connection connection;

    public static void main(String[] args) {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            displayMainMenu();
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

    private static void displayMainMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Main Menu");
            System.out.println("1. Add New DisasterVictim");
            System.out.println("2. Manage Relationships");
            System.out.println("3. Add Medical Records");
            System.out.println("4. Search and View Information");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addNewDisasterVictim(scanner);
                    break;
                case 2:
                    manageRelationships(scanner);
                    break;
                case 3:
                    addMedicalRecords(scanner);
                    break;
                case 4:
                    searchAndViewInformation(scanner);
                    break;
                case 5:
                    System.out.println("Exiting the application...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addNewDisasterVictim(Scanner scanner) {
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

    private static void addMedicalRecords(Scanner scanner) {
        System.out.println("Adding Medical Records");

        // Ask for information related to medical records
        System.out.print("Enter First Name of DisasterVictim: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name of DisasterVictim: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter Treatment Details: ");
        String treatmentDetails = scanner.nextLine();
        System.out.print("Enter Date of Treatment (YYYY-MM-DD): ");
        String dateOfTreatmentStr = scanner.nextLine();

        try {
            // Convert date string to java.util.Date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date dateOfTreatmentUtil = dateFormat.parse(dateOfTreatmentStr);

            // Convert java.util.Date to java.sql.Date
            java.sql.Date dateOfTreatment = new java.sql.Date(dateOfTreatmentUtil.getTime());

            // Get the DisasterVictim's ID based on the entered first name and last name
            int disasterVictimId = getDisasterVictimId(firstName, lastName);

            // Insert the medical record into the database
            String sql = "INSERT INTO medical_records (disaster_victim_id, treatment_details, date_of_treatment) " +
                    "VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, disasterVictimId);
            statement.setString(2, treatmentDetails);
            statement.setDate(3, dateOfTreatment);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Medical Record added successfully.");
            }
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }
    }



    // Helper method to get DisasterVictim's ID based on first name and last name
    private static int getDisasterVictimId(String firstName, String lastName) throws SQLException {
        int disasterVictimId = -1;
        String sql = "SELECT id FROM disaster_victims WHERE first_name = ? AND last_name = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, firstName);
        statement.setString(2, lastName);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            disasterVictimId = resultSet.getInt("id");
        }
        return disasterVictimId;
    }
    private static void manageRelationships(Scanner scanner) {
        System.out.println("Manage Relationships");
        System.out.println("1. Add Relationship");
        System.out.println("2. Remove Relationship");
        System.out.println("3. Back to Main Menu");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                addRelationship(scanner);
                break;
            case 2:
                removeRelationship(scanner);
                break;
            case 3:
                System.out.println("Returning to the main menu...");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void addRelationship(Scanner scanner) {
        System.out.println("Adding Relationship");

        System.out.print("Enter the first DisasterVictim's first name: ");
        String firstName1 = scanner.nextLine();
        System.out.print("Enter the first DisasterVictim's last name: ");
        String lastName1 = scanner.nextLine();

        System.out.print("Enter the second DisasterVictim's first name: ");
        String firstName2 = scanner.nextLine();
        System.out.print("Enter the second DisasterVictim's last name: ");
        String lastName2 = scanner.nextLine();

        System.out.print("Enter the relationship type: ");
        String relationshipType = scanner.nextLine();

        // Check if both disaster victims exist in the database
        boolean found1 = doesDisasterVictimExist(firstName1, lastName1);
        boolean found2 = doesDisasterVictimExist(firstName2, lastName2);

        if (!found1 || !found2) {
            System.out.println("One or both DisasterVictims do not exist.");
            return;
        }

        // Insert the relationship into the database
        try {
            String sql = "INSERT INTO family_relations (person_one_first_name, person_one_last_name, person_two_first_name, person_two_last_name, relationship_type) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, firstName1);
            statement.setString(2, lastName1);
            statement.setString(3, firstName2);
            statement.setString(4, lastName2);
            statement.setString(5, relationshipType);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Relationship added successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void removeRelationship(Scanner scanner) {
        System.out.println("Removing Relationship");

        System.out.print("Enter the first DisasterVictim's first name: ");
        String firstName1 = scanner.nextLine();
        System.out.print("Enter the first DisasterVictim's last name: ");
        String lastName1 = scanner.nextLine();

        System.out.print("Enter the second DisasterVictim's first name: ");
        String firstName2 = scanner.nextLine();
        System.out.print("Enter the second DisasterVictim's last name: ");
        String lastName2 = scanner.nextLine();

        System.out.print("Enter the relationship type: ");
        String relationshipType = scanner.nextLine();

        // Check if both disaster victims exist in the database
        boolean found1 = doesDisasterVictimExist(firstName1, lastName1);
        boolean found2 = doesDisasterVictimExist(firstName2, lastName2);

        if (!found1 || !found2) {
            System.out.println("One or both DisasterVictims do not exist.");
            return;
        }

        // Remove the relationship from the database
        try {
            String sql = "DELETE FROM family_relations WHERE (person_one_first_name = ? AND person_one_last_name = ? AND person_two_first_name = ? AND person_two_last_name = ? AND relationship_type = ?) OR (person_one_first_name = ? AND person_one_last_name = ? AND person_two_first_name = ? AND person_two_last_name = ? AND relationship_type = ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, firstName1);
            statement.setString(2, lastName1);
            statement.setString(3, firstName2);
            statement.setString(4, lastName2);
            statement.setString(5, relationshipType);
            statement.setString(6, firstName2);
            statement.setString(7, lastName2);
            statement.setString(8, firstName1);
            statement.setString(9, lastName1);
            statement.setString(10, relationshipType);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Relationship removed successfully.");
            } else {
                System.out.println("No matching relationship found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static boolean doesDisasterVictimExist(String firstName, String lastName) {
        try {
            String sql = "SELECT COUNT(*) FROM disaster_victims WHERE first_name = ? AND last_name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    private static void searchAndViewInformation(Scanner scanner) {
        System.out.println("Search and View Information");

        // Prompt the user to specify search criteria
        System.out.print("Enter the first name of the DisasterVictim: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter the last name of the DisasterVictim: ");
        String lastName = scanner.nextLine();

        try {
            // Prepare SQL statement to retrieve information about the DisasterVictim
            String victimSql = "SELECT * FROM disaster_victims WHERE first_name = ? AND last_name = ?";
            PreparedStatement victimStatement = connection.prepareStatement(victimSql);
            victimStatement.setString(1, firstName);
            victimStatement.setString(2, lastName);

            // Execute the query to retrieve DisasterVictim information
            ResultSet victimResultSet = victimStatement.executeQuery();

            // Process the results
            if (victimResultSet.next()) {
                // Retrieve and display information about the DisasterVictim
                int id = victimResultSet.getInt("id");
                String dob = victimResultSet.getString("date_of_birth");
                int age = victimResultSet.getInt("approximate_age");

                System.out.println("Information about " + firstName + " " + lastName + ":");
                System.out.println("ID: " + id);
                System.out.println("Date of Birth: " + dob);
                System.out.println("Approximate Age: " + age);

                // Retrieve and display relationships
                System.out.println("Relationships:");
                String relationshipSql = "SELECT * FROM family_relations WHERE (person_one_first_name = ? AND person_one_last_name = ?) OR (person_two_first_name = ? AND person_two_last_name = ?)";
                PreparedStatement relationshipStatement = connection.prepareStatement(relationshipSql);
                relationshipStatement.setString(1, firstName);
                relationshipStatement.setString(2, lastName);
                relationshipStatement.setString(3, firstName);
                relationshipStatement.setString(4, lastName);

                ResultSet relationshipResultSet = relationshipStatement.executeQuery();

                while (relationshipResultSet.next()) {
                    String personOneFirstName = relationshipResultSet.getString("person_one_first_name");
                    String personOneLastName = relationshipResultSet.getString("person_one_last_name");
                    String personTwoFirstName = relationshipResultSet.getString("person_two_first_name");
                    String personTwoLastName = relationshipResultSet.getString("person_two_last_name");
                    String relationshipType = relationshipResultSet.getString("relationship_type");

                    // Print relationship information
                    System.out.println(personOneFirstName + " " + personOneLastName + " - " + personTwoFirstName + " " + personTwoLastName + " : " + relationshipType);
                }

                // Retrieve and display medical records
                System.out.println("Medical Records:");
                String medicalSql = "SELECT * FROM medical_records WHERE disaster_victim_id = ?";
                PreparedStatement medicalStatement = connection.prepareStatement(medicalSql);
                medicalStatement.setInt(1, id);

                ResultSet medicalResultSet = medicalStatement.executeQuery();

                while (medicalResultSet.next()) {
                    String treatmentDetails = medicalResultSet.getString("treatment_details");
                    String dateOfTreatment = medicalResultSet.getString("date_of_treatment");

                    // Print medical record information
                    System.out.println("Treatment Details: " + treatmentDetails);
                    System.out.println("Date of Treatment: " + dateOfTreatment);
                }

                // Close resources
                relationshipResultSet.close();
                relationshipStatement.close();
                medicalResultSet.close();
                medicalStatement.close();
            } else {
                System.out.println("No matching DisasterVictim found.");
            }

            // Close resources
            victimResultSet.close();
            victimStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}

