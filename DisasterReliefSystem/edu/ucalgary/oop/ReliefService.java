/**
 * The `ReliefService` class represents a record of relief inquiry related to a missing person.
 * It encapsulates information about the inquirer, missing person, date of inquiry, provided information,
 * and the last known location of the missing person.
 * Encapsulation: Encapsulates data related to a relief inquiry using private fields and getter/setter methods.
 * This class facilitates tracking and managing relief services related to missing persons within the system.
 */
package edu.ucalgary.oop;
import java.util.Date;

public class ReliefService {
    private Inquirer inquirer;
    private DisasterVictim missingPerson;
    private Date dateOfInquiry;
    private String infoProvided;
    private Location lastKnownLocation;

    public ReliefService(Inquirer inquirer, DisasterVictim missingPerson, Date dateOfInquiry,
                         String infoProvided, Location lastKnownLocation) {
        this.inquirer = inquirer;
        this.missingPerson = missingPerson;
        this.dateOfInquiry = dateOfInquiry;
        this.infoProvided = infoProvided;
        this.lastKnownLocation = lastKnownLocation;
    }

    public Inquirer getInquirer() {
        return inquirer;
    }

    public void setInquirer(Inquirer inquirer) {
        this.inquirer = inquirer;
    }

    public DisasterVictim getMissingPerson() {
        return missingPerson;
    }

    public void setMissingPerson(DisasterVictim missingPerson) {
        this.missingPerson = missingPerson;
    }

    public Date getDateOfInquiry() {
        return dateOfInquiry;
    }

    public void setDateOfInquiry(Date dateOfInquiry) {
        this.dateOfInquiry = dateOfInquiry;
    }

    public String getInfoProvided() {
        return infoProvided;
    }

    public void setInfoProvided(String infoProvided) {
        this.infoProvided = infoProvided;
    }

    public Location getLastKnownLocation() {
        return lastKnownLocation;
    }

    public void setLastKnownLocation(Location lastKnownLocation) {
        this.lastKnownLocation = lastKnownLocation;
    }

    public String getLogDetails() {
        return "Inquirer: " + inquirer.getFirstName() + " " + inquirer.getLastName() + "\n" +
                "Missing Person: " + missingPerson.getFirstName() + " " + missingPerson.getLastName() + "\n" +
                "Date of Inquiry: " + dateOfInquiry + "\n" +
                "Info Provided: " + infoProvided + "\n" +
                "Last Known Location: " + lastKnownLocation.getName() + ", " + lastKnownLocation.getAddress();

    }

}

