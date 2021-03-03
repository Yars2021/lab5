package ru.itmo.p3114.s312198.util.command.actions;

import ru.itmo.p3114.s312198.collection.Person;
import ru.itmo.p3114.s312198.collection.StudyGroup;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class PrintFieldAscendingGroupAdmin extends AbstractCommand {
    private final LinkedHashSet<StudyGroup> studyGroups;

    public PrintFieldAscendingGroupAdmin(LinkedHashSet<StudyGroup> studyGroups) {
        setCommand("print_field_ascending_group_admin");
        this.studyGroups = studyGroups;
    }

    public PrintFieldAscendingGroupAdmin(ArrayList<String> arguments, LinkedHashSet<StudyGroup> studyGroups) {
        super("print_field_ascending_group_admin", arguments);
        this.studyGroups = studyGroups;
    }

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
