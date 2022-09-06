package org.nickgrant.example;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.nickgrant.example.enums.US;
import org.nickgrant.example.models.Address;
import org.nickgrant.example.models.HouseholdGroup;
import org.nickgrant.example.models.Occupant;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static final String ERROR_NO_HOUSEHOLDS = "ERROR: Unable to process households. Cannot proceed further.";

    private HouseholdGroup households = new HouseholdGroup();
    private ArrayList<Occupant> entries = new ArrayList<>();

    /**
     * Iterate through households and write each household and each adult occupants as a new line in the console
     */
    protected void render () {
        if (households.getHouseholds().isEmpty()) {
            System.out.println(ERROR_NO_HOUSEHOLDS);
        } else {
            households.getHouseholds().forEach(household -> {
                // Print Household information
                System.out.println(household);

                ArrayList<Occupant> sortedAdults = new ArrayList<>(household.getAdults());

                // Sort adults by lastName, firstName
                sortedAdults.sort(
                        Comparator.comparing(Occupant::getLastName)
                                .thenComparing(Occupant::getFirstName)
                );

                // Print each adult
                sortedAdults.forEach(System.out::println);

                // Print an extra line for readability
                System.out.println();
            });
        }
    }

    /**
     * Process a list of Occupants into a Household Group
     * @param entries
     * @return
     */
    protected HouseholdGroup processEntries (ArrayList<Occupant> entries) {
        return HouseholdGroup.fromOccupants(entries);
    }

    /**
     * To line from CSV into Occupant object
     * @param line The line from the CSV
     * @return Corresponding occupant
     */
    protected Occupant csvToOccupant (String[] line) {
        if(line.length == 6) {
            Address occupantAddress = new Address(line[2], line[3], US.parse(line[4]));
            try {
                Occupant entry = new Occupant(line[0], line[1], occupantAddress, Integer.parseInt(line[5]));
                return entry;
            } catch (NumberFormatException e) {
                LOGGER.debug("Main.csvToOccupant :: NumberFormatException :: Unable to parse age for entry: {}", Arrays.toString(line));
            }
        } else {
            LOGGER.debug("Main.csvToOccupant :: Unable to parse entry: {}", Arrays.toString(line));
        }
        return null;
    }

    /**
     * Get data from csv file
     * @return csv entries as occupants
     */
    protected ArrayList<Occupant> readData () {
        /*
         * Improvement: Accept path as parameter to load external csv files
         */
        ArrayList<Occupant> output = new ArrayList<>();
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("data.csv");

        if (inputStream != null) {
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            CSVReaderBuilder builder = new CSVReaderBuilder(streamReader);
            CSVReader reader = builder.build();
            /*
             * Improvement: opencsv has an annotation system to ease conversion
             */
            try {
                String[] nextLine;
                while ((nextLine = reader.readNext()) != null) {
                    Occupant entry = csvToOccupant(nextLine);
                    if (entry != null) {
                        output.add(entry);
                    }
                }
            } catch (IOException e) {
                LOGGER.error("Main.readData :: IOException :: {}", e.getMessage());
            } catch (CsvValidationException e) {
                LOGGER.error("Main.readData :: CsvValidationExcept :: {}", e.getMessage());
            }
        }

        return output;
    }

    /**
     * Instance initialization function
     */
    protected void run() {
        /*
         * Improvement: Accept arguments as parameter to extend functionality
         */
        entries = readData();

        if(households.getHouseholds().isEmpty()) {
            households = processEntries(entries);
        }

        render();
    }

    public static void main(String[] args) {
        new Main().run();
    }

    /**
     * Set households
     * @param households
     */
    protected void setHouseholds (HouseholdGroup households) {
        this.households = households;
    }
}
