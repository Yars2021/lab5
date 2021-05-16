package ru.itmo.p3114.s312198.util.command.actions;

import ru.itmo.p3114.s312198.collection.StudyGroup;
import ru.itmo.p3114.s312198.util.CommandOutput;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * Clears the collection
 */
public class Clear extends AbstractCommand {
    private LinkedHashSet<StudyGroup> studyGroups;

    /**
     * Creates a new instance of Clear command
     * @param studyGroups The collection, it`s working with
     */
    public Clear(LinkedHashSet<StudyGroup> studyGroups) {
        setCommand("clear");
        this.studyGroups = studyGroups;
    }

    /**
     * Creates a new instance of Clear command
     * @param arguments Arguments
     * @param studyGroups The collection, it`s working with
     */
    public Clear(ArrayList<String> arguments, LinkedHashSet<StudyGroup> studyGroups) {
        super("clear", arguments);
        this.studyGroups = studyGroups;
    }

    public void setTargetCollection(LinkedHashSet<StudyGroup> studyGroups) {
        this.studyGroups = studyGroups;
    }

    /**
     * Executes the Clear command
     * @return Status
     */
    @Override
    public CommandOutput execute() {
        if (getArguments() != null) {
            status.setStatus(Status.INCORRECT_ARGUMENTS);
            status.setOutput(null);
            return status;
        } else {
            studyGroups.clear();
            status.setStatus(Status.OK);
            status.setOutput(null);
            return status;
        }
    }
}
