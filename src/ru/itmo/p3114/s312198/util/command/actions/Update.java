package ru.itmo.p3114.s312198.util.command.actions;

import ru.itmo.p3114.s312198.collection.Location;
import ru.itmo.p3114.s312198.collection.Person;
import ru.itmo.p3114.s312198.collection.StudyGroup;
import ru.itmo.p3114.s312198.exception.ValueOutOfBoundsException;
import ru.itmo.p3114.s312198.util.FieldParser;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class Update extends AbstractCommand {
    private final LinkedHashSet<StudyGroup> studyGroups;

    public Update(LinkedHashSet<StudyGroup> studyGroups) {
        setCommand("update");
        this.studyGroups = studyGroups;
    }

    public Update(ArrayList<String> arguments, LinkedHashSet<StudyGroup> studyGroups) {
        super("update", arguments);
        this.studyGroups = studyGroups;
    }

    @Override
    public Status execute() {
        if (getArguments() == null || (arguments.size() != 13 && arguments.size() != 11 && arguments.size() != 7)) {
            status = Status.INCORRECT_ARGUMENTS;
            return Status.INCORRECT_ARGUMENTS;
        } else {
            Person admin = null;
            Location location = null;
            long id = Long.parseLong(getArguments().get(0));

            for (StudyGroup sg : studyGroups) {
                if (sg.getId() == id) {
                    try {
                        sg.setName(FieldParser.parseName(arguments.get(1)));
                        sg.setCoordinates(FieldParser.parseCoordinates(arguments.get(2)));
                        sg.setStudentsCount(FieldParser.parseStudentsCount(arguments.get(3)));
                        sg.setShouldBeExpelled(FieldParser.parseShouldBeExpelled(arguments.get(4)));
                        sg.setTransferredStudents(FieldParser.parseTransferredStudents(arguments.get(5)));
                        sg.setFormOfEducation(FieldParser.parseFormOfEducation(arguments.get(6)));

                        if (arguments.size() > 7) {
                            if (arguments.size() > 11) {
                                location = new Location();

                                location.setCoords(FieldParser.parseLocationCoords(arguments.get(11)));
                                location.setName(FieldParser.parseName(arguments.get(12)));
                            }

                            admin = new Person();

                            admin.setName(FieldParser.parseName(arguments.get(7)));
                            admin.setHeight(FieldParser.parseHeight(arguments.get(8)));
                            admin.setHairColor(FieldParser.parseHairColor(arguments.get(9)));
                            admin.setNationality(FieldParser.parseNationality(arguments.get(10)));
                            admin.setLocation(location);

                            sg.setGroupAdmin(admin);
                        }

                        status = Status.OK;
                        return Status.OK;
                    } catch (ValueOutOfBoundsException voob) {
                        voob.printStackTrace();
                        status = Status.FAILED;
                        return Status.FAILED;
                    }
                }
            }

            status = Status.FAILED;
            return Status.FAILED;
        }
    }
}
