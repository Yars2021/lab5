package ru.itmo.p3114.s312198.command.actions;

import ru.itmo.p3114.s312198.collection.StudyGroup;
import ru.itmo.p3114.s312198.command.actions.marker.CollectionInteracting;
import ru.itmo.p3114.s312198.command.CommandOutput;
import ru.itmo.p3114.s312198.command.actions.marker.DatabaseInteracting;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * Removes all the elements with current shouldBeExpelled value
 */
public class RemoveAllByShouldBeExpelled extends AbstractCommand implements CollectionInteracting, DatabaseInteracting {
    private LinkedHashSet<StudyGroup> studyGroups;

    /**
     * Creates an instance of public class RemoveAllByShouldBeExpelled extends AbstractCommand { command
     * @param studyGroups The collection, it`s working with
     */
    public RemoveAllByShouldBeExpelled(LinkedHashSet<StudyGroup> studyGroups) {
        setCommand("remove_all_by_should_be_expelled");
        this.studyGroups = studyGroups;
    }

    /**
     * Creates an instance of public class RemoveAllByShouldBeExpelled extends AbstractCommand { command
     * @param arguments Arguments
     * @param studyGroups The collection, it`s working with
     */
    public RemoveAllByShouldBeExpelled(ArrayList<String> arguments, LinkedHashSet<StudyGroup> studyGroups) {
        super("remove_all_by_should_be_expelled", arguments);
        this.studyGroups = studyGroups;
    }

    public void setTargetCollection(LinkedHashSet<StudyGroup> studyGroups) {
        this.studyGroups = studyGroups;
    }

    /**
     * Executes the RemoveAllByShouldBeExpelled command
     * @return Status
     */
    @Override
    public CommandOutput execute() {
        if (getArguments() == null || getArguments().size() != 1) {
            status.setStatus(Status.INCORRECT_ARGUMENTS);
            status.setOutput(null);
            return status;
        } else {
            studyGroups.removeIf(sg -> sg.getShouldBeExpelled() == Integer.parseInt(arguments.get(0)));

            status.setStatus(Status.OK);
            status.setOutput(null);
            return status;
        }
    }
}
