package ru.itmo.p3114.s312198.collection;

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
public enum Country {
    UNDEFINED,
    AUSTRALIA,
    BELARUS,
    GABON,
    KAZAKHSTAN,
    KOREA_NORTH,
    KOREA_SOUTH,
    RUSSIA,
    SENEGAL,
    SERBIA,
    VIETNAM,
    ZIMBABWE;

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