package ru.itmo.p3114.s312198.util.command.actions;

import ru.itmo.p3114.s312198.collection.StudyGroup;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class RemoveAnyByTransferredStudents extends AbstractCommand {
    private final LinkedHashSet<StudyGroup> studyGroups;

    public RemoveAnyByTransferredStudents(LinkedHashSet<StudyGroup> studyGroups) {
        setCommand("remove_any_by_transferred_students");
        this.studyGroups = studyGroups;
    }

    public RemoveAnyByTransferredStudents(ArrayList<String> arguments, LinkedHashSet<StudyGroup> studyGroups) {
        super("remove_any_by_transferred_students", arguments);
        this.studyGroups = studyGroups;
    }

    @Override
    public Status execute() {
        if (getArguments() == null || getArguments().size() != 1) {
            status = Status.INCORRECT_ARGUMENTS;
            return Status.INCORRECT_ARGUMENTS;
        } else {
            for (StudyGroup studyGroup : studyGroups) {
                if (studyGroup.getTransferredStudents() == Integer.parseInt(arguments.get(0))) {
                    studyGroups.remove(studyGroup);
                    status = Status.OK;
                    return Status.OK;
                }
            }

            status = Status.OK;
            return Status.OK;
        }
    }
}