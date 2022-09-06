package org.nickgrant.example.models;

public class Occupant {

    public final int ADULT_AGE = 18;

    private final String firstName;

    private final String lastName;

    private final Address address;

    private final int age;

    public Occupant (String firstName, String lastName, Address address, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Address getAddress () {
        return address;
    }

    public int getAge () { return age;}

    public boolean isAdult () {
        return age >= ADULT_AGE;
    }

    @Override
    public String toString () {
        return String.format("%s %s - %s - Age: %d", firstName, lastName, address.toString(), age);
    }
}
