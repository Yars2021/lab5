package ru.itmo.p3114.s312198.util;

/**
 * Gives a unique ID to a new StudyGroup added to the collection
 */
public class IdGenerator {
    private static long currentId = 0;

    private IdGenerator() {};

    /**
     * @return A unique number
     */
    public static long getNextId() {
        return ++currentId;
    }

}
