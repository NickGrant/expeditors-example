package org.nickgrant.example.models;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.nickgrant.example.Main;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Household {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private final Address address;

    private final ArrayList<Occupant> occupants = new ArrayList<>();

    public Household (Address address) {
        this.address = address;
    }

    /**
     * Add a new Occupant to the household, the occupant address must match the household address
     * @param member The occupant to add to the household
     */
    public void addOccupant (Occupant member) {
        if (address.equals(member.getAddress())) {
            occupants.add(member);
        } else {
            LOGGER.debug("Household.addOccupant :: Address mismatch. Household: {}, Occupant: {}", toString(), member.toString());
        }
    }

    /**
     * Get all occupants of household
     * @return Occupants of household
     */
    public ArrayList<Occupant> getOccupants () {
        return occupants;
    }

    /**
     * Get adult occupants of household
     * @return Filtered ArrayList containing only adult occupants
     */
    public ArrayList<Occupant> getAdults () {
        return occupants
                .stream()
                .filter(Occupant::isAdult)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public String toString () {
        return String.format("%s - %x", address.toMatchString(), occupants.size());
    }
}
