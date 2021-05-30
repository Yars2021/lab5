package ru.itmo.p3114.s312198.command.actions;

import ru.itmo.p3114.s312198.collection.StudyGroup;
import ru.itmo.p3114.s312198.command.CollectionInteracting;
import ru.itmo.p3114.s312198.command.CommandOutput;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * Removes an element with current transferredStudents value
 */
public class RemoveAnyByTransferredStudents extends AbstractCommand implements CollectionInteracting {
    private LinkedHashSet<StudyGroup> studyGroups;

    /**
     * Creates an instance of RemoveAnyByTransferredStudents command
     * @param studyGroups The collection, it`s working with
     */
    public RemoveAnyByTransferredStudents(LinkedHashSet<StudyGroup> studyGroups) {
        setCommand("remove_any_by_transferred_students");
        this.studyGroups = studyGroups;
    }

    /**
     * Creates an instance of RemoveAnyByTransferredStudents command
     * @param arguments Arguments
     * @param studyGroups The collection, it`s working with
     */
    public RemoveAnyByTransferredStudents(ArrayList<String> arguments, LinkedHashSet<StudyGroup> studyGroups) {
        super("remove_any_by_transferred_students", arguments);
        this.studyGroups = studyGroups;
    }

    public void setTargetCollection(LinkedHashSet<StudyGroup> studyGroups) {
        this.studyGroups = studyGroups;
    }

    /**
     * Executes the RemoveAnyByTransferredStudents command
     * @return Status
     */
    @Override
    public CommandOutput execute() {
        if (getArguments() == null || getArguments().size() != 1) {
            status.setStatus(Status.INCORRECT_ARGUMENTS);
            status.setOutput(null);
            return status;
        } else {
            for (StudyGroup studyGroup : studyGroups) {
                if (studyGroup.getTransferredStudents() == Integer.parseInt(arguments.get(0))) {
                    studyGroups.remove(studyGroup);
                    status.setStatus(Status.OK);
                    status.setOutput(null);
                    return status;
                }
            }

            status.setStatus(Status.OK);
            status.setOutput(null);
            return status;
        }
    }
}