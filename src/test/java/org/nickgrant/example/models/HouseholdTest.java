package org.nickgrant.example.models;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.junit.Test;
import org.nickgrant.example.enums.US;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class HouseholdTest {

    private final String OCCUPANT_FIRST_NAME = "Matt";
    private final String OCCUPANT_LAST_NAME = "Matthews";
    private final int OCCUPANT_AGE = 35;

    @Test
    public void addOccupantHappy () {
        Household house = new Household(getAddress());
        Occupant person = new Occupant(OCCUPANT_FIRST_NAME, OCCUPANT_LAST_NAME, getAddress(), OCCUPANT_AGE);
        house.addOccupant(person);
        assertTrue("Household should have 1 occupant", house.getOccupants().contains(person));
    }

    @Test
    public void addOccupantWrongAddress () {
        Household house = new Household(getAddress());
        Address badAddress = new Address("234 2nd ave", "Federal Way", US.WASHINGTON);
        Occupant person = new Occupant(OCCUPANT_FIRST_NAME, OCCUPANT_LAST_NAME, badAddress, OCCUPANT_AGE);
        assertEquals("Household should have 0 occupants", 0, house.getOccupants().size());
    }

    @Test
    public void getAdultsHappy () {
        Household house = new Household(getAddress());
        Occupant adult = new Occupant(OCCUPANT_FIRST_NAME, OCCUPANT_LAST_NAME, getAddress(), OCCUPANT_AGE);
        Occupant child = new Occupant("Mike", OCCUPANT_LAST_NAME, getAddress(), 12);
        house.addOccupant(adult);
        house.addOccupant(child);
        ArrayList<Occupant> adults = house.getAdults();
        assertTrue("List should have adult occupant", adults.contains(adult));
        assertFalse("List should not have child occupant", adults.contains(child));
    }

    @Test
    public void toStringHappy () {
        Household house = new Household(getAddress());
        Occupant person = new Occupant(OCCUPANT_FIRST_NAME, OCCUPANT_LAST_NAME, getAddress(), OCCUPANT_AGE);
        house.addOccupant(person);
        assertEquals("Output should be properly formatted", "123 main Federal Way, WA - 1", house.toString());
    }

    private Address getAddress () {
        return new Address("123 main", "Federal Way", US.WASHINGTON);
    }
}
