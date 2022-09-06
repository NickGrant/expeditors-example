package org.nickgrant.example.enums;

/**
 * Enum to handle US states
 */
public enum US {

    ALABAMA("Alabama","AL"),
    ALASKA("Alaska","AK"),
    ARIZONA("Arizona","AZ"),
    ARKANSAS("Arkansas","AR"),
    CALIFORNIA("California","CA"),
    COLORADO("Colorado","CO"),
    CONNECTICUT("Connecticut","CT"),
    DELAWARE("Delaware","DE"),
    DISTRICT_OF_COLUMBIA("District of Columbia","DC"),
    FLORIDA("Florida","FL"),
    GEORGIA("Georgia","GA"),
    HAWAII("Hawaii","HI"),
    IDAHO("Idaho","ID"),
    ILLINOIS("Illinois","IL"),
    INDIANA("Indiana","IN"),
    IOWA("Iowa","IA"),
    KANSAS("Kansas","KS"),
    KENTUCKY("Kentucky","KY"),
    LOUISIANA("Louisiana","LA"),
    MAINE("Maine","ME"),
    MARYLAND("Maryland","MD"),
    MASSACHUSETTS("Massachusetts","MA"),
    MICHIGAN("Michigan","MI"),
    MINNESOTA("Minnesota","MN"),
    MISSISSIPPI("Mississippi","MS"),
    MISSOURI("Missouri","MO"),
    MONTANA("Montana","MT"),
    NEBRASKA("Nebraska","NE"),
    NEVADA("Nevada","NV"),
    NEW_HAMPSHIRE("New Hampshire","NH"),
    NEW_JERSEY("New Jersey","NJ"),
    NEW_MEXICO("New Mexico","NM"),
    NEW_YORK("New York","NY"),
    NORTH_CAROLINA("North Carolina","NC"),
    NORTH_DAKOTA("North Dakota","ND"),
    OHIO("Ohio","OH"),
    OKLAHOMA("Oklahoma","OK"),
    OREGON("Oregon","OR"),
    PENNSYLVANIA("Pennsylvania","PA"),
    RHODE_ISLAND("Rhode Island","RI"),
    SOUTH_CAROLINA("South Carolina","SC"),
    SOUTH_DAKOTA("South Dakota","SD"),
    TENNESSEE("Tennessee","TN"),
    TEXAS("Texas","TX"),
    UTAH("Utah","UT"),
    VERMONT("Vermont","VT"),
    VIRGINIA("Virginia","VA"),
    WASHINGTON("Washington","WA"),
    WEST_VIRGINIA("West Virginia","WV"),
    WISCONSIN("Wisconsin","WI"),
    WYOMING("Wyoming","WY"),
    PUERTO_RICO("Puerto Rico","PR");

    String name;
    String abbreviation;

    US (String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }

    /**
     * The full, unabbreviated name of this state.
     */
    public String getName() {
        return this.name;
    }

    /**
     * The ANSI abbreviated name of this state, e.g. "NY", or "WY".
     */
    public String getAbbreviation() {
        return this.abbreviation;
    }

    /**
     * Parse string input to enum. Accepts either name or abbreviation, ignores case.
     *
     * @param input String to parse
     * @return The parsed US state, or null on failure.
     */
    public static US parse(String input) {
        if (null == input) {
            return null;
        }
        input = input.trim();
        for (US state : values()) {
            if (state.name.equalsIgnoreCase(input) || state.abbreviation.equalsIgnoreCase(input))  {
                return state;
            }
        }
        return null;
    }
}
