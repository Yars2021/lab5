package ru.itmo.p3114.s312198.util.command.actions;

import ru.itmo.p3114.s312198.collection.StudyGroup;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * Removes an element by ID
 */
public class RemoveByID extends AbstractCommand {
    private final LinkedHashSet<StudyGroup> studyGroups;

    /**
     * Creates an instance of RemoveByID command
     * @param studyGroups The collection, it`s working with
     */
    public RemoveByID(LinkedHashSet<StudyGroup> studyGroups) {
        setCommand("remove_by_id");
        this.studyGroups = studyGroups;
    }

    /**
     * Creates an instance of RemoveByID command
     * @param arguments Arguments
     * @param studyGroups The collection, it`s working with
     */
    public RemoveByID(ArrayList<String> arguments, LinkedHashSet<StudyGroup> studyGroups) {
        super("remove_by_id", arguments);
        this.studyGroups = studyGroups;
    }

    /**
     * Executes the RemoveByID command
     * @return Status (OK, FAILED if the element does not exist, INCORRECT_ARGUMENTS)
     */
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
