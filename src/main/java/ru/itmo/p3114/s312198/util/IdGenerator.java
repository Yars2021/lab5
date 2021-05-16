package ru.itmo.p3114.s312198.util;

import java.util.LinkedHashSet;

/**
 * Gives a unique ID to a new StudyGroup added to the collection
 */
public class IdGenerator {
    private static long currentId = 0;
    private final static LinkedHashSet<Long> usedIDs = new LinkedHashSet<>();

    private IdGenerator() {}

    /**
     * @return A unique number
     */
    public static long getNextId() {
        usedIDs.add(++currentId);
        return currentId;
    }

    /**
     * Increments current ID value
     */
    public static void incCurrentID() {
        currentId++;
    }

    /**
     * Checks id
     */
    public static boolean checkID(long id) {
        return usedIDs.contains(id);
    }

    /**
     * Adds id
     */
    public static void addId(long id) {
        usedIDs.add(id);
    }

    /**
     * Clears ID list
     */
    public static void clearIDs() {
        usedIDs.clear();
    }
}
