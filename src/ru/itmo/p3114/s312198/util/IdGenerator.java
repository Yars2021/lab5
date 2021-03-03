package ru.itmo.p3114.s312198.util;

public class IdGenerator {
    private static long currentId = 0;

    private IdGenerator() {};

    public static long getNextId() {
        return ++currentId;
    }

}
