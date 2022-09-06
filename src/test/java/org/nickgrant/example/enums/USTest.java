package org.nickgrant.example.enums;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class USTest {
    
    private static String VALID_NAME = "Washington";
    private static String VALID_ABBREVIATION = "WA";

    private static String CASING_NAME = "WASHINGTON";
    private static String CASING_ABBREVIATION = "wa";

    private static String INVALID_NAME = "Zanzabar";
    private static String INVALID_ABBREVATION = "ZA";
    
    @Test
    public void parseHappy () {
        US name = US.parse(VALID_NAME);
        US abbrev = US.parse(VALID_ABBREVIATION);

        assertEquals("Parsing by name should match", VALID_NAME, name.getName());
        assertEquals("Parsing by abbreviation should match", VALID_ABBREVIATION, abbrev.getAbbreviation());
    }

    @Test
    public void parseCasing () {
        US name = US.parse(CASING_NAME);
        US abbrev = US.parse(CASING_ABBREVIATION);

        assertEquals("Parsing by name should match", VALID_NAME, name.getName());
        assertEquals("Parsing by abbreviation should match", VALID_ABBREVIATION, abbrev.getAbbreviation());
    }

    @Test
    public void parseInvalid () {
        US name = US.parse(INVALID_NAME);
        US abbrev = US.parse(INVALID_ABBREVATION);

        assertNull("Parsing with an invalid name should return null", name);
        assertNull("Parsing with an invalid abbreviation should return null", abbrev);
    }

    @Test
    public void parseNull () {
        US nullString = US.parse(null);
        assertNull("Parsing with null should return null", nullString);
    }
}
