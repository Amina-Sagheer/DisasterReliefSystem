package edu.ucalgary.oop;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PersonTest {
    private Person person;

    @Before
    public void setUp() {
        // Initialize a Person instance before each test
        person = new Person("John", "Doe");
    }

    @Test
    public void testConstructor() {
        // Test constructor by checking if Person instance is not null
        assertNotNull(person);
    }

    @Test
    public void testGetFirstName() {
        // Test getFirstName() method
        assertEquals("John", person.getFirstName());
    }

    @Test
    public void testGetLastName() {
        // Test getLastName() method
        assertEquals("Doe", person.getLastName());
    }

    @Test
    public void testSetFirstName() {
        // Test setFirstName() method
        person.setFirstName("Jane");
        assertEquals("Jane", person.getFirstName());
    }

    @Test
    public void testSetLastName() {
        // Test setLastName() method
        person.setLastName("Smith");
        assertEquals("Smith", person.getLastName());
    }
}


