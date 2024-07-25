/**
 * Represents an inquirer who interacts with a system and maintains a log of interactions.
 * Each inquirer is identified by their first name, last name, and services phone number.
 * Requirements fulfilled:
 * 1. **Multiple interactions with inquirer**: The `Inquirer` class maintains a static map (`interactionLogs`)
 *    that stores interaction logs for each inquirer identified by their services phone number.
 *    - The constructor ensures that a new entry is added to `interactionLogs` if the services phone number
 *      is encountered for the first time.
 *    - The `logInteraction()` method adds interaction details to the log associated with the inquirer's services phone number.
 *    - The `getInteractionLog()` method retrieves the interaction log for a specific inquirer based on their services phone number.
 */
package edu.ucalgary.oop;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inquirer {
    // Fields
    private String firstName;
    private String lastName;
    private String info;
    private String servicesPhone;
    private static Map<String, List<String>> interactionLogs = new HashMap<>();

    // Constructor
    public Inquirer(String firstName, String lastName, String servicesPhone, String info) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.servicesPhone = servicesPhone;
        this.info = info;
        if (!interactionLogs.containsKey(servicesPhone)) {
            interactionLogs.put(servicesPhone, new ArrayList<>());
        }
    }

    // Getter methods
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getInfo() {
        return info;
    }

    public String getServicesPhone() {
        return servicesPhone;
    }

    // Method to log interaction
    public void logInteraction(String interactionDetails) {
        interactionLogs.get(servicesPhone).add(interactionDetails);
    }

    // Method to get interaction log
    public List<String> getInteractionLog() {
        return interactionLogs.get(servicesPhone);
    }
}
