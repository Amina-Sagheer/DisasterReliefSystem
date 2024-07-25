package edu.ucalgary.oop;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;

public class ReliefServiceTest {
    private Inquirer inquirer;
    private DisasterVictim missingPerson;
    private Date dateOfInquiry;
    private String infoProvided;
    private Location lastKnownLocation;
    private ReliefService reliefService;

    @Before
    public void setUp() {
        Inquirer newInquirer = new Inquirer("Alice", "Johnson", "alice.johnson@example.com", "volunteer opportunities");
        missingPerson = new DisasterVictim("Jane", "Smith", null, 30, null);
        dateOfInquiry = new Date();
        infoProvided = "Additional information about the inquiry.";
        lastKnownLocation = new Location("City", "Country");

        reliefService = new ReliefService(inquirer, missingPerson, dateOfInquiry, infoProvided, lastKnownLocation);
    }

    @Test
    public void testConstructor() {
        assertNotNull(reliefService);
    }

    @Test
    public void testGetInquirer() {
        assertEquals(inquirer, reliefService.getInquirer());
    }

    @Test
    public void testSetInquirer() {
        Inquirer newInquirer = new Inquirer("Alice", "Johnson", "alice.johnson@example.com", "volunteer opportunities");
        reliefService.setInquirer(newInquirer);
        assertEquals(newInquirer, reliefService.getInquirer());
    }

    @Test
    public void testGetMissingPerson() {
        assertEquals(missingPerson, reliefService.getMissingPerson());
    }

    @Test
    public void testSetMissingPerson() {
        DisasterVictim newMissingPerson = new DisasterVictim("Mark", "Wilson", null, 40, null);
        reliefService.setMissingPerson(newMissingPerson);
        assertEquals(newMissingPerson, reliefService.getMissingPerson());
    }

    @Test
    public void testGetDateOfInquiry() {
        assertEquals(dateOfInquiry, reliefService.getDateOfInquiry());
    }

    @Test
    public void testSetDateOfInquiry() {
        Date newDateOfInquiry = new Date();
        reliefService.setDateOfInquiry(newDateOfInquiry);
        assertEquals(newDateOfInquiry, reliefService.getDateOfInquiry());
    }

    @Test
    public void testGetInfoProvided() {
        assertEquals(infoProvided, reliefService.getInfoProvided());
    }

    @Test
    public void testSetInfoProvided() {
        String newInfoProvided = "Updated information about the inquiry.";
        reliefService.setInfoProvided(newInfoProvided);
        assertEquals(newInfoProvided, reliefService.getInfoProvided());
    }

    @Test
    public void testGetLastKnownLocation() {
        assertEquals(lastKnownLocation, reliefService.getLastKnownLocation());
    }

    @Test
    public void testSetLastKnownLocation() {
        Location newLocation = new Location("Town", "Country");
        reliefService.setLastKnownLocation(newLocation);
        assertEquals(newLocation, reliefService.getLastKnownLocation());
    }

    @Test
    public void testGetLogDetails() {
        Inquirer inquirer = new Inquirer("John", "Alex", "1234567890", "Looking for family member");
        Person person = new Person("Jane", "Alex");
        DisasterVictim missingPerson = new DisasterVictim(person.getFirstName(), person.getLastName(), new Date(), 30, new Date());

        Date dateOfInquiry = new Date();
        String infoProvided = "Information provided about the missing person";
        Location lastKnownLocation = new Location("123 Main St", "Calgary");

        ReliefService reliefService = new ReliefService(inquirer, missingPerson, dateOfInquiry, infoProvided, lastKnownLocation);

        String expectedLog = "Inquirer: John Alex\n" +
                "Missing Person: Jane Alex\n" +
                "Date of Inquiry: " + dateOfInquiry.toString() + "\n" +
                "Info Provided: Information provided about the missing person\n" +
                "Last Known Location: 123 Main St, Calgary";

        assertEquals(expectedLog, reliefService.getLogDetails());
    }
}


