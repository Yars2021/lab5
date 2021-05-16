package ru.itmo.p3114.s312198.util;

import java.util.LinkedHashSet;

/**
 * Set of files, which are currently used by scripts.
 * Prevents stack overflow
 */
public class FileHashSet {
    private static final LinkedHashSet<String> files = new LinkedHashSet<>();

    /**
     * Adds a file to the Set
     * @param name File name
     */
    public static void add(String name) {
        files.add(name);
    }

    /**
     * Checks if the file is in use
     * @param name File name
     * @return true or false
     */
    public static boolean contains(String name) {
        return files.contains(name);
    }

    /**
     * Removes file from the set
     * @param name File name
     */
    public static void remove(String name) {
        files.remove(name);
    }

    /**
     * Prints all the files, which are currently in use
     */
    public static void print() {
        for (String item : files) {
            System.out.println(item);
        }
    }
}
