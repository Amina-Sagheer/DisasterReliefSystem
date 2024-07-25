
/**
 * Represents a family relationship between two DisasterVictim objects.
 * This class ensures consistency in family relationship management by providing methods
 * to add and remove relationships symmetrically from both individuals' familyConnections lists.
 * Requirements fulfilled:
 * 1. **Family relationship consistency**: The class provides methods (`addRelationship()` and `removeRelationship()`)
 *    to ensure that family relationships are managed consistently. It checks if a relationship already exists
 *    before adding it and removes the relationship symmetrically from both individuals involved.
 * 2. **Symmetric management**: The `addRelationship()` and `removeRelationship()` methods maintain symmetry
 *    by adding or removing the relationship from both persons involved, ensuring that family connections are
 *    properly managed and updated.
 * 3. **Equals, toString, and hashCode methods**: Overrides these methods to properly compare and represent
 *    `FamilyRelation` objects based on personOne, personTwo, relationshipTo, and familyRelationID.
 */

package edu.ucalgary.oop;
import java.util.Objects;
public class FamilyRelation {
    private DisasterVictim personOne;
    private String relationshipTo;
    private DisasterVictim personTwo;
    private int familyRelationID;

    public FamilyRelation(DisasterVictim personOne, String relationshipTo, DisasterVictim personTwo, int familyRelationID) {
        this.personOne = personOne;
        this.relationshipTo = relationshipTo;
        this.personTwo = personTwo;
        this.familyRelationID = familyRelationID;
    }

    public int getFamilyRelationID() {
        return familyRelationID;
    }

    public void setFamilyRelationID(int familyRelationID) {
        this.familyRelationID = familyRelationID;
    }

    public DisasterVictim getPersonOne() {
        return personOne;
    }

    public void setPersonOne(DisasterVictim personOne) {
        this.personOne = personOne;
    }

    public String getRelationshipTo() {
        return relationshipTo;
    }

    public void setRelationshipTo(String relationshipTo) {
        this.relationshipTo = relationshipTo;
    }

    public DisasterVictim getPersonTwo() {
        return personTwo;
    }

    public void setPersonTwo(DisasterVictim personTwo) {
        this.personTwo = personTwo;
    }

    // Check if the relationship already exists
    public boolean relationshipExists() {
        // Check both sides of the relationship
        return personOne.hasFamilyConnectionWith(personTwo) && personTwo.hasFamilyConnectionWith(personOne);
    }

    // Method to add the relationship between two persons and ensure consistency
    public void addRelationship() {
        if (!relationshipExists()) {
            personOne.addFamilyConnection(this);
            personTwo.addFamilyConnection(this);
        } else {
            System.out.println("Relationship already exists.");
        }
    }

    // Method to remove the relationship between two persons
    public void removeRelationship() {
        if (relationshipExists()) {
            personOne.removeFamilyConnection(this);
            personTwo.removeFamilyConnection(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FamilyRelation that = (FamilyRelation) o;
        return familyRelationID == that.familyRelationID &&
                ((Objects.equals(personOne, that.personOne) && Objects.equals(personTwo, that.personTwo)) ||
                        (Objects.equals(personOne, that.personTwo) && Objects.equals(personTwo, that.personOne)));
    }

    @Override
    public String toString() {
        return "FamilyRelation{" +
                "personOne=" + personOne.getFirstName() + " " + personOne.getLastName() +
                ", relationshipTo='" + relationshipTo + '\'' +
                ", personTwo=" + personTwo.getFirstName() + " " + personTwo.getLastName() +
                ", familyRelationID=" + familyRelationID +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(personOne, relationshipTo, personTwo, familyRelationID);
    }
}

