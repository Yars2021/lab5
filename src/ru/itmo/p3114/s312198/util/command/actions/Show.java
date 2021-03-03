package ru.itmo.p3114.s312198.util.command.actions;

import ru.itmo.p3114.s312198.collection.StudyGroup;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class Show extends AbstractCommand {
    private final LinkedHashSet<StudyGroup> studyGroups;

    public Show(LinkedHashSet<StudyGroup> studyGroups) {
        setCommand("show");
        this.studyGroups = studyGroups;
    }

    public Show(ArrayList<String> arguments, LinkedHashSet<StudyGroup> studyGroups) {
        super("show", arguments);
        this.studyGroups = studyGroups;
    }

    @Override
    public Status execute() {
        if (getArguments() != null) {
            status = Status.INCORRECT_ARGUMENTS;
            return Status.INCORRECT_ARGUMENTS;
        } else {
            if (studyGroups == null) {
                status = Status.FAILED;
                return Status.FAILED;
            } else {
                if (studyGroups.isEmpty()) {
                    System.out.println("Collection is empty");
                } else {
                    System.out.println("Collection elements:\n");
                    for (StudyGroup studyGroup : studyGroups) {
                        System.out.println(studyGroup.toReadableString());
                    }
                }

                status = Status.OK;
                return Status.OK;
            }
        }
    }
}
