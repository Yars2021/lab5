package ru.itmo.p3114.s312198.collection;

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

    @Override
    public String toString() {
        return super.toString();
    }

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