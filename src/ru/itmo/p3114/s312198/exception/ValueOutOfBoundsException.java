package ru.itmo.p3114.s312198.exception;

/**
 * An exception, which can be thrown, when the Value is out of it`s bounds
 */
public class ValueOutOfBoundsException extends Exception {
    public ValueOutOfBoundsException(String message) {
        super("Value out of bounds: " + message);
    }
}
