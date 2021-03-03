package ru.itmo.p3114.s312198.exception;

public class IncorrectLineFormat extends Throwable {
    public IncorrectLineFormat(String line) {
        super(line);
    }
}
