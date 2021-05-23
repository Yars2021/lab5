package ru.itmo.p3114.s312198.util.command.actions;

import ru.itmo.p3114.s312198.util.CommandOutput;

import java.util.ArrayList;

/**
 * Terminates the program
 */
public class Exit extends AbstractCommand {
    /**
     * Creates a new instance of Exit command
     */
    public Exit() {
        setCommand("exit");
    }

    /**
     * Creates a new instance of Exit command
     * @param arguments Arguments
     */
    public Exit(ArrayList<String> arguments) {
        super("exit", arguments);
    }

    /**
     * Executes the Exit command
     * @return Status
     */
    @Override
    public CommandOutput execute() {
        if (getArguments() != null) {
            status.setStatus(Status.INCORRECT_ARGUMENTS);
            status.setOutput(null);
        } else {
            ArrayList<String> output = new ArrayList<>();
            status.setStatus(Status.OK);
            output.add("Process finished");
            status.setOutput(output);
        }
        return status;
    }
}
