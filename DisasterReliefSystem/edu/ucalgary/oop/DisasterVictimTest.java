package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class DisasterVictimTest {

    private DisasterVictim person1;
    private DisasterVictim person2;
    private FamilyRelation relationship;

    @Before
    public void setUp() {
        // Initialize new DisasterVictim instances and create a FamilyRelation
        person1 = new DisasterVictim("Person1","surname", null, 99, null);
        person2 = new DisasterVictim("Person2", "surname", null, 89, null);
        relationship = new FamilyRelation(person1, "sibling", person2, 1);

        // Add the relationship to person1's familyConnections
        person1.addFamilyConnection(relationship);
    }


    @Test
    public void testConstructor() {
        // Create a DisasterVictim instance using the constructor
        DisasterVictim victim = new DisasterVictim("John", "Doe", new Date(1975, 3, 20), 45, new Date());

        // Verify the constructor initializes fields correctly
        assertNotNull(victim);
        assertEquals("John", victim.getFirstName());
        assertEquals("Doe", victim.getLastName());
        assertEquals(new Date(1975, 3, 20), victim.getDateOfBirth());
        assertEquals(45, victim.getApproximateAge());
        assertNotNull(victim.getEntryDate());
    }

    @Test
    public void testAddFamilyConnection() {
        // Verify that person1's familyConnections contains the relationship
        assertTrue(person1.getFamilyConnections().contains(relationship));
    }

    @Test
    public void testRemoveFamilyConnection() {
        // Verify that person1's familyConnections initially contains the relationship
        assertTrue(person1.getFamilyConnections().contains(relationship));

        // Remove the relationship from person1's familyConnections
        person1.removeFamilyConnection(relationship);

        // Verify that person1's familyConnections no longer contains the relationship
        assertFalse(person1.getFamilyConnections().contains(relationship));
    }

    @Test
    public void testHasFamilyConnectionWith_Positive() {
        // Verify that person1 has a family connection with person2
        assertTrue(person1.hasFamilyConnectionWith(person2));
    }

    @Test
    public void testHasFamilyConnectionWith_Negative() {
        // Verify that person1 does not have a family connection with a different person
        DisasterVictim otherPerson = new DisasterVictim("OtherPerson", "surname", null, 80, null);
        assertFalse(person1.hasFamilyConnectionWith(otherPerson));
    }

    @Test
    public void testHasFamilyConnectionWith_NullPerson() {
        // Verify behavior when checking against a null person
        assertFalse(person1.hasFamilyConnectionWith(null));
    }

    @Test
    public void testGetAndSetGender() {
        // Create a new DisasterVictim instance
        DisasterVictim victim = new DisasterVictim("John", "Doe", null, 25, null);

        // Set the gender using the setGender method
        victim.setGender("Male");

        // Get the gender using the getGender method and assert that it matches the expected value
        assertEquals("Male", victim.getGender());
    }



    @Test
    public void testSetAndGetComments() {
        // Create a DisasterVictim instance
        DisasterVictim victim = new DisasterVictim("John", "Doe", null, 25, null);

        // Set comments using the setter method
        victim.setComments("Test comments");

        // Verify that the comments were set correctly
        assertEquals("Test comments", victim.getComments());
    }
    @Test
    public void testAddAndRemovePersonalBelonging() {
        DisasterVictim victim = new DisasterVictim("John", "Doe", null, 25, null);
        Supply supply1 = new Supply("Clothing", 50);
        Supply supply2 = new Supply("Medications", 89);

        victim.addPersonalBelonging(supply1);
        victim.addPersonalBelonging(supply2);

        List<Supply> personalBelongings = victim.getPersonalBelongings();
        assertEquals(2, personalBelongings.size());
        assertTrue(personalBelongings.contains(supply1));
        assertTrue(personalBelongings.contains(supply2));

        victim.removePersonalBelonging(supply1);
        assertEquals(1, victim.getPersonalBelongings().size());
        assertFalse(victim.getPersonalBelongings().contains(supply1));
    }


    @Test
        public void testAddAndRemoveMedicalRecord() {
            DisasterVictim victim = new DisasterVictim("Jane", "Smith", null, 30, null);
            MedicalRecord record1 = new MedicalRecord(null, "Patient history", new Date());
            MedicalRecord record2 = new MedicalRecord(null, "allergy history", new Date());

            victim.addMedicalRecord(record1);
            victim.addMedicalRecord(record2);

            List<MedicalRecord> medicalRecords = victim.getMedicalRecords();
            assertEquals(2, medicalRecords.size());
            assertTrue(medicalRecords.contains(record1));
            assertTrue(medicalRecords.contains(record2));

            victim.removeMedicalRecord(record1);
            assertEquals(1, victim.getMedicalRecords().size());
            assertFalse(victim.getMedicalRecords().contains(record1));
        }


    @Test
    public void testSetAndGetDateOfBirth() {
        // Create a DisasterVictim instance with initial values
        DisasterVictim victim = new DisasterVictim("John", "Doe", null, -1, new Date());

        // Create a new date for testing
        Date dateOfBirth = new Date(1980 - 1900, 5, 15);  // Note: Adjust year to be relative to 1900

        // Set the date of birth using the setter method
        victim.setDateOfBirth(dateOfBirth);

        // Verify that the date of birth was set correctly
        assertEquals(dateOfBirth, victim.getDateOfBirth());
    }

    @Test
    public void testGetApproximateAgeAfterSettingDateOfBirth() {
        // Create a DisasterVictim instance with date of birth set
        Date dateOfBirth = new Date(1980 - 1900, 5, 15);  // Note: Adjust year to be relative to 1900
        DisasterVictim victim = new DisasterVictim("John", "Doe", dateOfBirth, -1, new Date());

        // Verify that the approximate age is reset to -1
        assertEquals(-1, victim.getApproximateAge());
    }

    @Test
    public void testSetAndGetApproximateAge() {
        // Create a DisasterVictim instance with initial values
        DisasterVictim victim = new DisasterVictim("John", "Doe", null, -1, new Date());

        // Set the approximate age using the setter method
        victim.setApproximateAge(50);

        // Verify that the approximate age was set correctly
        assertEquals(50, victim.getApproximateAge());
    }

    @Test
    public void testGetDateOfBirthAfterSettingApproximateAge() {
        // Create a DisasterVictim instance with approximate age set
        DisasterVictim victim = new DisasterVictim("John", "Doe", null, 50, new Date());

        // Verify that the date of birth is reset to null
        assertNull(victim.getDateOfBirth());
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
        DisasterVictim victim = new DisasterVictim("John", "Alex", null, -1, new Date());

        // Allocate the supply to the victim
        location.allocateSupply(victim, supply, 10);

        // Verify that the supply was removed from the location's available supplies
        assertFalse(location.getAvailableSupplies().contains(supply));
    }

    @Test
    public void testMutuallyExclusiveFields() {
        // Create a DisasterVictim instance with initial values
        DisasterVictim victim = new DisasterVictim("John", "Doe", null, -1, new Date());

        // Set date of birth and verify that approximate age is reset
        Date dateOfBirth = new Date(1980 - 1900, 5, 15);  // Note: Adjust year to be relative to 1900
        victim.setDateOfBirth(dateOfBirth);
        assertEquals(dateOfBirth, victim.getDateOfBirth());
        assertEquals(-1, victim.getApproximateAge());  // Ensure approximate age is reset

        // Set approximate age and verify that date of birth is reset
        victim.setApproximateAge(50);
        assertEquals(50, victim.getApproximateAge());
        assertNull(victim.getDateOfBirth());  // Ensure date of birth is reset
    }

    @Test
    public void testAddDietaryRestriction() {
        DisasterVictim victim = new DisasterVictim("John", "Doe", null, 25, null);
        victim.addDietaryRestriction(DietaryRestriction.AVML);
        victim.addDietaryRestriction(DietaryRestriction.DBML);
        assertEquals(2, victim.getDietaryRestrictions().size());
        assertTrue(victim.getDietaryRestrictions().contains(DietaryRestriction.AVML));
        assertTrue(victim.getDietaryRestrictions().contains(DietaryRestriction.DBML));
    }

    @Test
    public void testGetSetEntryDate() {
        // Create a DisasterVictim instance
        DisasterVictim victim = new DisasterVictim("John", "Doe", new Date(), 30, new Date());

        // Create a new date for testing
        Date newEntryDate = new Date();

        // Set the entry date using the setter method
        victim.setEntryDate(newEntryDate);

        // Verify that the entry date was set correctly
        assertEquals(newEntryDate, victim.getEntryDate());
    }

    @Test
    public void testSetAndGetDietaryRestrictions() {
        DisasterVictim victim = new DisasterVictim("John", "Doe", null, 25, null);

        // Create a list of dietary restrictions
        List<DietaryRestriction> restrictions = new ArrayList<>();
        restrictions.add(DietaryRestriction.AVML);
        restrictions.add(DietaryRestriction.DBML);

        // Set dietary restrictions
        victim.setDietaryRestrictions(restrictions);

        // Get dietary restrictions
        List<DietaryRestriction> retrievedRestrictions = victim.getDietaryRestrictions();

        // Check if retrieved restrictions match the original list
        assertEquals(restrictions.size(), retrievedRestrictions.size());
        assertTrue(retrievedRestrictions.contains(DietaryRestriction.AVML));
        assertTrue(retrievedRestrictions.contains(DietaryRestriction.DBML));
    }

    @Test
    public void testLoadGenderOptionsFromFile() {
        DisasterVictim.loadGenderOptionsFromFile("GenderOptions.txt");
        List<String> genderOptions = DisasterVictim.getValidGenderOptions();

        assertNotNull(genderOptions);
        assertFalse(genderOptions.isEmpty()); // Ensure there are options loaded

        // Read the expected gender options from the file
        List<String> expectedOptions = readGenderOptionsFromFile("GenderOptions.txt");

        // Verify that each expected option is present in the loaded options
        for (String expectedOption : expectedOptions) {
            assertTrue("gender option not found: " + expectedOption, genderOptions.contains(expectedOption));
        }
    }

    private List<String> readGenderOptionsFromFile(String fileName) {
        List<String> options = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(DisasterVictimTest.class.getResourceAsStream(fileName)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                options.add(line.trim());
            }
        } catch (IOException e) {
            System.err.println("Error reading gender options file: " + e.getMessage());
        }
        return options;
    }


}

