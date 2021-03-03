package ru.itmo.p3114.s312198.util.command.actions;

import ru.itmo.p3114.s312198.collection.StudyGroup;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class Clear extends AbstractCommand {
    private final LinkedHashSet<StudyGroup> studyGroups;

    public Clear(LinkedHashSet<StudyGroup> studyGroups) {
        setCommand("clear");
        this.studyGroups = studyGroups;
    }

    public Clear(ArrayList<String> arguments, LinkedHashSet<StudyGroup> studyGroups) {
        super("clear", arguments);
        this.studyGroups = studyGroups;
    }

    @Override
    public Status execute() {
        if (getArguments() != null) {
            status = Status.INCORRECT_ARGUMENTS;
            return Status.INCORRECT_ARGUMENTS;
        } else {
            studyGroups.clear();
            status = Status.OK;
            return Status.OK;
        }
    }
}
