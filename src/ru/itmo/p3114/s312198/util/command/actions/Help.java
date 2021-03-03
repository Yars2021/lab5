package ru.itmo.p3114.s312198.util.command.actions;

import java.util.ArrayList;

public class Help extends AbstractCommand {
    public Help() {
        setCommand("help");
    }

    public Help(ArrayList<String> arguments) {
        super("help", arguments);
    }

    @Override
    public Status execute() {
        if (getArguments() != null) {
            status = Status.INCORRECT_ARGUMENTS;
            return Status.INCORRECT_ARGUMENTS;
        } else {
            System.out.println("=============================================================================================================");
            System.out.println("                     Command reference page. {arg} means that arg is a complex argument                      ");
            System.out.println("=============================================================================================================");
            System.out.println("help: Command reference");
            System.out.println("info: Shows the information about the data collection (LinkedHashSet of StudyGroups)");
            System.out.println("show: Prints all the data of the collection");
            System.out.println("add {element}: Adds a new element to the collection");
            System.out.println("update id {element}: Updates an element by id");
            System.out.println("remove_by_id id: Removes an element by id");
            System.out.println("clear: Removes everything from the collection");
            System.out.println("save file_name: Saves the collection into a CSV file");
            System.out.println("execute_script file_name: Executes a script of these commands");
            System.out.println("exit: Aborts the program without saving anything");
            System.out.println("add_if_max {element}: Adds the element only if it's value is greater than the max value of the collection");
            System.out.println("remove_greater {element}: Removes all the elements with the greater value than the argument");
            System.out.println("history: Shows 5 last used commands without their arguments");
            System.out.println("remove_all_by_should_be_expelled shouldBeExpelled: Removes all the elements by their shouldBeExpelled value");
            System.out.println("remove_any_by_transferred_students transferredStudents: Removes one element by it's transferredStudents value");
            System.out.println("print_field_ascending_group_admin: Prints all admins sorted from the smallest to the biggest");
            System.out.println("=============================================================================================================");
            status = Status.OK;
            return Status.OK;
        }
    }
}
