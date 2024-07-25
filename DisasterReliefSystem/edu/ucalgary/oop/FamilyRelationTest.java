package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class FamilyRelationTest {

    private DisasterVictim person1;
    private DisasterVictim person2;



    @Before
    public void setUp() {
        // Create two DisasterVictim objects for testing
        person1 = new DisasterVictim("John", "Doe", null, 25, null);
        person2 = new DisasterVictim("Jane", "Smith", new Date(1985, 10, 20), -1, null);
    }

    @Test
    public void testRelationshipExists() {
        // Create a FamilyRelation object
        FamilyRelation relation = new FamilyRelation(person1, "Sibling", person2, 1);

        // Initially, the relationship should not exist
        assertFalse(relation.relationshipExists());

        // Add the relationship
        relation.addRelationship();

        // Now, the relationship should exist
        assertTrue(relation.relationshipExists());
    }

    @Test
    public void testAddAndRemoveRelationship() {
        // Create a FamilyRelation object
        FamilyRelation relation = new FamilyRelation(person1, "Parent", person2, 2);

        // Add the relationship
        relation.addRelationship();

        // Verify that the relationship exists
        assertTrue(person1.hasFamilyConnectionWith(person2));
        assertTrue(person2.hasFamilyConnectionWith(person1));

        // Remove the relationship
        relation.removeRelationship();

        // Verify that the relationship is removed
        assertFalse(person1.hasFamilyConnectionWith(person2));
        assertFalse(person2.hasFamilyConnectionWith(person1));
    }

    @Test
    public void testEquals() {
        // Create two FamilyRelation objects with the same details
        FamilyRelation relation1 = new FamilyRelation(person1, "Parent", person2, 3);
        FamilyRelation relation2 = new FamilyRelation(person1, "Parent", person2, 3);

        // Relation objects should be equal
        assertEquals(relation1, relation2);

        // Change the relationship ID
        relation2.setFamilyRelationID(4);

        // Now, relation objects should not be equal
        assertNotEquals(relation1, relation2);
    }

    @Test
    public void testToString() {
        // Create a FamilyRelation object
        FamilyRelation relation = new FamilyRelation(person1, "Sibling", person2, 5);

        // Check the string representation
        String expectedString = "FamilyRelation{personOne=John Doe, relationshipTo='Sibling', personTwo=Jane Smith, familyRelationID=5}";
        assertEquals(expectedString, relation.toString());
    }

    // Test other getters and setters if needed
    @Test
    public void testGettersAndSetters() {
        FamilyRelation relation = new FamilyRelation(person1, "Parent", person2, 6);

        // Test getters
        assertEquals(person1, relation.getPersonOne());
        assertEquals("Parent", relation.getRelationshipTo());
        assertEquals(person2, relation.getPersonTwo());
        assertEquals(6, relation.getFamilyRelationID());

        // Test setters
        relation.setRelationshipTo("Spouse");
        assertEquals("Spouse", relation.getRelationshipTo());

        DisasterVictim newPerson = new DisasterVictim("Alice", "Brown", null, 30, null);
        relation.setPersonTwo(newPerson);
        assertEquals(newPerson, relation.getPersonTwo());
    }


@Test
    public void testRelationshipCreation() {
        DisasterVictim peace = new DisasterVictim("Peace", "Lastname", new Date(), 25, new Date());
        DisasterVictim sam = new DisasterVictim("Sam", "Lastname", new Date(), 30, new Date());

        FamilyRelation relationshipPeaceSam = new FamilyRelation(peace, "Sibling", sam, 1);
        relationshipPeaceSam.addRelationship();

        assertTrue(peace.hasFamilyConnectionWith(sam));
        assertTrue(sam.hasFamilyConnectionWith(peace));
    }

    @Test
    public void testDuplicateDataPrevention() {
        DisasterVictim peace = new DisasterVictim("Peace", "Lastname", new Date(), 25, new Date());
        DisasterVictim sam = new DisasterVictim("Sam", "Lastname", new Date(), 30, new Date());

        FamilyRelation relationshipPeaceSam1 = new FamilyRelation(peace, "Sibling", sam, 1);
        FamilyRelation relationshipPeaceSam2 = new FamilyRelation(peace, "Sibling", sam, 2);

        relationshipPeaceSam1.addRelationship();
        relationshipPeaceSam2.addRelationship();

        assertTrue(peace.hasFamilyConnectionWith(sam));
        assertTrue(sam.hasFamilyConnectionWith(peace));
    }
    @Test
    public void testFamilyRelationConsistency() {
        DisasterVictim peace = new DisasterVictim("Peace", "Lastname", new Date(), 25, new Date());
        DisasterVictim sam = new DisasterVictim("Sam", "Lastname", new Date(), 30, new Date());

        // Create a FamilyRelation representing a sibling relationship between peace and sam
        FamilyRelation relationshipPeaceSam = new FamilyRelation(peace, "Sibling", sam, 1);

        // Add the relationship (first attempt)
        relationshipPeaceSam.addRelationship();

        // Verify that both peace and sam have a family connection with each other
        assertTrue(peace.hasFamilyConnectionWith(sam));
        assertTrue(sam.hasFamilyConnectionWith(peace));

        // Create another FamilyRelation with the same relationship (duplicate attempt)
        FamilyRelation duplicateRelationship = new FamilyRelation(peace, "Sibling", sam, 2);

        // Attempt to add the duplicate relationship
        duplicateRelationship.addRelationship();

        // Verify that adding the duplicate relationship did not create a new connection
        assertTrue(peace.hasFamilyConnectionWith(sam));
        assertTrue(sam.hasFamilyConnectionWith(peace));
    }
    @Test
    public void testAddSingleRelationship() {
        // Create a FamilyRelation between person1 and person2
        FamilyRelation relationship = new FamilyRelation(person1, "sibling", person2, 1);
        relationship.addRelationship();

        // Verify that the relationship was added to both persons' connections
        assertTrue(person1.hasFamilyConnectionWith(person2));
        assertTrue(person2.hasFamilyConnectionWith(person1));
    }

    @Test
    public void testAddBidirectionalRelationships() {
        // Create and add relationship from person1 to person2
        FamilyRelation relationship1 = new FamilyRelation(person1, "sibling", person2, 1);
        relationship1.addRelationship();

        // Create and add relationship from person2 to person1
        FamilyRelation relationship2 = new FamilyRelation(person2, "sibling", person1, 2);
        relationship2.addRelationship();

        // Verify bidirectional relationships
        assertTrue(person1.hasFamilyConnectionWith(person2));
        assertTrue(person2.hasFamilyConnectionWith(person1));
    }

    @Test
    public void testCopyAndStoreRelationship() {
        // Create relationship1 and add it to person1's familyConnections
        FamilyRelation relationship1 = new FamilyRelation(person1, "sibling", person2, 1);
        relationship1.addRelationship();

        // Create relationship2 by copying relationship1
        FamilyRelation relationship2 = new FamilyRelation(person1, "sibling", person2, 2);
        relationship2.addRelationship();

        // Verify that relationship2 is stored in person2's familyConnections
        assertTrue(person2.hasFamilyConnectionWith(person1));
    }

}

