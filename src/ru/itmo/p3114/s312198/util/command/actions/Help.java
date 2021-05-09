package ru.itmo.p3114.s312198.util.command.actions;

import ru.itmo.p3114.s312198.util.CommandOutput;

import java.util.ArrayList;

/**
 * Prints command reference
 */
public class Help extends AbstractCommand {
    /**
     * Creates a new instance of Help command
     */
    public Help() {
        setCommand("help");
    }

    /**
     * Creates a new instance of Help command
     * @param arguments Arguments
     */
    public Help(ArrayList<String> arguments) {
        super("help", arguments);
    }

    /**
     * Executes the Help command
     * @return Status
     */
    @Override
    public CommandOutput execute() {
        if (getArguments() != null) {
            status.setStatus(Status.INCORRECT_ARGUMENTS);
            status.setOutput(null);
            return status;
        } else {
            ArrayList<String> output = new ArrayList<>();
            output.add("=============================================================================================================");
            output.add("                     Command reference page. {arg} means that arg is a complex argument                      ");
            output.add("=============================================================================================================");
            output.add("help: Command reference");
            output.add("info: Shows the information about the data collection (LinkedHashSet of StudyGroups)");
            output.add("show: Prints all the data of the collection");
            output.add("add {element}: Adds a new element to the collection");
            output.add("update id {element}: Updates an element by id");
            output.add("remove_by_id id: Removes an element by id");
            output.add("clear: Removes everything from the collection");
            output.add("save file_name: Saves the collection into a CSV file");
            output.add("execute_script file_name: Executes a script of these commands");
            output.add("exit: Aborts the program without saving anything");
            output.add("add_if_max {element}: Adds the element only if it's value is greater than the max value of the collection");
            output.add("remove_greater {element}: Removes all the elements with the greater value than the argument");
            output.add("history: Shows 5 last used commands without their arguments");
            output.add("remove_all_by_should_be_expelled shouldBeExpelled: Removes all the elements by their shouldBeExpelled value");
            output.add("remove_any_by_transferred_students transferredStudents: Removes one element by it's transferredStudents value");
            output.add("print_field_ascending_group_admin: Prints all admins sorted from the smallest to the biggest");
            output.add("=============================================================================================================");
            status.setStatus(Status.OK);
            status.setOutput(output);
            return status;
        }
    }
}
