package org.nickgrant.example.models;

import org.nickgrant.example.enums.US;

public class Address {

    private final String rawStreet;

    private final String street;

    private final String city;

    private final US state;

    /**
     * Normalizes street address for better matching
     * @param value Raw street address to normalize
     * @return The normalized street address
     */
    private String normalizeStreet (String value) {
        return value.replaceAll("[,.]", "").trim();
    }

    public Address (String street, String city, US state) {
        this.rawStreet = street;
        this.street = normalizeStreet(street);
        this.city = city;
        this.state = state;
    }

    /**
     * Get normalized street address
     * @return street portion of address
     */
    public String getStreet () {
        return street;
    }

    /**
     * Get supplied street address before normalization
     * @return street portion of address
     */
    public String getRawStreet () { return rawStreet; }

    /**
     * Get city portion of address
     * @return city name
     */
    public String getCity () {
        return city;
    }

    /**
     * Get state object for address
     * @return state
     */
    public US getState () {
        return state;
    }

    @Override
    public String toString () {
        return String.format("%s %s, %s", rawStreet, city, state.getAbbreviation());
    }

    public String toMatchString () {
        return String.format("%s %s, %s", street, city, state.getAbbreviation());
    }

    @Override
    public boolean equals (Object obj) {
        return (obj instanceof Address) &&
            this.toMatchString().equalsIgnoreCase(((Address) obj).toMatchString());
    }
}
