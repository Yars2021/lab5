package ru.itmo.p3114.s312198.util.command.actions;

import ru.itmo.p3114.s312198.util.CommandOutput;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Shows 5 last used commands with no arguments
 */
public class History extends AbstractCommand {
    private final LinkedList<AbstractCommand> commands;
    private final int length = 5;

    /**
     * Creates an instance of History command
     * @param history List of commands
     */
    public History(LinkedList<AbstractCommand> history) {
        super("history", null);
        commands = history;
    }

    /**
     * Executes the History command
     * @return Status
     */
    @Override
    public CommandOutput execute() {
        if (commands == null) {
            status.setStatus(Status.FAILED);
            status.setOutput(null);
            return status;
        } else {
            ArrayList<String> output = new ArrayList<>();

            if (commands.isEmpty()) {
                output.add("History is empty");
            } else {
                for (AbstractCommand command : commands) {
                    output.add(command.getCommand() + " [" + command.getStatusOut().getStatus() + "]");
                }
            }

            status.setStatus(Status.OK);
            status.setOutput(output);
            return status;
        }
    }
}
