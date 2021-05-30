package ru.itmo.p3114.s312198.command.actions;

import ru.itmo.p3114.s312198.collection.StudyGroup;
import ru.itmo.p3114.s312198.file.DataFileWriter;
import ru.itmo.p3114.s312198.command.CommandOutput;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * Saves the collection
 */
@Deprecated
public class Save extends AbstractCommand {
    private LinkedHashSet<StudyGroup> studyGroups;

    /**
     * Creates an instance of Save command
     * @param studyGroups The collection it`s working with
     */
    public Save(LinkedHashSet<StudyGroup> studyGroups) {
        setCommand("save");
        this.studyGroups = studyGroups;
    }

    /**
     * Creates an instance of Save command
     * @param arguments Arguments
     * @param studyGroups The collection it`s working with
     */
    public Save(ArrayList<String> arguments, LinkedHashSet<StudyGroup> studyGroups) {
        super("save", arguments);
        this.studyGroups = studyGroups;
    }

    public void setTargetCollection(LinkedHashSet<StudyGroup> studyGroups) {
        this.studyGroups = studyGroups;
    }

    /**
     * Executes the Save command
     * @return Status
     */
    @Override
    public CommandOutput execute() {
        if (getArguments() == null || getArguments().size() != 1) {
            status.setStatus(Status.INCORRECT_ARGUMENTS);
            status.setOutput(null);
            return status;
        } else {
            DataFileWriter writer = new DataFileWriter();

            boolean state = writer.safeWrite(arguments.get(0).trim(), studyGroups);

            if (!state) {
                status.setStatus(Status.FAILED);
                status.setOutput(null);
                return status;
            }

            status.setStatus(Status.OK);
            status.setOutput(null);
            return status;
        }
    }
}
