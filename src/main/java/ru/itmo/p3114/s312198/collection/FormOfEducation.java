package ru.itmo.p3114.s312198.collection;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Form of education enum. Supported values:
 *     UNDEFINED,
 *     DISTANCE_EDUCATION,
 *     FULL_TIME_EDUCATION,
 *     EVENING_CLASSES;
 */
public enum FormOfEducation implements Serializable {
    UNDEFINED(0),
    DISTANCE_EDUCATION(1),
    FULL_TIME_EDUCATION(2),
    EVENING_CLASSES(3);

    private final int value;
    private static final HashMap<Integer, FormOfEducation> map = new HashMap<>();

    FormOfEducation(int value) {
        this.value = value;
    }

    static {
        for (FormOfEducation formOfEducation : FormOfEducation.values()) {
            map.put(formOfEducation.value, formOfEducation);
        }
    }

    public static FormOfEducation formOfEducationByID(int formOfEducation) {
        return map.get(formOfEducation);
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
        for (FormOfEducation formOfEducation : FormOfEducation.values()) {
            if (formOfEducation != FormOfEducation.UNDEFINED) {
                s += (formOfEducation.toString() + " ");
            }
        }
        s = s.trim();
        return s;
    }
}
