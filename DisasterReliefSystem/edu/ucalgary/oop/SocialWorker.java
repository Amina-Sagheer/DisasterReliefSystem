/**
 * The `SocialWorker` class represents a social worker, extending the `Person` class.
 * It encapsulates information about a social worker including their assigned social ID.
 * Requirements met by this class:
 * Inheritance: Inherits properties and methods from the `Person` class.
 * This class enables representation and management of social workers within the system,
 * inheriting common properties from the `Person` class.
 */
package edu.ucalgary.oop;
public class SocialWorker extends Person {
    // Additional field
    private int assignedSocialID;

    // Constructor
    public SocialWorker(String firstName, String lastName, int assignedSocialID) {
        super(firstName, lastName);
        this.assignedSocialID = assignedSocialID;
    }

    // Getter for assignedSocialID
    public int getAssignedSocialID() {
        return assignedSocialID;
    }

    // Setter for assignedSocialID
    public void setAssignedSocialID(int assignedSocialID) {
        this.assignedSocialID = assignedSocialID;
    }
}

