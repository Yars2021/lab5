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
        if (getArguments() == null || getArguments().size() == 0) {
            status.setStatus(Status.INCORRECT_ARGUMENTS);
            status.setOutput(null);
            return status;
        } else {
            ArrayList<String> output = new ArrayList<>();

            if (FileHashSet.contains(arguments.get(0))) {
                output.add("File \"" + arguments.get(0) + "\" cannot be executed again since it will result in stack overflow.");
                output.add("Execution terminated");
                status.setStatus(Status.FAILED);
                status.setOutput(output);
                return status;
            } else {
                FileHashSet.add(arguments.get(0));
                CommandLineProcessor commandLineProcessor = new CommandLineProcessor();
                ArrayList<AbstractCommand> commandsToExecute = new ArrayList<>();

                for (int i = 1; i < arguments.size(); i++) {
                    if (arguments.get(i).startsWith(":")) {
                        AbstractCommand command = commandLineProcessor.parseFileInput(arguments, i);
                        if (command != null) {
                            commandsToExecute.add(command);
                        }
                    }
                }

                for (AbstractCommand command : commandsToExecute) {
                    command.setTargetCollection(studyGroups);
                    CommandOutput commandOutput = command.execute();
                    if (commandOutput != null && commandOutput.getOutput() != null) {
                        output.addAll(commandOutput.getOutput());
                    }
                }

                FileHashSet.remove(arguments.get(0));
                status.setStatus(Status.OK);
                status.setOutput(output);
                return status;
            }
        }
    }
}
