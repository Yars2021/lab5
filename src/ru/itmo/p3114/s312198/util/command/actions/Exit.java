package ru.itmo.p3114.s312198.util.command.actions;

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
    public Status execute() {
        if (getArguments() != null) {
            status = Status.INCORRECT_ARGUMENTS;
            return Status.INCORRECT_ARGUMENTS;
        } else {
            System.out.println("Process finished");
            System.exit(0);
            status = Status.OK;
            return Status.OK;
        }
    }
}
