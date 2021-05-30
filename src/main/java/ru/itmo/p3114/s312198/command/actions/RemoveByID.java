package ru.itmo.p3114.s312198.command.actions;

import ru.itmo.p3114.s312198.collection.StudyGroup;
import ru.itmo.p3114.s312198.command.CollectionInteracting;
import ru.itmo.p3114.s312198.command.CommandOutput;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * Removes an element by ID
 */
public class RemoveByID extends AbstractCommand implements CollectionInteracting {
    private LinkedHashSet<StudyGroup> studyGroups;

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

    public void setTargetCollection(LinkedHashSet<StudyGroup> studyGroups) {
        this.studyGroups = studyGroups;
    }

    /**
     * Executes the RemoveByID command
     * @return Status (OK, FAILED if the element does not exist, INCORRECT_ARGUMENTS)
     */
    @Override
    public CommandOutput execute() {
        if (getArguments() == null || getArguments().size() != 1) {
            status.setStatus(Status.INCORRECT_ARGUMENTS);
            status.setOutput(null);
            return status;
        } else {
            for (StudyGroup studyGroup : studyGroups) {
                if (studyGroup.getId() == Long.parseLong(getArguments().get(0))) {
                    studyGroups.remove(studyGroup);
                    status.setStatus(Status.OK);
                    status.setOutput(null);
                    return status;
                }
            }

            status.setStatus(Status.FAILED);
            status.setOutput(null);
            return status;
        }
    }
}
