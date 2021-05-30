package ru.itmo.p3114.s312198.transmission.structures.packet;

import ru.itmo.p3114.s312198.command.CommandOutput;

import java.io.Serializable;

public class ServerDataPacket implements Serializable {
    private final String serverMessage;
    private final CommandOutput commandOutput;
    private final boolean executed;

    public ServerDataPacket(String serverMessage, CommandOutput commandOutput, boolean executed) {
        this.serverMessage = serverMessage;
        this.commandOutput = commandOutput;
        this.executed = executed;
    }

    public String getServerMessage() {
        return serverMessage;
    }

    public CommandOutput getCommandOutput() {
        return commandOutput;
    }

    public boolean isExecuted() {
        return executed;
    }
}
