package ru.itmo.p3114.s312198.command.actions;

import ru.itmo.p3114.s312198.collection.StudyGroup;
import ru.itmo.p3114.s312198.command.CommandOutput;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * Abstract command class.
 */
abstract public class AbstractCommand implements Serializable {
    protected String command = "";
    protected ArrayList<String> arguments;
    protected CommandOutput status = new CommandOutput(Status.UNDEFINED, null);
    protected LinkedHashSet<StudyGroup> studyGroups;

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

    public void setTargetCollection(LinkedHashSet<StudyGroup> studyGroups) {
        this.studyGroups = studyGroups;
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
    public CommandOutput getStatusOut() {
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
    abstract public CommandOutput execute();
}
