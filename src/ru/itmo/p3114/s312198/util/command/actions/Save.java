package ru.itmo.p3114.s312198.util.command.actions;

import ru.itmo.p3114.s312198.collection.StudyGroup;
import ru.itmo.p3114.s312198.file.DataFileWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class Save extends AbstractCommand {
    private final LinkedHashSet<StudyGroup> studyGroups;

    public Save(LinkedHashSet<StudyGroup> studyGroups) {
        setCommand("save");
        this.studyGroups = studyGroups;
    }

    public Save(ArrayList<String> arguments, LinkedHashSet<StudyGroup> studyGroups) {
        super("save", arguments);
        this.studyGroups = studyGroups;
    }

    @Override
    public Status execute() {
        if (getArguments() == null || getArguments().size() != 1) {
            status = Status.INCORRECT_ARGUMENTS;
            return Status.INCORRECT_ARGUMENTS;
        } else {
            DataFileWriter writer = new DataFileWriter();

            try {
                writer.write(arguments.get(0).trim(), studyGroups);
            } catch (IOException ioe) {
                ioe.printStackTrace();
                status = Status.FAILED;
                return Status.FAILED;
            }

            status = Status.OK;
            return Status.OK;
        }
    }
}
