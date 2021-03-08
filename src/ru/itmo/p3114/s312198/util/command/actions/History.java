package ru.itmo.p3114.s312198.util.command.actions;

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
    public Status execute() {
        if (commands == null) {
            status = Status.FAILED;
            return Status.FAILED;
        } else {
            if (commands.isEmpty()) {
                System.out.println("History is empty");
            } else {
                for (AbstractCommand command : commands) {
                    System.out.println(command.getCommand() + " [" + command.getStatus() + "]");
                }
            }
            status = Status.OK;
            return Status.OK;
        }
    }
}
