package org.nickgrant.example.models;

import java.util.ArrayList;
import java.util.Comparator;

public class HouseholdGroup {

    private ArrayList<Household> households = new ArrayList<>();

    public HouseholdGroup() {}

    public HouseholdGroup(ArrayList<Household> households) {
        this.households = households;
    }

    public ArrayList<Household> getHouseholds () {
        return households;
    }

    public void addHousehold (Household household) {
        households.add(household);
    }

    public static HouseholdGroup fromOccupants (ArrayList<Occupant> occupants) {
        Address currentAddress = null;
        Household currentHousehold = null;
        ArrayList<Household> households = new ArrayList<>();

        // Sort entries by address for matching purposes
        occupants.sort(
            Comparator.comparing((Occupant o) -> o.getAddress().toMatchString())
        );

        for (Occupant entry : occupants) {
            if (currentAddress == null || !currentAddress.equals(entry.getAddress())) {
                // Because we sorted our entries by address, if we have a new address we need a new household
                currentAddress = entry.getAddress();

                if (currentHousehold != null) {
                    // If we have an existing household, add it to output
                    households.add(currentHousehold);
                }

                if (currentAddress != null) {
                    // Create a new Household with the new Address
                    currentHousehold = new Household(currentAddress);
                }

            }

            // Add the person to the current household
            if (currentHousehold != null) {
                currentHousehold.addOccupant(entry);
            }

        }
        if (currentHousehold != null) {
            // If we have an existing household, add the final household to the list
            households.add(currentHousehold);
        }
        return new HouseholdGroup(households);
    }
}
