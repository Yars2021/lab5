package ru.itmo.p3114.s312198.util.command.actions;

import java.util.ArrayList;

abstract public class AbstractCommand {
    protected String command = "";
    protected ArrayList<String> arguments;
    protected Status status = Status.UNDEFINED;

    public AbstractCommand() {
    }

    public AbstractCommand(String command, ArrayList<String> arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    public String getCommand() {
        return command;
    }

    public Status getStatus() {
        return status;
    }

    public ArrayList<String> getArguments() {
        return arguments;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setArguments(ArrayList<String> arguments) {
        this.arguments = arguments;
    }

    abstract public Status execute();
}
