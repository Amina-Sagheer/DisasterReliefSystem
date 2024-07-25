package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InquirerTest {

    private Inquirer inquirer;

    @Before
    public void setUp() {
        // Initialize an Inquirer instance before each test
        inquirer = new Inquirer("John", "Doe", "123-456-7890", "Interested in volunteering");
    }

    @Test
    public void testConstructor() {
        // Verify constructor initialization
        assertEquals("John", inquirer.getFirstName());
        assertEquals("Doe", inquirer.getLastName());
        assertEquals("123-456-7890", inquirer.getServicesPhone());
        assertEquals("Interested in volunteering", inquirer.getInfo());
    }
    @Test
    public void testGetFirstName() {
        assertEquals("John", inquirer.getFirstName());
    }

    @Test
    public void testGetLastName() {
        assertEquals("Doe", inquirer.getLastName());
    }

    @Test
    public void testGetServicesPhone() {
        assertEquals("123-456-7890", inquirer.getServicesPhone());
    }

    @Test
    public void testGetInfo() {
        assertEquals("Interested in volunteering", inquirer.getInfo());
    }

    @Test
    public void testInteractionLog() {
        // Create Inquirer objects for two different inquirers
        Inquirer inquirer1 = new Inquirer("John", "Doe", "123-456-7890", "Interested in volunteering");
        Inquirer inquirer2 = new Inquirer("Jane", "Smith", "987-654-3210", "Seeking assistance");

        // Log interactions for the first inquirer
        inquirer1.logInteraction("Requested more information about volunteering opportunities.");
        inquirer1.logInteraction("Expressed interest in participating in community events.");

        // Log interactions for the second inquirer
        inquirer2.logInteraction("Asked about available support services.");
        inquirer2.logInteraction("Requested assistance with transportation.");

        // Assert interaction logs for both inquirers
        assertEquals("Requested more information about volunteering opportunities.", inquirer1.getInteractionLog().get(0));
        assertEquals("Expressed interest in participating in community events.", inquirer1.getInteractionLog().get(1));
        assertEquals("Asked about available support services.", inquirer2.getInteractionLog().get(0));
        assertEquals("Requested assistance with transportation.", inquirer2.getInteractionLog().get(1));
    }
}
