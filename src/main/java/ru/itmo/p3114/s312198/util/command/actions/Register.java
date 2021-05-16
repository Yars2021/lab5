package ru.itmo.p3114.s312198.util.command.actions;

import ru.itmo.p3114.s312198.util.CommandOutput;

import java.util.ArrayList;

public class Register extends AbstractCommand {
    public Register() {
        setCommand("register");
    }

    public Register(ArrayList<String> arguments) {
        super("register", arguments);
    }

    @Override
    public CommandOutput execute() {
        ArrayList<String> output = new ArrayList<>();
        return new CommandOutput(Status.OK, null);
    }
}
