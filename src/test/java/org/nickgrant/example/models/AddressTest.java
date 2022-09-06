package org.nickgrant.example.models;

import org.junit.Test;
import org.nickgrant.example.enums.US;

import static org.junit.Assert.*;

public class AddressTest {

    private final String CLEAN_STREET = "123 main st";
    private final String DIRTY_STREET = "123 main st.";
    private final String CITY = "Federal Way";
    private final US STATE = US.WASHINGTON;

    @Test
    public void normalizeStreetHappy () {
        Address address = new Address(DIRTY_STREET, CITY, STATE);
        assertEquals("Address should be normalized", CLEAN_STREET, address.getStreet());
    }

    @Test
    public void normalizeStreetNoChange () {
        Address address = new Address(CLEAN_STREET, CITY, STATE);
        assertEquals("Address should not be changed", CLEAN_STREET, address.getStreet());
    }

    @Test
    public void equalsHappy () {
        Address address1 = new Address(CLEAN_STREET, CITY, STATE);
        Address address2 = new Address(CLEAN_STREET, CITY, STATE);
        assertEquals("Objects should be equal", address1, address2);
    }

    @Test
    public void equalsDifferent () {
        String secondStreet = "234 2nd ave";
        Address address1 = new Address(CLEAN_STREET, CITY, STATE);
        Address address2 = new Address(secondStreet, CITY, STATE);
        assertNotEquals("Objects should not be equal", address1, address2);
    }

    @Test
    public void toStringHappy () {
        String expectedOutput = "123 main st Federal Way, WA";
        Address address = new Address(CLEAN_STREET, CITY, STATE);
        assertEquals("Address should match output", expectedOutput, address.toString());
    }

    @Test
    public void getRawStreetHappy () {
        Address address = new Address(DIRTY_STREET, CITY, STATE);
        assertEquals("Output should match input", DIRTY_STREET, address.getRawStreet());
    }

    @Test
    public void getStateHappy () {
        Address address = new Address(CLEAN_STREET, CITY, STATE);
        assertEquals("Output should match input", STATE, address.getState());
    }

    @Test
    public void getCityHappy () {
        Address address = new Address(DIRTY_STREET, CITY, STATE);
        assertEquals("Output should match input", CITY, address.getCity());
    }
}
