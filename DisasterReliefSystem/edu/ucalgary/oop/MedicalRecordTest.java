package edu.ucalgary.oop;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;

public class MedicalRecordTest {
    private MedicalRecord medicalRecord;
    private Location testLocation;
    private Date testDate;

    @Before
    public void setUp() {
        // Initialize test data before each test
        testLocation = new Location("Hospital", "123 Main St");
        testDate = new Date(2024, 3, 15); // Note: Deprecated usage for demonstration

        // Create a MedicalRecord instance before each test
        medicalRecord = new MedicalRecord(testLocation, "Surgery", testDate);
    }

    @Test
    public void testConstructor() {
        // Test constructor by checking if MedicalRecord instance is not null
        assertNotNull(medicalRecord);
    }

    @Test
    public void testGetLocation() {
        // Test getLocation() method
        assertEquals(testLocation, medicalRecord.getLocation());
    }

    @Test
    public void testSetLocation() {
        // Create a new Location instance for testing
        Location newLocation = new Location("Clinic", "456 Elm St");

        // Test setLocation() method
        medicalRecord.setLocation(newLocation);
        assertEquals(newLocation, medicalRecord.getLocation());
    }

    @Test
    public void testGetTreatmentDetails() {
        // Test getTreatmentDetails() method
        assertEquals("Surgery", medicalRecord.getTreatmentDetails());
    }

    @Test
    public void testSetTreatmentDetails() {
        // Test setTreatmentDetails() method
        medicalRecord.setTreatmentDetails("Medication");
        assertEquals("Medication", medicalRecord.getTreatmentDetails());
    }

    @Test
    public void testGetDateOfTreatment() {
        // Test getDateOfTreatment() method
        assertEquals(testDate, medicalRecord.getDateOfTreatment());
    }

    @Test
    public void testSetDateOfTreatment() {
        // Create a new Date instance for testing
        Date newDate = new Date(2025, 4, 20); // Note: Deprecated usage for demonstration

        // Test setDateOfTreatment() method
        medicalRecord.setDateOfTreatment(newDate);
        assertEquals(newDate, medicalRecord.getDateOfTreatment());
    }
}
