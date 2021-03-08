package ru.itmo.p3114.s312198.util.command.actions;

import java.util.ArrayList;

/**
 * Abstract command class.
 */
abstract public class AbstractCommand {
    protected String command = "";
    protected ArrayList<String> arguments;
    protected Status status = Status.UNDEFINED;

    /**
     * Creates an empty instance of AbstractCommand
     */
    public AbstractCommand() {
    }

    /**
     * Creates an instance of AbstractCommand
     * @param command Command name
     * @param arguments Arguments
     */
    public AbstractCommand(String command, ArrayList<String> arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    /**
     * @return Command name
     */
    public String getCommand() {
        return command;
    }

    /**
     * @return Status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @return Arguments
     */
    public ArrayList<String> getArguments() {
        return arguments;
    }

    /**
     * Sets command name
     * @param command Command name
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * Sets arguments
     * @param arguments Arguments
     */
    public void setArguments(ArrayList<String> arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes the command
     * @return Status
     */
    abstract public Status execute();
}
