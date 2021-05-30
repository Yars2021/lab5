package ru.itmo.p3114.s312198.command.actions;

import ru.itmo.p3114.s312198.collection.StudyGroup;
import ru.itmo.p3114.s312198.command.CommandOutput;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * Shows all the elements of the collection
 */
public class Show extends AbstractCommand {
    private LinkedHashSet<StudyGroup> studyGroups;

    /**
     * Creates an new instance of Shpw command
     * @param studyGroups The collection it`s working with
     */
    public Show(LinkedHashSet<StudyGroup> studyGroups) {
        setCommand("show");
        this.studyGroups = studyGroups;
    }

    /**
     * Creates an new instance of Shpw command
     * @param arguments Arguments
     * @param studyGroups The collection it`s working with
     */
    public Show(ArrayList<String> arguments, LinkedHashSet<StudyGroup> studyGroups) {
        super("show", arguments);
        this.studyGroups = studyGroups;
    }

    public void setTargetCollection(LinkedHashSet<StudyGroup> studyGroups) {
        this.studyGroups = studyGroups;
    }

    /**
     * Executes the Show command
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
                if (studyGroups.isEmpty()) {
                    output.add("Collection is empty");
                } else {
                    output.add("Collection elements:\n");
                    for (StudyGroup studyGroup : studyGroups) {
                        output.add(studyGroup.toReadableString());
                    }
                }

                status.setStatus(Status.OK);
                status.setOutput(output);
                return status;
            }
        }
    }
}
