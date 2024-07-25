/**
 This class represents a DisasterVictim with essential attributes such as date of birth, approximate age,
 gender, dietary restrictions, and personal information.
 Key Points:
 Age or Birthdate: The class manages either the approximate age or the date of birth for a DisasterVictim,
 ensuring that only one can be set at a time using mutually exclusive setter methods.
 Gender Options from File: Gender options are loaded from a text file (GenderOptions.txt) to provide culturally
 relevant and validated gender choices for DisasterVictims, preventing invalid inputs.
 Dietary Restrictions: The class tracks dietary restrictions using an enumeration (DietaryRestriction),
 allowing multiple restrictions to be assigned to a DisasterVictim from a predefined list of options.
 Implementation includes appropriate methods for managing these features, such as setters for age and birthdate,
 loading gender options from a file, and adding/removing dietary restrictions.
 */


package edu.ucalgary.oop;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DisasterVictim extends Person {
    private Date dateOfBirth;
    private int approximateAge;
    private String comments;
    private List<MedicalRecord> medicalRecords;
    private List<FamilyRelation> familyConnections;
    private Date entryDate;
    private List<Supply> personalBelongings;
    private String gender;
    private static List<String> validGenderOptions;
    private static int counter = 0;
    private String info;
    private List<DietaryRestriction> dietaryRestrictions;
    private Map<Supply, Integer> allocatedSupplies;


    // Constructor
    public DisasterVictim(String firstName, String lastName, Date dateOfBirth, int approximateAge, Date entryDate) {
        super(firstName, lastName);
        this.dateOfBirth = dateOfBirth;
        this.approximateAge = approximateAge;
        this.entryDate = entryDate;
        this.medicalRecords = new ArrayList<>();
        this.familyConnections = new ArrayList<>();
        this.personalBelongings = new ArrayList<>();
        this.dietaryRestrictions = new ArrayList<>();
        this.gender = "";
        counter++;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    // Getters and setters for date of birth and approximate age
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        // Ensure only one of date of birth or approximate age is set
        this.approximateAge = -1; // Reset approximate age when date of birth is set
    }

    public int getApproximateAge() {
        return approximateAge;
    }

    public void setApproximateAge(int approximateAge) {
        this.approximateAge = approximateAge;
        // Ensure only one of date of birth or approximate age is set
        this.dateOfBirth = null; // Reset date of birth when approximate age is set
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public List<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

    public void setMedicalRecords(List<MedicalRecord> medicalRecords) {
        this.medicalRecords = medicalRecords;
    }

    public List<FamilyRelation> getFamilyConnections() {
        return familyConnections;
    }

    public void setFamilyConnections(List<FamilyRelation> familyConnections) {
        this.familyConnections = familyConnections;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public List<Supply> getPersonalBelongings() {
        return personalBelongings;
    }

    public void setPersonalBelongings(List<Supply> personalBelongings) {
        this.personalBelongings = personalBelongings;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public static List<String> getValidGenderOptions() {
        return validGenderOptions;
    }

    public static void setValidGenderOptions(List<String> validGenderOptions) {
        DisasterVictim.validGenderOptions = validGenderOptions;
    }
    public void receiveSupply(Supply supply, int quantity) {
        allocatedSupplies.put(supply, allocatedSupplies.getOrDefault(supply, 0) + quantity);
    }

    public Map<Supply, Integer> getAllocatedSupplies() {
        return allocatedSupplies;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        DisasterVictim.counter = counter;
    }

    public List<DietaryRestriction> getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    public void setDietaryRestrictions(List<DietaryRestriction> dietaryRestrictions) {
        this.dietaryRestrictions = dietaryRestrictions;
    }

    // Methods to add and remove medical records
    public void addMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecords.add(medicalRecord);
    }

    public void removeMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecords.remove(medicalRecord);
    }

    // Methods to add and remove family connections
    public void addFamilyConnection(FamilyRelation familyConnection) {
        familyConnections.add(familyConnection);
    }

    public void removeFamilyConnection(FamilyRelation familyConnection) {
        familyConnections.remove(familyConnection);
    }

    // Method to check if there's a family connection with a person

    public boolean hasFamilyConnectionWith(DisasterVictim person) {
        // Check if the person is a family connection
        for (FamilyRelation relation : familyConnections) {
            if (relation.getPersonOne().equals(person) || relation.getPersonTwo().equals(person)) {
                return true;
            }
        }
        return false;
    }


    // Methods to add and remove personal belongings
    public void addPersonalBelonging(Supply supply) {
        personalBelongings.add(supply);
    }

    public void removePersonalBelonging(Supply supply) {
        personalBelongings.remove(supply);
    }

    // Method to add dietary restriction
    public void addDietaryRestriction(DietaryRestriction restriction) {
        dietaryRestrictions.add(restriction);
    }
    // Methods to add and remove medical records, family connections, personal belongings, and dietary restrictions

    // Method to load gender options from file
    public static void loadGenderOptionsFromFile(String fileName) {
        validGenderOptions = new ArrayList<>();
        try (InputStream inputStream = DisasterVictim.class.getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("File not found: " + fileName);
            }

            // Use InputStreamReader with explicit encoding (UTF-8) to read the file
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    validGenderOptions.add(line.trim());
                }
            } catch (IOException e) {
                System.err.println("Error reading gender options file: " + e.getMessage());
            }
        } catch (IOException e) {
            System.err.println("Error opening gender options file: " + e.getMessage());
        }
    }





}
