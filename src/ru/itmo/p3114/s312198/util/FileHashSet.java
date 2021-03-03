package ru.itmo.p3114.s312198.util;

import java.util.LinkedHashSet;

public class FileHashSet {
    private static final LinkedHashSet<String> files = new LinkedHashSet<>();

    public static void add(String name) {
        files.add(name);
    }

    public static boolean contains(String name) {
        return files.contains(name);
    }

    public static void remove(String name) {
        files.remove(name);
    }

    public static void print() {
        for (String item : files) {
            System.out.println(item);
        }
    }
}
