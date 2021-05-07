package ru.itmo.p3114.s312198.util.command.actions;

import ru.itmo.p3114.s312198.collection.Person;
import ru.itmo.p3114.s312198.collection.StudyGroup;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.LinkedList;

/**
 * Prints all the group admins sorted from the smallest to the biggest value
 */
public class PrintFieldAscendingGroupAdmin extends AbstractCommand {
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
    public Status execute() {
        if (getArguments() != null) {
            status = Status.INCORRECT_ARGUMENTS;
            return Status.INCORRECT_ARGUMENTS;
        } else {
            LinkedList<Person> admins = new LinkedList<>();

            for (StudyGroup studyGroup : studyGroups) {
                if (studyGroup.getGroupAdmin() != null) {
                    admins.add(studyGroup.getGroupAdmin());
                }
            }

            if (admins.isEmpty()) {
                System.out.println("No group admins found");
                status = Status.OK;
                return Status.OK;
            } else {
                admins.sort(Comparator.naturalOrder());

                for (Person person : admins) {
                    System.out.println(person.toReadableLine());
                    System.out.println();
                }
            }

            status = Status.OK;
            return Status.OK;
        }
    }
}
