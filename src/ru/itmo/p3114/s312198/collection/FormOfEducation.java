package ru.itmo.p3114.s312198.collection;

public enum FormOfEducation {
    UNDEFINED,
    DISTANCE_EDUCATION,
    FULL_TIME_EDUCATION,
    EVENING_CLASSES;

    @Override
    public String toString() {
        return super.toString();
    }

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
