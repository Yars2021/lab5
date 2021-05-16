package ru.itmo.p3114.s312198.util.command;

import ru.itmo.p3114.s312198.collection.Color;
import ru.itmo.p3114.s312198.collection.Country;
import ru.itmo.p3114.s312198.collection.FormOfEducation;
import ru.itmo.p3114.s312198.exception.ValueOutOfBoundsException;
import ru.itmo.p3114.s312198.util.ConsoleReader;
import ru.itmo.p3114.s312198.util.FieldParser;
import ru.itmo.p3114.s312198.util.command.actions.AbstractCommand;
import ru.itmo.p3114.s312198.util.command.actions.Add;
import ru.itmo.p3114.s312198.util.command.actions.AddIfMax;
import ru.itmo.p3114.s312198.util.command.actions.Clear;
import ru.itmo.p3114.s312198.util.command.actions.ExecuteScript;
import ru.itmo.p3114.s312198.util.command.actions.Exit;
import ru.itmo.p3114.s312198.util.command.actions.Help;
import ru.itmo.p3114.s312198.util.command.actions.History;
import ru.itmo.p3114.s312198.util.command.actions.Info;
import ru.itmo.p3114.s312198.util.command.actions.Login;
import ru.itmo.p3114.s312198.util.command.actions.PrintFieldAscendingGroupAdmin;
import ru.itmo.p3114.s312198.util.command.actions.Register;
import ru.itmo.p3114.s312198.util.command.actions.RemoveAllByShouldBeExpelled;
import ru.itmo.p3114.s312198.util.command.actions.RemoveAnyByTransferredStudents;
import ru.itmo.p3114.s312198.util.command.actions.RemoveByID;
import ru.itmo.p3114.s312198.util.command.actions.RemoveGreater;
import ru.itmo.p3114.s312198.util.command.actions.Save;
import ru.itmo.p3114.s312198.util.command.actions.Show;
import ru.itmo.p3114.s312198.util.command.actions.Update;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

// 0: GroupName
// 1: coordsX, coordsY
// 2: studentsCount
// 3: shouldBeExpelled
// 4: transferredStudents
// 5: formOfEducation
// 6: AdminName
// 7: AdminHeight
// 8: AdminHairColor
// 9: AdminNationality
// 10: LocationX, LocationY, LocationZ
// 11: LocationName

/**
 * Processes the user`s input
 */
public class CommandLineProcessor {
    private final LinkedList<AbstractCommand> history = new LinkedList<>();

    /**
     * Parses a String, splits it into command name and arguments, creates the command
     *
     * @param line           Input
     * @param suppressOutput Will not print anything if set to true
     * @return Last used command
     */
    public AbstractCommand parseInput(String line, boolean suppressOutput) {
        ArrayList<String> arguments;
        Commands command;

        try {
            if (line == null) {
                command = Commands.EXIT;
            } else {
                command = Commands.valueOf(line.trim().toUpperCase(Locale.ROOT).split("\\s")[0]);
            }
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException iae) {
            System.out.println("Unknown command: " + line.split("\\s")[0]);
            System.out.println("Use \"help\" to see the command reference");
            return null;
        }

        AbstractCommand currentCommand = null;

        switch (command) {
            case HELP:
                currentCommand = new Help();
                break;
            case INFO:
                currentCommand = new Info(null);
                break;
            case SHOW:
                currentCommand = new Show(null);
                break;
            case ADD:
                arguments = requestElement(suppressOutput);
                currentCommand = new Add(arguments, null);
                break;
            case UPDATE:
                arguments = new ArrayList<>();
                try {
                    arguments.add(line.split("\\s")[1].trim());
                    arguments.addAll(requestElement(suppressOutput));
                } catch (ArrayIndexOutOfBoundsException aioob) {
                    System.out.println("Incorrect input, no argument found");
                }
                currentCommand = new Update(arguments, null);
                break;
            case REMOVE_BY_ID:
                arguments = new ArrayList<>();
                try {
                    arguments.add(line.split("\\s")[1].trim());
                } catch (ArrayIndexOutOfBoundsException aioob) {
                    System.out.println("Incorrect input, no argument found");
                }
                currentCommand = new RemoveByID(arguments, null);
                break;
            case CLEAR:
                currentCommand = new Clear(null);
                break;
            case SAVE:
                arguments = new ArrayList<>();
                try {
                    arguments.add(line.split("\\s")[1].trim());
                } catch (ArrayIndexOutOfBoundsException aioob) {
                    System.out.println("Incorrect input, no argument found");
                }
                currentCommand = new Save(arguments, null);
                currentCommand = null;
                break;
            case EXECUTE_SCRIPT:
                arguments = new ArrayList<>();
                List<String> lines;
                String filename = "";
                try {
                    filename = line.split("\\s")[1].trim();
                } catch (ArrayIndexOutOfBoundsException aioob) {
                    System.out.println("Incorrect input, no argument found");
                }

                arguments.add(filename);

                try {
                    lines = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
                    arguments.addAll(lines);
                } catch (IOException e) {
                    System.out.println("File cannot be read");
                }

                currentCommand = new ExecuteScript(arguments, null);
                break;
            case EXIT:
                currentCommand = new Exit();
                currentCommand.execute();
                currentCommand = null;
                break;
            case ADD_IF_MAX:
                arguments = requestElement(suppressOutput);
                currentCommand = new AddIfMax(arguments, null);
                break;
            case REMOVE_GREATER:
                arguments = requestElement(suppressOutput);
                currentCommand = new RemoveGreater(arguments, null);
                break;
            case HISTORY:
                currentCommand = new History(history);
                break;
            case REMOVE_ALL_BY_SHOULD_BE_EXPELLED:
                arguments = new ArrayList<>();
                try {
                    arguments.add(line.split("\\s")[1].trim());
                } catch (ArrayIndexOutOfBoundsException aioob) {
                    System.out.println("Incorrect input, no argument found");
                }
                try {
                    FieldParser.parseShouldBeExpelled(arguments.get(0));
                    currentCommand = new RemoveAllByShouldBeExpelled(arguments, null);
                } catch (ValueOutOfBoundsException voob) {
                    System.out.println("Incorrect value format");
                    currentCommand = new RemoveAllByShouldBeExpelled(null, null);
                } catch (IndexOutOfBoundsException ioob) {
                    currentCommand = new RemoveAllByShouldBeExpelled(null, null);
                }
                break;
            case REMOVE_ANY_BY_TRANSFERRED_STUDENTS:
                arguments = new ArrayList<>();
                try {
                    arguments.add(line.split("\\s")[1].trim());
                } catch (ArrayIndexOutOfBoundsException aioob) {
                    System.out.println("Incorrect input, no argument found");
                }
                try {
                    FieldParser.parseTransferredStudents(arguments.get(0));
                    currentCommand = new RemoveAnyByTransferredStudents(arguments, null);
                } catch (ValueOutOfBoundsException voob) {
                    System.out.println("Incorrect value format");
                    currentCommand = new RemoveAnyByTransferredStudents(null, null);
                } catch (IndexOutOfBoundsException ioob) {
                    currentCommand = new RemoveAllByShouldBeExpelled(null, null);
                }
                break;
            case PRINT_FIELD_ASCENDING_GROUP_ADMIN:
                currentCommand = new PrintFieldAscendingGroupAdmin(null);
                break;
            case LOGIN:
                currentCommand = new Login();
                break;
            case REGISTER:
                currentCommand = new Register();
                break;
        }

        history.addLast(currentCommand);
        if (history.size() > 5) {
            history.pop();
        }

        return currentCommand;
    }

    /**
     * Parses a String, splits it into command name and arguments, creates the command
     *
     * @param lines Lines of the file
     * @param index The index of current line
     * @return Last used command
     */
    public AbstractCommand parseFileInput(List<String> lines, int index) {
        ArrayList<String> arguments;
        Commands command;

        try {
            command = Commands.valueOf(lines.get(index).toUpperCase(Locale.ROOT).split("\\s")[0].substring(1, lines.get(index).split("\\s")[0].length()));
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException iae) {
            System.out.println("Unknown command: " + lines.get(index).split("\\s")[0]);
            return null;
        }

        System.out.println("> Current file command: " + lines.get(index).split("\\s")[0].substring(1) + ": ");

        AbstractCommand currentCommand = null;

        switch (command) {
            case HELP:
                currentCommand = new Help();
                break;
            case INFO:
                currentCommand = new Info(null);
                break;
            case SHOW:
                currentCommand = new Show(null);
                break;
            case ADD:
                try {
                    arguments = readElement(lines, index);
                    currentCommand = new Add(arguments, null);
                } catch (Exception e) {
                    System.out.println("Incorrect input");
                }
                break;
            case UPDATE:
                arguments = new ArrayList<>();
                try {
                    arguments.add(lines.get(index).split("\\s")[1].trim());
                    arguments.addAll(readElement(lines, index));
                } catch (Exception aioob) {
                    System.out.println("Incorrect input, no argument found");
                }
                currentCommand = new Update(arguments, null);
                break;
            case REMOVE_BY_ID:
                arguments = new ArrayList<>();
                try {
                    arguments.add(lines.get(index).split("\\s")[1].trim());
                } catch (ArrayIndexOutOfBoundsException aioob) {
                    System.out.println("Incorrect input, no argument found");
                }
                break;
            case CLEAR:
                currentCommand = new Clear(null);
                break;
            case SAVE:
                arguments = new ArrayList<>();
                try {
                    arguments.add(lines.get(index).split("\\s")[1].trim());
                } catch (ArrayIndexOutOfBoundsException aioob) {
                    System.out.println("Incorrect input, no argument found");
                }
                currentCommand = new Save(arguments, null);
                currentCommand = null;
                break;
            case EXECUTE_SCRIPT:
                arguments = new ArrayList<>();
                List<String> fileLines;
                String filename = "";

                try {
                    filename = lines.get(index).split("\\s")[1].trim();
                } catch (ArrayIndexOutOfBoundsException aioob) {
                    System.out.println("Incorrect input, no argument found");
                }

                arguments.add(filename);

                try {
                    lines = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
                    arguments.addAll(lines);
                } catch (IOException e) {
                    System.out.println("File cannot be read");
                }

                currentCommand = new ExecuteScript(arguments, null);
                break;
            case EXIT:
                currentCommand = new Exit();
                currentCommand.execute();
                currentCommand = null;
                break;
            case ADD_IF_MAX:
                arguments = new ArrayList<>();
                try {
                    arguments = readElement(lines, index);
                } catch (Exception e) {
                    System.out.println("Incorrect input");
                }
                currentCommand = new AddIfMax(arguments, null);
                break;
            case REMOVE_GREATER:
                arguments = new ArrayList<>();
                try {
                    arguments = readElement(lines, index);
                } catch (Exception e) {
                    System.out.println("Incorrect input");
                }
                currentCommand = new RemoveGreater(arguments, null);
                break;
            case HISTORY:
                currentCommand = new History(history);
                break;
            case REMOVE_ALL_BY_SHOULD_BE_EXPELLED:
                arguments = new ArrayList<>();
                try {
                    arguments.add(lines.get(index).split("\\s")[1].trim());
                } catch (ArrayIndexOutOfBoundsException aioob) {
                    System.out.println("Incorrect input, no argument found");
                }
                try {
                    FieldParser.parseShouldBeExpelled(arguments.get(0));
                    currentCommand = new RemoveAllByShouldBeExpelled(arguments, null);
                } catch (ValueOutOfBoundsException voob) {
                    System.out.println("Incorrect value format");
                    currentCommand = new RemoveAllByShouldBeExpelled(null, null);
                } catch (IndexOutOfBoundsException ioob) {
                    currentCommand = new RemoveAllByShouldBeExpelled(null, null);
                }
                break;
            case REMOVE_ANY_BY_TRANSFERRED_STUDENTS:
                arguments = new ArrayList<>();
                try {
                    arguments.add(lines.get(index).split("\\s")[1].trim());
                } catch (ArrayIndexOutOfBoundsException aioob) {
                    System.out.println("Incorrect input, no argument found");
                }
                try {
                    FieldParser.parseTransferredStudents(arguments.get(0));
                    currentCommand = new RemoveAnyByTransferredStudents(arguments, null);
                } catch (ValueOutOfBoundsException voob) {
                    System.out.println("Incorrect value format");
                    currentCommand = new RemoveAnyByTransferredStudents(null, null);
                } catch (IndexOutOfBoundsException ioob) {
                    currentCommand = new RemoveAllByShouldBeExpelled(null, null);
                }
                break;
            case PRINT_FIELD_ASCENDING_GROUP_ADMIN:
                currentCommand = new PrintFieldAscendingGroupAdmin(null);
                break;
        }

        return currentCommand;
    }

    /**
     * Requests StudyGroup field values from the user and validates them
     *
     * @param suppressOutput Will not print anything if set to true
     * @return ArrayList of values
     */
    private ArrayList<String> requestElement(boolean suppressOutput) {
        ArrayList<String> args = new ArrayList<>();
        String input;
        boolean wrong = true;

        try {
            if (!suppressOutput) {
                System.out.print("Enter the name of a new group (String value): ");
            }
            while (FieldParser.parseName(input = ConsoleReader.readLine()) == null) {
                if (!suppressOutput) {
                    System.out.println("Incorrect input, try again");
                    System.out.print("> ");
                }
            }
            args.add(input);

            if (!suppressOutput) {
                System.out.print("Enter the coordinates (x y; x is Integer and y is Double <= 426): ");
            }
            while (FieldParser.parseCoordinates(input = ConsoleReader.readLine()) == null) {
                if (!suppressOutput) {
                    System.out.println("Incorrect input, try again");
                    System.out.print("> ");
                }
            }
            args.add(input);

            if (!suppressOutput) {
                System.out.print("Enter students count (Integer value): ");
            }
            while (FieldParser.parseStudentsCount(input = ConsoleReader.readLine()) == null) {
                if (!suppressOutput) {
                    System.out.println("Incorrect input, try again");
                    System.out.print("> ");
                }
            }
            args.add(input);

            if (!suppressOutput) {
                System.out.print("Enter the number of students that should be expelled (Integer value): ");
            }
            while (wrong) {
                try {
                    FieldParser.parseShouldBeExpelled(input = ConsoleReader.readLine());
                    wrong = false;
                } catch (ValueOutOfBoundsException voob) {
                    if (!suppressOutput) {
                        System.out.println("Incorrect input, try again");
                        System.out.print("> ");
                    }
                }
            }
            args.add(input);

            wrong = true;

            if (!suppressOutput) {
                System.out.print("Enter the number of transferred students (Integer value): ");
            }
            while (wrong) {
                try {
                    FieldParser.parseTransferredStudents(input = ConsoleReader.readLine());
                    wrong = false;
                } catch (ValueOutOfBoundsException voob) {
                    if (!suppressOutput) {
                        System.out.println("Incorrect input, try again");
                        System.out.print("> ");
                    }
                }
            }
            args.add(input);

            if (!suppressOutput) {
                System.out.print("Enter the form of education (" + FormOfEducation.getValues() + "): ");
            }
            while (FieldParser.parseFormOfEducation(input = ConsoleReader.readLine()) == null) {
                if (!suppressOutput) {
                    System.out.println("Incorrect input, try again");
                    System.out.print("> ");
                }
            }
            args.add(input);

            if (!suppressOutput) {
                System.out.print("Does the group have an admin? (Y/N): ");
            }
            input = ConsoleReader.readLine();

            if (!"Y".equals(input.split("\\s+?")[0].toUpperCase(Locale.ROOT))) {
                return args;
            } else {
                if (!suppressOutput) {
                    System.out.print("Enter the group admin`s name (String value): ");
                }
                while (FieldParser.parseName(input = ConsoleReader.readLine()) == null) {
                    if (!suppressOutput) {
                        System.out.println("Incorrect input, try again");
                        System.out.print("> ");
                    }
                }
                args.add(input);

                wrong = true;

                if (!suppressOutput) {
                    System.out.print("Enter the group admin`s height (Positive Long value): ");
                }
                while (wrong) {
                    try {
                        FieldParser.parseHeight(input = ConsoleReader.readLine());
                        wrong = false;
                    } catch (ValueOutOfBoundsException voob) {
                        if (!suppressOutput) {
                            System.out.println("Incorrect input, try again");
                            System.out.print("> ");
                        }
                    }
                }
                args.add(input);

                if (!suppressOutput) {
                    System.out.print("Enter the group admin`s hair color (" + Color.getValues() + "): ");
                }
                if (FieldParser.parseHairColor(input = ConsoleReader.readLine()) == null) {
                    input = "UNDEFINED";
                }
                args.add(input);

                if (!suppressOutput) {
                    System.out.print("Enter the group admin`s nationality (" + Country.getValues() + "): ");
                }
                while (FieldParser.parseNationality(input = ConsoleReader.readLine()) == null) {
                    if (!suppressOutput) {
                        System.out.println("Incorrect input, try again");
                        System.out.print("> ");
                    }
                }
                args.add(input);

                if (!suppressOutput) {
                    System.out.print("Does the group admin have location? (Y/N): ");
                }
                input = ConsoleReader.readLine();

                if (!"Y".equals(input.split("\\s+?")[0].toUpperCase(Locale.ROOT))) {
                    return args;
                } else {
                    if (!suppressOutput) {
                        System.out.print("Enter the group admin`s location coordinates (x y z; 3 Float values divided by space): ");
                    }
                    while (FieldParser.parseLocationCoords(input = ConsoleReader.readLine()) == null) {
                        if (!suppressOutput) {
                            System.out.println("Incorrect input, try again");
                            System.out.print("> ");
                        }
                    }
                    args.add(input);

                    if (!suppressOutput) {
                        System.out.print("Enter the group admin`s location name (String value): ");
                    }
                    while (FieldParser.parseName(input = ConsoleReader.readLine()) == null) {
                        if (!suppressOutput) {
                            System.out.println("Incorrect input, try again");
                            System.out.print("> ");
                        }
                    }
                    args.add(input);
                }
            }
        } catch (Exception e) {
            Exit exit = new Exit();
            exit.execute();
        }
        return args;
    }

    /**
     * Reads StudyGroup field values and validates them
     *
     * @param lines The lines, read from file
     * @param index Current index
     * @return ArrayList of values
     * @throws Exception Can be thrown, if the index is out of bounds
     */
    private ArrayList<String> readElement(List<String> lines, int index) throws Exception {
        ArrayList<String> args = new ArrayList<>();
        String input;
        boolean wrong = true;

        args.add(FieldParser.parseName(lines.get(index + 1)));
        args.add(lines.get(index + 2));
        args.add(lines.get(index + 3));
        args.add(lines.get(index + 4));
        args.add(lines.get(index + 5));
        args.add(lines.get(index + 6));

        input = lines.get(index + 7);

        if (!"Y".equals(input.split("\\s+?")[0].toUpperCase(Locale.ROOT))) {
            return args;
        } else {
            args.add(lines.get(index + 8));
            args.add(lines.get(index + 9));
            args.add(lines.get(index + 10));
            args.add(lines.get(index + 11));

            input = lines.get(index + 12);

            if (!"Y".equals(input.split("\\s+?")[0].toUpperCase(Locale.ROOT))) {
                return args;
            } else {
                args.add(lines.get(index + 13));
                args.add(lines.get(index + 14));
            }
        }

        return args;
    }
}
