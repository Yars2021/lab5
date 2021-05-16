package ru.itmo.p3114.s312198.util;

import ru.itmo.p3114.s312198.util.command.actions.Status;

import java.io.Serializable;
import java.util.ArrayList;

public class CommandOutput implements Serializable {
    private Status status;
    private ArrayList<String> output;

    public CommandOutput(Status status, ArrayList<String> output) {
        this.status = status;
        this.output = output;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setOutput(ArrayList<String> output) {
        this.output = output;
    }

    public Status getStatus() {
        return status;
    }

    public ArrayList<String> getOutput() {
        return output;
    }
}
