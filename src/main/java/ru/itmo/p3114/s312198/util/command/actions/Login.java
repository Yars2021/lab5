package ru.itmo.p3114.s312198.util.command.actions;

import ru.itmo.p3114.s312198.util.CommandOutput;

import java.util.ArrayList;

public class Login extends AbstractCommand {
    public Login() {
        setCommand("login");
    }

    public Login(ArrayList<String> arguments) {
        super("login", arguments);
    }

    @Override
    public CommandOutput execute() {
        return new CommandOutput(Status.OK, null);
    }
}