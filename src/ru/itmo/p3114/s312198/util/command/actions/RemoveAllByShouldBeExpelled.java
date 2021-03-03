package ru.itmo.p3114.s312198.util.command.actions;

import ru.itmo.p3114.s312198.collection.StudyGroup;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class RemoveAllByShouldBeExpelled extends AbstractCommand {
    private final LinkedHashSet<StudyGroup> studyGroups;


    public RemoveAllByShouldBeExpelled(LinkedHashSet<StudyGroup> studyGroups) {
        setCommand("remove_all_by_should_be_expelled");
        this.studyGroups = studyGroups;
    }

    public RemoveAllByShouldBeExpelled(ArrayList<String> arguments, LinkedHashSet<StudyGroup> studyGroups) {
        super("remove_all_by_should_be_expelled", arguments);
        this.studyGroups = studyGroups;
    }

    @Override
    public Status execute() {
        if (getArguments() == null || getArguments().size() != 1) {
            status = Status.INCORRECT_ARGUMENTS;
            return Status.INCORRECT_ARGUMENTS;
        } else {
            studyGroups.removeIf(sg -> sg.getShouldBeExpelled() == Integer.parseInt(arguments.get(0)));

            status = Status.OK;
            return Status.OK;
        }
    }
}
