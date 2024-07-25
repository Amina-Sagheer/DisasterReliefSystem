/**
 * The `MedicalRecord` class represents a record of medical treatment associated with a specific location,
 * treatment details, and date of treatment.
 * This class facilitates the management of medical records within the disaster management system.
 */
package edu.ucalgary.oop;
import java.util.Date;

public class MedicalRecord {
    private Location location;
    private String treatmentDetails;
    private Date dateOfTreatment;

    // Constructor
    public MedicalRecord(Location location, String treatmentDetails, Date dateOfTreatment) {
        this.location = location;
        this.treatmentDetails = treatmentDetails;
        this.dateOfTreatment = dateOfTreatment;
    }

    // Getter and setter methods
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getTreatmentDetails() {
        return treatmentDetails;
    }

    public void setTreatmentDetails(String treatmentDetails) {
        this.treatmentDetails = treatmentDetails;
    }

    public Date getDateOfTreatment() {
        return dateOfTreatment;
    }

    public void setDateOfTreatment(Date dateOfTreatment) {
        this.dateOfTreatment = dateOfTreatment;
    }
}
