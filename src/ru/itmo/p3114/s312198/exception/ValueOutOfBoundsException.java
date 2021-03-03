package ru.itmo.p3114.s312198.exception;

public class ValueOutOfBoundsException extends Exception {
    public ValueOutOfBoundsException(String message) {
        super("Value out of bounds: " + message);
    }
}
