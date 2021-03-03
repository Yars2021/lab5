package ru.itmo.p3114.s312198.util.command.actions;

import java.util.ArrayList;

public class Exit extends AbstractCommand {
    public Exit() {
        setCommand("exit");
    }

    public Exit(ArrayList<String> arguments) {
        super("exit", arguments);
    }

    @Override
    public Status execute() {
        if (getArguments() != null) {
            status = Status.INCORRECT_ARGUMENTS;
            return Status.INCORRECT_ARGUMENTS;
        } else {
            System.exit(0);
            status = Status.OK;
            return Status.OK;
        }
    }
}
