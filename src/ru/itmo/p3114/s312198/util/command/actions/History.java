package ru.itmo.p3114.s312198.util.command.actions;

import java.util.LinkedList;

public class History extends AbstractCommand {
    private final LinkedList<AbstractCommand> commands;
    private final int length = 5;

    public History(LinkedList<AbstractCommand> history) {
        super("history", null);
        commands = history;
    }

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
