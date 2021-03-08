package ru.itmo.p3114.s312198.collection;

/**
 * Form of education enum. Supported values:
 *     UNDEFINED,
 *     DISTANCE_EDUCATION,
 *     FULL_TIME_EDUCATION,
 *     EVENING_CLASSES;
 */
public enum FormOfEducation {
    UNDEFINED,
    DISTANCE_EDUCATION,
    FULL_TIME_EDUCATION,
    EVENING_CLASSES;

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
