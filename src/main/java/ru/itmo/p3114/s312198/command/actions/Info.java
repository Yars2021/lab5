package ru.itmo.p3114.s312198.command.actions;

import ru.itmo.p3114.s312198.collection.StudyGroup;
import ru.itmo.p3114.s312198.command.actions.marker.CollectionInteracting;
import ru.itmo.p3114.s312198.command.CommandOutput;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * Shows some information about the collection
 */
public class Info extends AbstractCommand implements CollectionInteracting {
    private LinkedHashSet<StudyGroup> studyGroups;

    /**
     * Creates a new instance of Info command
     * @param studyGroups The collection, it`s going to work with
     */
    public Info(LinkedHashSet<StudyGroup> studyGroups) {
        setCommand("info");
        this.studyGroups = studyGroups;
    }

    /**
     * Creates a new instance of Info command
     * @param arguments Arguments
     * @param studyGroups The collection, it`s going to work with
     */
    public Info(ArrayList<String> arguments, LinkedHashSet<StudyGroup> studyGroups) {
        super("info", arguments);
        this.studyGroups = studyGroups;
    }

    public void setTargetCollection(LinkedHashSet<StudyGroup> studyGroups) {
        this.studyGroups = studyGroups;
    }

    /**
     * Executes the Info command
     * @return Status
     */
    @Override
    public CommandOutput execute() {
        if (getArguments() != null) {
            status.setStatus(Status.INCORRECT_ARGUMENTS);
            status.setOutput(null);
            return status;
        } else {
            if (studyGroups == null) {
                status.setStatus(Status.FAILED);
                status.setOutput(null);
                return status;
            } else {
                ArrayList<String> output = new ArrayList<>();
                output.add("Collection type: Linked hash set of study groups");
                output.add("Size: " + studyGroups.size());
                if (studyGroups.isEmpty()) {
                    output.add("Collection is empty");
                } else {
                    output.add("Elements in CSV format: ");
                    for (StudyGroup studyGroup : studyGroups) {
                        output.add(studyGroup.toCSVLine());
                    }
                }
                status.setStatus(Status.OK);
                status.setOutput(output);
                return status;
            }
        }
    }
}
