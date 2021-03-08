package ru.itmo.p3114.s312198.exception;

/**
 * An exception, which can be thrown, when a line has incorrect format
 */
public class IncorrectLineFormat extends Throwable {
    public IncorrectLineFormat(String line) {
        super(line);
    }
}
