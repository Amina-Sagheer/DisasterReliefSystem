package edu.ucalgary.oop;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class SocialWorkerTest {
    private SocialWorker socialWorker;

    @Before
    public void setUp() {
        // Initialize a SocialWorker instance before each test
        socialWorker = new SocialWorker("Alice", "Smith", 123456);
    }

    @Test
    public void testConstructor() {
        // Test constructor by checking if SocialWorker instance is not null
        assertNotNull(socialWorker);
    }

    @Test
    public void testGetFirstName() {
        // Test getFirstName() method
        assertEquals("Alice", socialWorker.getFirstName());
    }

    @Test
    public void testGetLastName() {
        // Test getLastName() method
        assertEquals("Smith", socialWorker.getLastName());
    }

    @Test
    public void testGetAssignedSocialID() {
        // Test getAssignedSocialID() method
        assertEquals(123456, socialWorker.getAssignedSocialID());
    }

    @Test
    public void testSetAssignedSocialID() {
        // Test setAssignedSocialID() method
        socialWorker.setAssignedSocialID(789012);
        assertEquals(789012, socialWorker.getAssignedSocialID());
    }

    @Test
    public void testSetFirstName() {
        // Test setFirstName() method
        socialWorker.setFirstName("Bob");
        assertEquals("Bob", socialWorker.getFirstName());
    }

    @Test
    public void testSetLastName() {
        // Test setLastName() method
        socialWorker.setLastName("Johnson");
        assertEquals("Johnson", socialWorker.getLastName());
    }
}



