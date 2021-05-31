package ru.itmo.p3114.s312198.command.actions;

import ru.itmo.p3114.s312198.collection.Person;
import ru.itmo.p3114.s312198.collection.StudyGroup;
import ru.itmo.p3114.s312198.command.actions.marker.CollectionInteracting;
import ru.itmo.p3114.s312198.command.CommandOutput;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.LinkedList;

/**
 * Prints all the group admins sorted from the smallest to the biggest value
 */
public class PrintFieldAscendingGroupAdmin extends AbstractCommand implements CollectionInteracting {
    private LinkedHashSet<StudyGroup> studyGroups;

    /**
     * Creates an instance of PrintFieldAscendingGroupAdmin command
     * @param studyGroups The collection, it`s working with
     */
    public PrintFieldAscendingGroupAdmin(LinkedHashSet<StudyGroup> studyGroups) {
        setCommand("print_field_ascending_group_admin");
        this.studyGroups = studyGroups;
    }

    /**
     * Creates an instance of PrintFieldAscendingGroupAdmin command
     * @param arguments Arguments
     * @param studyGroups The collection, it`s working with
     */
    public PrintFieldAscendingGroupAdmin(ArrayList<String> arguments, LinkedHashSet<StudyGroup> studyGroups) {
        super("print_field_ascending_group_admin", arguments);
        this.studyGroups = studyGroups;
    }

    public void setTargetCollection(LinkedHashSet<StudyGroup> studyGroups) {
        this.studyGroups = studyGroups;
    }

    /**
     * Executes the PrintFieldAscendingGroupAdmin command
     * @return Status
     */
    @Override
    public CommandOutput execute() {
        if (getArguments() != null) {
            status.setStatus(Status.INCORRECT_ARGUMENTS);
            status.setOutput(null);
            return status;
        } else {
            LinkedList<Person> admins = new LinkedList<>();
            ArrayList<String> output = new ArrayList<>();

            for (StudyGroup studyGroup : studyGroups) {
                if (studyGroup.getGroupAdmin() != null) {
                    admins.add(studyGroup.getGroupAdmin());
                }
            }

            if (admins.isEmpty()) {
                output.add("No group admins found");
                status.setStatus(Status.OK);
                return status;
            } else {
                admins.sort(Comparator.naturalOrder());

                admins.forEach(person -> {
                    output.add(person.toReadableLine());
                    output.add("");
                });
            }

            status.setStatus(Status.OK);
            status.setOutput(output);
            return status;
        }
    }
}
