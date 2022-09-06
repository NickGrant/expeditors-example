package org.nickgrant.example.models;

import org.junit.Test;
import org.nickgrant.example.enums.US;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class HouseholdGroupTest {

    @Test
    public void fromOccupantsHappy () {
        HouseholdGroup group = HouseholdGroup.fromOccupants(getOccupantsList());
        assertEquals("Households size should be 1",1, group.getHouseholds().size());
    }

    @Test
    public void fromOccupantsMultiple () {
        ArrayList<Occupant> occupants = getOccupantsList();
        occupants.add(new Occupant("Alternate", "User", new Address("234 2nd", "Seattle", US.WASHINGTON), 22));
        HouseholdGroup group = HouseholdGroup.fromOccupants(occupants);
        assertEquals("Households size should be 2",2, group.getHouseholds().size());
    }

    @Test
    public void fromOccupantsEmpty () {
        HouseholdGroup group = HouseholdGroup.fromOccupants(new ArrayList<>());
        assertEquals("Households size should be 0", 0, group.getHouseholds().size());
    }

    @Test
    public void addHouseholdHappy() {
        HouseholdGroup group = new HouseholdGroup();
        group.addHousehold(new Household(getAddress()));
        assertEquals("Households size should be 1",1, group.getHouseholds().size());
    }

    /**
     * Get populated occupants list
     * @return populated list
     */
    private ArrayList<Occupant> getOccupantsList () {
        ArrayList<Occupant> output = new ArrayList<>();
        output.add(new Occupant("Test", "User", getAddress(), 34));

        return output;
    }

    /**
     * Get sample address
     * @return sample address
     */
    private Address getAddress () {
        return new Address("123 Main", "Seattle", US.WASHINGTON);
    }
}
