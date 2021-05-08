package ru.itmo.p3114.s312198.util.command.actions;

import ru.itmo.p3114.s312198.collection.StudyGroup;
import ru.itmo.p3114.s312198.util.CommandOutput;
import ru.itmo.p3114.s312198.util.FileHashSet;
import ru.itmo.p3114.s312198.util.command.CommandLineProcessor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Executes a script of these commands
 */
public class ExecuteScript extends AbstractCommand {
    private LinkedHashSet<StudyGroup> studyGroups;

    /**
     * Creates a new instance of ExecuteScript command
     * @param studyGroups The collection, it`s working with
     */
    public ExecuteScript(LinkedHashSet<StudyGroup> studyGroups) {
        setCommand("execute_script");
        this.studyGroups = studyGroups;
    }

    /**
     * Creates a new instance of ExecuteScript command
     * @param arguments Arguments
     * @param studyGroups The collection, it`s working with
     */
    public ExecuteScript(ArrayList<String> arguments, LinkedHashSet<StudyGroup> studyGroups) {
        super("execute_script", arguments);
        this.studyGroups = studyGroups;
    }

    public void setTargetCollection(LinkedHashSet<StudyGroup> studyGroups) {
        this.studyGroups = studyGroups;
    }

    /**
     * Executes the ExecuteScriptCommand
     * @return Status (OK, FAILED if there is a risk of stack overflow, INCORRECT_ARGUMENTS)
     */
    @Override
    public CommandOutput execute() {
        if (getArguments() == null || getArguments().size() != 1) {
            status.setStatus(Status.INCORRECT_ARGUMENTS);
            status.setOutput(null);
            return status;
        } else {
            if (FileHashSet.contains(arguments.get(0))) {
                System.out.println("File \"" + arguments.get(0) + "\" cannot be executed again since it will result in stack overflow.");
                System.out.println("Execution terminated");
                status.setStatus(Status.FAILED);
                status.setOutput(null);
                return status;
            } else {
//                FileHashSet.add(arguments.get(0));
//
//                CommandLineProcessor commandLineProcessor = new CommandLineProcessor(studyGroups);
//
//                List<String> lines;
//                try {
//                    lines = Files.readAllLines(Paths.get(arguments.get(0)), StandardCharsets.UTF_8);
//                } catch (IOException e) {
//                    status = Status.FAILED;
//                    return Status.FAILED;
//                }
//
//                for (int i = 0; i < lines.size(); i++) {
//                    if (lines.get(i).startsWith(":")) {
//                        commandLineProcessor.parseFileInput(lines, i);
//                    }
//                }
//
//                FileHashSet.remove(arguments.get(0));
                status.setStatus(Status.OK);
                status.setOutput(null);
                return status;
            }
        }
    }
}
