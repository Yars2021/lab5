package ru.itmo.p3114.s312198.util.command.actions;

import ru.itmo.p3114.s312198.collection.StudyGroup;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * Shows some information about the collection
 */
public class Info extends AbstractCommand {
    private final LinkedHashSet<StudyGroup> studyGroups;

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

    /**
     * Executes the Info command
     * @return Status
     */
    @Override
    public Status execute() {
        if (getArguments() != null) {
            status = Status.INCORRECT_ARGUMENTS;
            return Status.INCORRECT_ARGUMENTS;
        } else {
            if (studyGroups == null) {
                status = Status.FAILED;
                return Status.FAILED;
            } else {
                System.out.println("Collection type: Linked hash set of study groups");
                System.out.println("Size: " + studyGroups.size());
                if (studyGroups.isEmpty()) {
                    System.out.println("Collection is empty");
                } else {
                    System.out.println("Elements in CSV format: ");
                    for (StudyGroup studyGroup : studyGroups) {
                        System.out.println(studyGroup.toCSVLine());
                    }
                }
                status = Status.OK;
                return Status.OK;
            }
        }
    }
}
