package ru.itmo.p3114.s312198.util.command.actions;

import ru.itmo.p3114.s312198.collection.StudyGroup;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class RemoveByID extends AbstractCommand {
    private final LinkedHashSet<StudyGroup> studyGroups;

    public RemoveByID(LinkedHashSet<StudyGroup> studyGroups) {
        setCommand("remove_by_id");
        this.studyGroups = studyGroups;
    }

    public RemoveByID(ArrayList<String> arguments, LinkedHashSet<StudyGroup> studyGroups) {
        super("remove_by_id", arguments);
        this.studyGroups = studyGroups;
    }

    @Override
    public Status execute() {
        if (getArguments() == null || getArguments().size() != 1) {
            status = Status.INCORRECT_ARGUMENTS;
            return Status.INCORRECT_ARGUMENTS;
        } else {
            for (StudyGroup studyGroup : studyGroups) {
                if (studyGroup.getId() == Long.parseLong(getArguments().get(0))) {
                    studyGroups.remove(studyGroup);
                    status = Status.OK;
                    return Status.OK;
                }
            }

            status = Status.FAILED;
            return Status.FAILED;
        }
    }
}
