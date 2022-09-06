package org.nickgrant.example;

import org.junit.Test;
import org.nickgrant.example.enums.US;
import org.nickgrant.example.models.Address;
import org.nickgrant.example.models.HouseholdGroup;
import org.nickgrant.example.models.Occupant;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MainTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    private final static String SINGLE_HOUSEHOLD_OUTPUT = "123 Main Seattle, WA - 1\n" +
        "Test User - 123 Main Seattle, WA - Age: 34\n" +
        "\n";

    @Test
    public void renderHappy () {
        setConsole();
        Main main = new Main();
        main.setHouseholds(getHouseholds());
        main.render();
        resetConsole();
        assertEquals("Output should show one household", SINGLE_HOUSEHOLD_OUTPUT, outContent.toString());
    }

    @Test
    public void renderEmpty () {
        setConsole();
        Main main = new Main();
        main.setHouseholds(new HouseholdGroup());
        main.render();
        resetConsole();
        assertEquals("Output should be error message", Main.ERROR_NO_HOUSEHOLDS + "\n", outContent.toString());
    }

    @Test
    public void csvToOccupantHappy () {
        Main main = new Main();
        String firstName = "Dave";
        String lastName = "Smith";
        String street = "123 Main st";
        String city = "Seattle";
        String state = "wa";
        String age = "43";
        String[] data = {firstName, lastName, street, city, state, age};
        Occupant occupant = main.csvToOccupant(data);
        Address resultAddress = new Address(street, city, US.WASHINGTON);
        assertEquals("First name should be Dave", firstName, occupant.getFirstName());
        assertEquals("Last name should be Smith", lastName, occupant.getLastName());
        assertEquals("Address should be 123 Main st Seattle, WA", resultAddress, occupant.getAddress());
        assertEquals("Age should be 43", 43, occupant.getAge());
    }

    @Test
    public void csvToOccupantShort () {
        Main main = new Main();
        String[] data = {"Too", "Short"};
        Occupant occupant = main.csvToOccupant(data);
        assertNull("Occupant should be null", occupant);
    }

    @Test
    public void csvToOccupantInvalidAge () {
        Main main = new Main();
        String[] data = {"Dave", "Smith", "123 Main st", "Seattle", "WA", "apples"};
        Occupant occupant = main.csvToOccupant(data);
    }

    @Test
    public void processEntriesHappy () {
        Main main = new Main();
        HouseholdGroup householdGroup = main.processEntries(getEntries());
        assertEquals("Group should have one household", 1, householdGroup.getHouseholds().size());
    }

    @Test
    public void processEntriesEmpty () {
        Main main = new Main();
        HouseholdGroup householdGroup = main.processEntries(new ArrayList<>());
        assertEquals("Group should have no households", 0, householdGroup.getHouseholds().size());
    }

    /**
     * Set console output to test bytestreams
     */
    private void setConsole () {
        //Reset bytestreams
        errContent.reset();
        outContent.reset();

        //set output and error stream
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    /**
     * Reset console output to what it should be
     */
    private void resetConsole () {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    /**
     * Get prepopulated list of Occupants
     * @return list containing an occupant
     */
    private ArrayList<Occupant> getEntries () {
        ArrayList<Occupant> output = new ArrayList<>();
        output.add(new Occupant("Test", "User", new Address("123 Main", "Seattle", US.WASHINGTON), 34));
        return output;
    }

    /**
     * Get prepopulated HouseholdGroup
     * @return Household group containing a single household
     */
    private HouseholdGroup getHouseholds () {
        return HouseholdGroup.fromOccupants(getEntries());
    }
}
