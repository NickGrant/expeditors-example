package org.nickgrant.example.models;

import org.junit.Test;
import org.nickgrant.example.enums.US;

import static org.junit.Assert.*;

public class OccupantTest {

    private final String OCCUPANT_FIRST_NAME = "Jen";
    private final String OCCUPANT_LAST_NAME = "Jennifers";


    @Test
    public void getFirstName () {
        Occupant person = getOccupant();
        assertEquals("First name should not be changed", OCCUPANT_FIRST_NAME, person.getFirstName());
    }

    @Test
    public void getLastName () {
        Occupant person = getOccupant();
        assertEquals("Last name should not be changed", OCCUPANT_LAST_NAME, person.getLastName());
    }

    @Test
    public void isAdultHappy() {
        Occupant person = getOccupant();
        assertTrue("Person is an adult", person.isAdult());
    }

    @Test
    public void isAdultFalse () {
        Occupant person = new Occupant(OCCUPANT_FIRST_NAME, OCCUPANT_LAST_NAME, getAddress(), 12);
        assertFalse("Person is a child", person.isAdult());
    }

    @Test
    public void toStringHappy () {
        Occupant person = getOccupant();
        String expectedString = "Jen Jennifers - 123 main Federal Way, WA - Age: 35";
        assertEquals("String should be properly formatted", expectedString, person.toString());
    }

    @Test
    public void getAddressHappy () {
        Occupant person = getOccupant();
        assertEquals("Address should remain unchanged", getAddress(), person.getAddress());
    }

    /**
     * Helper method to get address object
     * @return default address to use
     */
    private Address getAddress () {
        return new Address("123 main", "Federal Way", US.WASHINGTON);
    }

    /**
     * Helper method to get occupant object
     * @return default occupant to use
     */
    private Occupant getOccupant () {
        return new Occupant(OCCUPANT_FIRST_NAME, OCCUPANT_LAST_NAME, getAddress(), 35);
    }
}
