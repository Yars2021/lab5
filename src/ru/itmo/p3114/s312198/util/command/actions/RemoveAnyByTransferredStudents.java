package ru.itmo.p3114.s312198.util.command.actions;

import ru.itmo.p3114.s312198.collection.StudyGroup;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * Removes an element with current transferredStudents value
 */
public class RemoveAnyByTransferredStudents extends AbstractCommand {
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