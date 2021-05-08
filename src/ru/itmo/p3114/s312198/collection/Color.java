package ru.itmo.p3114.s312198.collection;

import java.io.Serializable;

/**
 * Color enum. Supported values:
 *     UNDEFINED,
 *     BLACK,
 *     BROWN,
 *     RED,
 *     WHITE;
 */
public enum Color implements Serializable {
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
