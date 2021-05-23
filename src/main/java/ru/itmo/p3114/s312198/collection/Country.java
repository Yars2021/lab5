package ru.itmo.p3114.s312198.collection;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Country enum. Supported values:
 *     UNDEFINED,
 *     AUSTRALIA,
 *     BELARUS,
 *     GABON,
 *     KAZAKHSTAN,
 *     KOREA_NORTH,
 *     KOREA_SOUTH,
 *     RUSSIA,
 *     SENEGAL,
 *     SERBIA,
 *     VIETNAM,
 *     ZIMBABWE;
 */
public enum Country implements Serializable {
    UNDEFINED(0),
    AUSTRALIA(1),
    BELARUS(2),
    GABON(3),
    KAZAKHSTAN(4),
    KOREA_NORTH(5),
    KOREA_SOUTH(6),
    RUSSIA(7),
    SENEGAL(8),
    SERBIA(9),
    VIETNAM(10),
    ZIMBABWE(11);

    private final int value;
    private static final HashMap<Integer, Country> map = new HashMap<>();

    Country(int value) {
        this.value = value;
    }

    static {
        for (Country country : Country.values()) {
            map.put(country.value, country);
        }
    }

    public static Country countryByID(int country) {
        return map.get(country);
    }

    public int getValue() {
        return value;
    }

    /**
     * Transforms enum value into a string
     * @return Enum value transformed into a string
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Shows all possible values
     * @return String with all possible values
     */
    public static String getValues() {
        String s = "";
        for (Country country : Country.values()) {
            if (country != Country.UNDEFINED) {
                s += (country.toString() + " ");
            }
        }
        s = s.trim();
        return s;
    }
}