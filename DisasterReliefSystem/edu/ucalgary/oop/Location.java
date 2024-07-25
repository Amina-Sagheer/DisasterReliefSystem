/**
 * Represents a location where disaster victims are housed and supplies are managed.
 * Each location has a name, address, list of occupants (DisasterVictims), and lists of supplies and available supplies.
 * Requirements fulfilled:
 * 1. **Supply consistency**: The `Location` class ensures that supply allocation to a `DisasterVictim` is handled
 *    consistently by updating the list of available supplies when supplies are allocated or deallocated.
 *    - The `allocateSupply()` method allocates a specified quantity of a supply to a `DisasterVictim` and updates
 *      the available supplies list accordingly. It checks if the supply is available at the location before allocation.
 *    - The `deallocateSupply()` method deallocates a specified quantity of a supply from a `DisasterVictim` and updates
 *      the available supplies list by adding the supply back if needed.
 *    - The `updateAvailableSupplies()` method is used internally to refresh the list of available supplies based on the
 *      current supplies in stock.
 *    - The `addSupply()` and `removeSupply()` methods are used to manage the supplies at the location and automatically
 *      update the available supplies list.
 *    - The `addOccupant()` and `removeOccupant()` methods manage the list of occupants at the location.
 */

package edu.ucalgary.oop;
import java.util.ArrayList;
import java.util.List;

public class Location {
    private String name;
    private String address;
    private List<DisasterVictim> occupants;
    private List<Supply> supplies;
    private List<Supply> availableSupplies;

    public Location(String name, String address) {
        this.name = name;
        this.address = address;
        this.occupants = new ArrayList<>();
        this.supplies = new ArrayList<>();
        this.availableSupplies = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<DisasterVictim> getOccupants() {
        return occupants;
    }

    public void setOccupants(List<DisasterVictim> occupants) {
        this.occupants = occupants;
    }

    public List<Supply> getSupplies() {
        return supplies;
    }

    public void setSupplies(List<Supply> supplies) {
        this.supplies = supplies;
    }

    public List<Supply> getAvailableSupplies() {
        return availableSupplies;
    }

    public void updateAvailableSupplies() {
        availableSupplies.clear();
        for (Supply supply : supplies) {
            if (supply.getQuantity() > 0) {
                availableSupplies.add(supply);
            }
        }
    }

    public void addOccupant(DisasterVictim occupant) {
        occupants.add(occupant);
    }

    public void removeOccupant(DisasterVictim occupant) {
        occupants.remove(occupant);
    }

    public void addSupply(Supply supply) {
        supplies.add(supply);
        updateAvailableSupplies();
    }

    public void removeSupply(Supply supply) {
        supplies.remove(supply);
        updateAvailableSupplies();
    }

    public void allocateSupply(DisasterVictim victim, Supply supply, int quantityToAllocate) {
        if (availableSupplies.contains(supply)) {
            int availableQuantity = supply.getQuantity();
            int allocatedQuantity = Math.min(availableQuantity, quantityToAllocate);
            supply.setQuantity(availableQuantity - allocatedQuantity);
            for (int i = 0; i < allocatedQuantity; i++) {
                victim.addPersonalBelonging(new Supply(supply.getType(), 1));
            }
            updateAvailableSupplies();
            // Remove the allocated supply from available supplies
            availableSupplies.remove(supply);
        }
    }
    public void deallocateSupply(DisasterVictim victim, Supply supply, int quantityToDeallocate) {
        if (victim.getPersonalBelongings().contains(supply)) {
            for (int i = 0; i < quantityToDeallocate; i++) {
                victim.removePersonalBelonging(supply);
            }
            supply.setQuantity(supply.getQuantity() + quantityToDeallocate);
            updateAvailableSupplies();
        }
    }
}

