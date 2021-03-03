package ru.itmo.p3114.s312198.util.command;

import ru.itmo.p3114.s312198.collection.Color;
import ru.itmo.p3114.s312198.collection.Country;
import ru.itmo.p3114.s312198.collection.FormOfEducation;
import ru.itmo.p3114.s312198.collection.StudyGroup;
import ru.itmo.p3114.s312198.exception.ValueOutOfBoundsException;
import ru.itmo.p3114.s312198.util.ConsoleReader;
import ru.itmo.p3114.s312198.util.FieldParser;

import ru.itmo.p3114.s312198.util.command.actions.Add;
import ru.itmo.p3114.s312198.util.command.actions.AddIfMax;
import ru.itmo.p3114.s312198.util.command.actions.Clear;
import ru.itmo.p3114.s312198.util.command.actions.AbstractCommand;
import ru.itmo.p3114.s312198.util.command.actions.ExecuteScript;
import ru.itmo.p3114.s312198.util.command.actions.Exit;
import ru.itmo.p3114.s312198.util.command.actions.Help;
import ru.itmo.p3114.s312198.util.command.actions.History;
import ru.itmo.p3114.s312198.util.command.actions.Info;
import ru.itmo.p3114.s312198.util.command.actions.PrintFieldAscendingGroupAdmin;
import ru.itmo.p3114.s312198.util.command.actions.RemoveAllByShouldBeExpelled;
import ru.itmo.p3114.s312198.util.command.actions.RemoveAnyByTransferredStudents;
import ru.itmo.p3114.s312198.util.command.actions.RemoveByID;
import ru.itmo.p3114.s312198.util.command.actions.RemoveGreater;
import ru.itmo.p3114.s312198.util.command.actions.Save;
import ru.itmo.p3114.s312198.util.command.actions.Show;
import ru.itmo.p3114.s312198.util.command.actions.Update;

import java.util.ArrayList;
import java.util.LinkedHashSet;
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

public class CommandLineProcessor {
    private final LinkedHashSet<StudyGroup> studyGroups;
    private final LinkedList<AbstractCommand> history = new LinkedList<>();

    public CommandLineProcessor(LinkedHashSet<StudyGroup> collection) {
        studyGroups = collection;
    }

    public AbstractCommand parseInput(String line, boolean suppressOutput) {
        ArrayList<String> arguments;
        Commands command;

        try {
            command = Commands.valueOf(line.toUpperCase(Locale.ROOT).split("\\s")[0]);
        } catch (IllegalArgumentException iae) {
            System.out.println("Unknown command: " + line.split("\\s")[0]);
            return null;
        }

        AbstractCommand currentCommand = null;

        switch (command) {
            case HELP:
                currentCommand = new Help();
                currentCommand.execute();
                break;
            case INFO:
                currentCommand = new Info(studyGroups);
                currentCommand.execute();
                break;
            case SHOW:
                currentCommand = new Show(studyGroups);
                currentCommand.execute();
                break;
            case ADD:
                arguments = requestElement(suppressOutput);
                currentCommand = new Add(arguments, studyGroups);
                currentCommand.execute();
                break;
            case UPDATE:
                arguments = new ArrayList<>();
                try {
                    arguments.add(line.split("\\s")[1].trim());
                    arguments.addAll(requestElement(suppressOutput));
                } catch (ArrayIndexOutOfBoundsException aioob) {
                    System.out.println("Incorrect input, no argument found");
                }
                currentCommand = new Update(arguments, studyGroups);
                currentCommand.execute();
                break;
            case REMOVE_BY_ID:
                arguments = new ArrayList<>();
                try {
                    arguments.add(line.split("\\s")[1].trim());
                } catch (ArrayIndexOutOfBoundsException aioob) {
                    System.out.println("Incorrect input, no argument found");
                }
                currentCommand = new RemoveByID(arguments, studyGroups);
                currentCommand.execute();
                break;
            case CLEAR:
                currentCommand = new Clear(studyGroups);
                currentCommand.execute();
                break;
            case SAVE:
                arguments = new ArrayList<>();
                try {
                    arguments.add(line.split("\\s")[1].trim());
                } catch (ArrayIndexOutOfBoundsException aioob) {
                    System.out.println("Incorrect input, no argument found");
                }
                currentCommand = new Save(arguments, studyGroups);
                currentCommand.execute();
                break;
            case EXECUTE_SCRIPT:
                arguments = new ArrayList<>();
                try {
                    arguments.add(line.split("\\s")[1].trim());
                } catch (ArrayIndexOutOfBoundsException aioob) {
                    System.out.println("Incorrect input, no argument found");
                }
                currentCommand = new ExecuteScript(arguments, studyGroups);
                currentCommand.execute();
                break;
            case EXIT:
                currentCommand = new Exit();
                currentCommand.execute();
                break;
            case ADD_IF_MAX:
                arguments = requestElement(suppressOutput);
                currentCommand = new AddIfMax(arguments, studyGroups);
                currentCommand.execute();
                break;
            case REMOVE_GREATER:
                arguments = requestElement(suppressOutput);
                currentCommand = new RemoveGreater(arguments, studyGroups);
                currentCommand.execute();
                break;
            case HISTORY:
                currentCommand = new History(history);
                currentCommand.execute();
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
                    currentCommand = new RemoveAllByShouldBeExpelled(arguments, studyGroups);
                } catch (ValueOutOfBoundsException voob) {
                    System.out.println("Incorrect value format");
                    currentCommand = new RemoveAllByShouldBeExpelled(null, null);
                } catch (IndexOutOfBoundsException ioob) {
                    currentCommand = new RemoveAllByShouldBeExpelled(null, null);
                }
                currentCommand.execute();
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
                    currentCommand = new RemoveAnyByTransferredStudents(arguments, studyGroups);
                } catch (ValueOutOfBoundsException voob) {
                    System.out.println("Incorrect value format");
                    currentCommand = new RemoveAnyByTransferredStudents(null, null);
                } catch (IndexOutOfBoundsException ioob) {
                    currentCommand = new RemoveAllByShouldBeExpelled(null, null);
                }
                currentCommand.execute();
                break;
            case PRINT_FIELD_ASCENDING_GROUP_ADMIN:
                currentCommand = new PrintFieldAscendingGroupAdmin(studyGroups);
                currentCommand.execute();
                break;
        }

        history.addLast(currentCommand);
        if (history.size() > 5) {
            history.pop();
        }

        return currentCommand;
    }

    public AbstractCommand parseFileInput(List<String> lines, int index) {
        ArrayList<String> arguments;
        Commands command;

        try {
            command = Commands.valueOf(lines.get(index).toUpperCase(Locale.ROOT).split("\\s")[0].substring(1, lines.get(index).split("\\s")[0].length()));
        } catch (IllegalArgumentException iae) {
            System.out.println("Unknown command: " + lines.get(index).split("\\s")[0]);
            return null;
        }

        System.out.println("> " + lines.get(index).split("\\s")[0].substring(1) + ": ");

        AbstractCommand currentCommand = null;

        switch (command) {
            case HELP:
                currentCommand = new Help();
                currentCommand.execute();
                break;
            case INFO:
                currentCommand = new Info(studyGroups);
                currentCommand.execute();
                break;
            case SHOW:
                currentCommand = new Show(studyGroups);
                currentCommand.execute();
                break;
            case ADD:
                try {
                    arguments = readElement(lines, index);
                    currentCommand = new Add(arguments, studyGroups);
                    currentCommand.execute();
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
                currentCommand = new Update(arguments, studyGroups);
                currentCommand.execute();
                break;
            case REMOVE_BY_ID:
                arguments = new ArrayList<>();
                try {
                    arguments.add(lines.get(index).split("\\s")[1].trim());
                } catch (ArrayIndexOutOfBoundsException aioob) {
                    System.out.println("Incorrect input, no argument found");
                }
                currentCommand = new RemoveByID(arguments, studyGroups);
                currentCommand.execute();
                break;
            case CLEAR:
                currentCommand = new Clear(studyGroups);
                currentCommand.execute();
                break;
            case SAVE:
                arguments = new ArrayList<>();
                try {
                    arguments.add(lines.get(index).split("\\s")[1].trim());
                } catch (ArrayIndexOutOfBoundsException aioob) {
                    System.out.println("Incorrect input, no argument found");
                }
                currentCommand = new Save(arguments, studyGroups);
                currentCommand.execute();
                break;
            case EXECUTE_SCRIPT:
                arguments = new ArrayList<>();
                try {
                    arguments.add(lines.get(index).split("\\s")[1].trim());
                } catch (ArrayIndexOutOfBoundsException aioob) {
                    System.out.println("Incorrect input, no argument found");
                }
                currentCommand = new ExecuteScript(arguments, studyGroups);
                currentCommand.execute();
                break;
            case EXIT:
                currentCommand = new Exit();
                currentCommand.execute();
                break;
            case ADD_IF_MAX:
                arguments  = new ArrayList<>();
                try {
                    arguments = readElement(lines, index);
                } catch (Exception e) {
                    System.out.println("Incorrect input");
                }
                currentCommand = new AddIfMax(arguments, studyGroups);
                currentCommand.execute();
                break;
            case REMOVE_GREATER:
                arguments  = new ArrayList<>();
                try {
                    arguments = readElement(lines, index);
                } catch (Exception e) {
                    System.out.println("Incorrect input");
                }
                currentCommand = new RemoveGreater(arguments, studyGroups);
                currentCommand.execute();
                break;
            case HISTORY:
                currentCommand = new History(history);
                currentCommand.execute();
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
                    currentCommand = new RemoveAllByShouldBeExpelled(arguments, studyGroups);
                } catch (ValueOutOfBoundsException voob) {
                    System.out.println("Incorrect value format");
                    currentCommand = new RemoveAllByShouldBeExpelled(null, null);
                } catch (IndexOutOfBoundsException ioob) {
                    currentCommand = new RemoveAllByShouldBeExpelled(null, null);
                }
                currentCommand.execute();
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
                    currentCommand = new RemoveAnyByTransferredStudents(arguments, studyGroups);
                } catch (ValueOutOfBoundsException voob) {
                    System.out.println("Incorrect value format");
                    currentCommand = new RemoveAnyByTransferredStudents(null, null);
                } catch (IndexOutOfBoundsException ioob) {
                    currentCommand = new RemoveAllByShouldBeExpelled(null, null);
                }
                currentCommand.execute();
                break;
            case PRINT_FIELD_ASCENDING_GROUP_ADMIN:
                currentCommand = new PrintFieldAscendingGroupAdmin(studyGroups);
                currentCommand.execute();
                break;
        }

        return currentCommand;
    }

    private ArrayList<String> requestElement(boolean suppressOutput) {
        ArrayList<String> args = new ArrayList<>();
        String input;
        boolean wrong = true;

        if (!suppressOutput) {
            System.out.print("Enter the name of a new group: ");
        }
        while (FieldParser.parseName(input = ConsoleReader.readLine()) == null) {
            if (!suppressOutput) {
                System.out.println("Incorrect input, try again");
                System.out.print("> ");
            }
        }
        args.add(input);

        if (!suppressOutput) {
            System.out.print("Enter the coordinates (x y): ");
        }
        while (FieldParser.parseCoordinates(input = ConsoleReader.readLine()) == null) {
            if (!suppressOutput) {
                System.out.println("Incorrect input, try again");
                System.out.print("> ");
            }
        }
        args.add(input);

        if (!suppressOutput) {
            System.out.print("Enter students count: ");
        }
        while (FieldParser.parseStudentsCount(input = ConsoleReader.readLine()) == null) {
            if (!suppressOutput) {
                System.out.println("Incorrect input, try again");
                System.out.print("> ");
            }
        }
        args.add(input);

        if (!suppressOutput) {
            System.out.print("Enter the number of students that should be expelled: ");
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
            System.out.print("Enter the number of transferred students: ");
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
                System.out.print("Enter the group admin`s name: ");
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
                System.out.print("Enter the group admin`s height: ");
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
                    System.out.print("Enter the group admin`s location coordinates: ");
                }
                while (FieldParser.parseLocationCoords(input = ConsoleReader.readLine()) == null) {
                    if (!suppressOutput) {
                        System.out.println("Incorrect input, try again");
                        System.out.print("> ");
                    }
                }
                args.add(input);

                if (!suppressOutput) {
                    System.out.print("Enter the group admin`s location name: ");
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
        return args;
    }

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