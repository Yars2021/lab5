package ru.itmo.p3114.s312198.collection;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Color enum. Supported values:
 *     UNDEFINED,
 *     BLACK,
 *     BROWN,
 *     RED,
 *     WHITE;
 */
public enum Color implements Serializable {
    UNDEFINED(0),
    BLACK(1),
    BROWN(2),
    RED(3),
    WHITE(4);

    private final int value;
    private static final HashMap<Integer, Color> map = new HashMap<>();

    Color(int value) {
        this.value = value;
    }

    static {
        for (Color color : Color.values()) {
            map.put(color.value, color);
        }
    }

    public static Color colorByID(int color) {
        return map.get(color);
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
        StringBuilder s = new StringBuilder();
        for (Color color : Color.values()) {
            if (color != Color.UNDEFINED) {
                s.append(color.toString()).append(" ");
            }
        }
        s = new StringBuilder(s.toString().trim());
        return s.toString();
    }
}