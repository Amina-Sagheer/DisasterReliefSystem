package edu.ucalgary.oop;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LocationTest {
    private Location location;
    private DisasterVictim occupant;
    private Supply supply;

    @Before
    public void setUp() {
        // Initialize test data before each test
        location = new Location("Hospital", "123 Main St");
        occupant = new DisasterVictim("John", "Doe", null, 30, null);
        supply = new Supply("Food", 50);
    }

    @Test
    public void testConstructor() {
        // Test constructor by checking if Location instance is not null
        assertNotNull(location);

        // Test initial values set by constructor
        assertEquals("Hospital", location.getName());
        assertEquals("123 Main St", location.getAddress());
        assertNotNull(location.getOccupants());
        assertNotNull(location.getSupplies());
        assertNotNull(location.getAvailableSupplies());
        assertTrue(location.getOccupants().isEmpty());
        assertTrue(location.getSupplies().isEmpty());
        assertTrue(location.getAvailableSupplies().isEmpty());
    }

    @Test
    public void testGettersAndSetters() {
        // Test getters and setters
        location.setName("Clinic");
        assertEquals("Clinic", location.getName());

        location.setAddress("456 Elm St");
        assertEquals("456 Elm St", location.getAddress());

        List<DisasterVictim> occupants = new ArrayList<>();
        occupants.add(occupant);
        location.setOccupants(occupants);
        assertEquals(1, location.getOccupants().size());
        assertTrue(location.getOccupants().contains(occupant));

        List<Supply> supplies = new ArrayList<>();
        supplies.add(supply);
        location.setSupplies(supplies);
        assertEquals(1, location.getSupplies().size());
        assertTrue(location.getSupplies().contains(supply));
    }

    @Test
    public void testUpdateAvailableSupplies() {
        // Test updateAvailableSupplies() method
        location.addSupply(new Supply("Water", 100));
        location.addSupply(new Supply("Blanket", 0));
        location.updateAvailableSupplies();

        assertEquals(1, location.getAvailableSupplies().size());
        assertTrue(location.getAvailableSupplies().get(0).getType().equals("Water"));
    }

    @Test
    public void testAddAndRemoveOccupant() {
        // Test addOccupant() and removeOccupant() methods
        location.addOccupant(occupant);
        assertEquals(1, location.getOccupants().size());
        assertTrue(location.getOccupants().contains(occupant));

        location.removeOccupant(occupant);
        assertTrue(location.getOccupants().isEmpty());
    }

    @Test
    public void testAddAndRemoveSupply() {
        // Test addSupply() and removeSupply() methods
        location.addSupply(supply);
        assertEquals(1, location.getSupplies().size());
        assertTrue(location.getSupplies().contains(supply));

        location.removeSupply(supply);
        assertTrue(location.getSupplies().isEmpty());
    }

    @Test
    public void testAllocateSupply() {
        // Create a location
        Location location = new Location("Test Location", "123 Test St");

        // Create a supply
        Supply supply = new Supply("Water", 50);

        // Add the supply to the location's available supplies
        location.addSupply(supply);

        // Create a victim
        Person person = new Person("John", "Alex");
        DisasterVictim victim = new DisasterVictim(person.getFirstName(), person.getLastName(), new Date(), 30, new Date());


        // Allocate the supply to the victim
        location.allocateSupply(victim, supply, 10);

        // Verify that the supply was removed from the location's available supplies
        assertFalse(location.getAvailableSupplies().contains(supply));
    }
}


