/**
 * The `Person` class represents a generic person with a first name and last name.
 * It serves as the parent class for `SocialWorker` and `DisasterVictim` classes,
 * providing common attributes and behaviors.
   Encapsulation: Encapsulates personal data (first name, last name) using private fields and getter/setter methods.
 * This class forms the foundation for modeling individuals within the disaster management system.
 */
package edu.ucalgary.oop;
public class Person {
    // Fields
    private String firstName;
    private String lastName;

    // Constructor
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getters and setters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
