package ru.itmo.p3114.s312198.transmission.structures.packet;

import ru.itmo.p3114.s312198.command.actions.AbstractCommand;
import ru.itmo.p3114.s312198.transmission.structures.user.UserSignature;

import java.io.Serializable;

public class ClientDataPacket implements Serializable {
    private final AbstractCommand command;
    private final UserSignature userSignature;

    public ClientDataPacket(AbstractCommand command, UserSignature userSignature) {
        this.command = command;
        this.userSignature = userSignature;
    }

    public AbstractCommand getCommand() {
        return command;
    }

    public UserSignature getUserSignature() {
        return userSignature;
    }
}
