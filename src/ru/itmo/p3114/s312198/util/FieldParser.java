package ru.itmo.p3114.s312198.util;

import ru.itmo.p3114.s312198.collection.Color;
import ru.itmo.p3114.s312198.collection.Coordinates;
import ru.itmo.p3114.s312198.collection.Country;
import ru.itmo.p3114.s312198.collection.FormOfEducation;
import ru.itmo.p3114.s312198.collection.Location;
import ru.itmo.p3114.s312198.exception.ValueOutOfBoundsException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Parses Strings into StudyGroup fields and validates them
 */
public class FieldParser {
    /**
     * Parses name
     * @param name Input
     * @return Name or null if the Input is invalid
     */
    public static String parseName(String name) {
        if (name == null || "".equals(name)) {
            return null;
        } else {
            return name;
        }
    }

    /**
     * Parses StudyGroup Coordinates
     * @param coords Input
     * @return Coordinates or null if the Input is invalid
     */
    public static Coordinates parseCoordinates(String coords) {
        String[] components = coords.split("\\s+?");
        if (components.length != 2) {
            return null;
        } else {
            long x = Long.parseLong(components[0]);
            double y = Double.parseDouble(components[1]);
            if (y > 426) {
                return null;
            } else {
                return new Coordinates(x, y);
            }
        }
    }

    /**
     * Parses date
     * @param date Input
     * @return A date or null if the Input is invalid
     */
    public static LocalDate parseDate(String date) {
        try {
            return LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeException dte) {
            return null;
        }
    }

    /**
     * Parses students count
     * @param studentsCount Input
     * @return Students count or null if the Input is invalid
     */
    public static Integer parseStudentsCount(String studentsCount) {
        try {
            int count = Integer.parseInt(studentsCount);
            if (count <= 0) {
                return null;
            } else {
                return count;
            }
        } catch (NumberFormatException nfe) {
            return null;
        }
    }

    /**
     * Parses the shouldBeExpelled field
     * @param shouldBeExpelled Input
     * @return int value
     * @throws ValueOutOfBoundsException Thrown, when the value of Input is out of bounds
     * or if it`s not a String
     */
    public static int parseShouldBeExpelled(String shouldBeExpelled) throws ValueOutOfBoundsException {
        try {
            int expelled = Integer.parseInt(shouldBeExpelled);
            if (expelled <= 0) {
                throw new ValueOutOfBoundsException("ShouldBeExpelled should be greater than 0. Current value is " + expelled);
            } else {
                return expelled;
            }
        } catch (NumberFormatException nfe) {
            throw new ValueOutOfBoundsException("Incorrect value format");
        }
    }

    /**
     * Parses the transferredStudents field
     * @param transferredStudents Input
     * @return int value
     * @throws ValueOutOfBoundsException Thrown, when the value of Input is out of bounds
     * or if it`s not a number
     */
    public static int parseTransferredStudents(String transferredStudents) throws ValueOutOfBoundsException {
        try {
            int transferred = Integer.parseInt(transferredStudents);
            if (transferred <= 0) {
                throw new ValueOutOfBoundsException("TransferredStudents should be greater than 0. Current value is " + transferred);
            } else {
                return transferred;
            }
        } catch (NumberFormatException nfe) {
            throw new ValueOutOfBoundsException("Incorrect value format");
        }
    }

    /**
     * Parses for of education
     * @param formOfEducation Input
     * @return FormOfEducation or null id the Input is invalid
     */
    public static FormOfEducation parseFormOfEducation(String formOfEducation) {
        String form = formOfEducation.toUpperCase(Locale.ROOT);
        try {
            return FormOfEducation.valueOf(form);
        } catch (IllegalArgumentException illegalArgumentException) {
            return null;
        }
    }

    /**
     * Parses admin`s height
     * @param height Input
     * @return long value
     * @throws ValueOutOfBoundsException Thrown, when the Input value is out of bounds or
     * is it`s not a number
     */
    public static long parseHeight(String height) throws ValueOutOfBoundsException {
        try {
            long h = Long.parseLong(height);
            if (h <= 0) {
                throw new ValueOutOfBoundsException("Height should be greater than 0. Current value is " + height);
            } else {
                return h;
            }
        } catch (NumberFormatException nfe) {
            throw new ValueOutOfBoundsException("Incorrect value format");
        }
    }

    /**
     * Parses hair color
     * @param color Input
     * @return Color or null if the Input is invalid
     */
    public static Color parseHairColor(String color) {
        String clr = color.toUpperCase(Locale.ROOT);
        try {
            return Color.valueOf(clr);
        } catch (IllegalArgumentException illegalArgumentException) {
            return null;
        }
    }

    /**
     * Parses Nationality
     * @param country Input
     * @return Country or null if the Input is invalid
     */
    public static Country parseNationality(String country) {
        String c = country.toUpperCase(Locale.ROOT);
        try {
            return Country.valueOf(c);
        } catch (IllegalArgumentException illegalArgumentException) {
            return null;
        }
    }

    /**
     * Parses Location coordinates
     * @param locC Input
     * @return An instance of a nameless Location
     */
    public static Location parseLocationCoords(String locC) {
        String[] components = locC.split("\\s+?");
        if (components.length != 3) {
            return null;
        } else {
            try {
                float x = Float.parseFloat(components[0]);
                float y = Float.parseFloat(components[1]);
                float z = Float.parseFloat(components[2]);
                return new Location(x, y, z);
            } catch (NumberFormatException nfe) {
                return null;
            }
        }
    }
}
