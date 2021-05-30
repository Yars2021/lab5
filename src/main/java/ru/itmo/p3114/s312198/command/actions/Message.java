package ru.itmo.p3114.s312198.command.actions;

import ru.itmo.p3114.s312198.command.CommandOutput;

import java.util.ArrayList;

public class Message extends AbstractCommand {
    public Message() {
        setCommand("msg");
    }

    public Message(ArrayList<String> arguments) {
        super("msg", arguments);
    }

    @Override
    public CommandOutput execute() {
        ArrayList<String> line = new ArrayList<>();
        StringBuilder ln = new StringBuilder();
        for (String arg : arguments) {
            ln.append(arg);
        }
        line.add(ln.toString());
        return new CommandOutput(Status.OK, line);
    }
}
