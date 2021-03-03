package ru.itmo.p3114.s312198.collection;

/**
 * Color enum. Supported values:
 *      UNDEFINED
 *      BLACK
 *      BROWN
 *      RED
 *      WHITE
 */
public enum Color {
    UNDEFINED,
    BLACK,
    BROWN,
    RED,
    WHITE;

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
        for (Color color : Color.values()) {
            if (color != Color.UNDEFINED) {
                s += (color.toString() + " ");
            }
        }
        s = s.trim();
        return s;
    }
}
