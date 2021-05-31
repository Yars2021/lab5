package ru.itmo.p3114.s312198.command.actions;

import ru.itmo.p3114.s312198.collection.Location;
import ru.itmo.p3114.s312198.collection.Person;
import ru.itmo.p3114.s312198.collection.StudyGroup;
import ru.itmo.p3114.s312198.command.actions.marker.CollectionInteracting;
import ru.itmo.p3114.s312198.command.actions.marker.DatabaseInteracting;
import ru.itmo.p3114.s312198.exception.ValueOutOfBoundsException;
import ru.itmo.p3114.s312198.command.CommandOutput;
import ru.itmo.p3114.s312198.util.FieldParser;
import ru.itmo.p3114.s312198.util.LocationBuilder;
import ru.itmo.p3114.s312198.util.PersonBuilder;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * Updates an element by ID
 */
public class Update extends AbstractCommand implements CollectionInteracting, DatabaseInteracting {
    private LinkedHashSet<StudyGroup> studyGroups;

    /**
     * Creates an instance of Update command
     * @param studyGroups The collection it`s working with
     */
    public Update(LinkedHashSet<StudyGroup> studyGroups) {
        setCommand("update");
        this.studyGroups = studyGroups;
    }

    /**
     * Creates an instance of Update command
     * @param arguments Arguments
     * @param studyGroups The collection it`s working with
     */
    public Update(ArrayList<String> arguments, LinkedHashSet<StudyGroup> studyGroups) {
        super("update", arguments);
        this.studyGroups = studyGroups;
    }

    public void setTargetCollection(LinkedHashSet<StudyGroup> studyGroups) {
        this.studyGroups = studyGroups;
    }

    /**
     * Executes the Update command
     * @return Status (OK, FAILED if there is no element with such ID or some field values are out of bounds, INCORRECT_ARGUMENTS)
     */
    @Override
    public CommandOutput execute() {
        if (getArguments() == null || (arguments.size() != 13 && arguments.size() != 11 && arguments.size() != 7)) {
            status.setStatus(Status.INCORRECT_ARGUMENTS);
            status.setOutput(null);
            return status;
        } else {
            Person admin;
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
                                location = new LocationBuilder()
                                        .addCoords(arguments.get(11))
                                        .addName(arguments.get(12))
                                        .toLocation();
                            }
                            admin = new PersonBuilder()
                                    .addName(arguments.get(7))
                                    .addHeight(arguments.get(8))
                                    .addHairColor(arguments.get(9))
                                    .addNationality(arguments.get(10))
                                    .addLocation(location)
                                    .toPerson();

                            sg.setGroupAdmin(admin);
                        }

                        status.setStatus(Status.OK);
                        status.setOutput(null);
                        return status;
                    } catch (ValueOutOfBoundsException voob) {
                        voob.printStackTrace();
                        status.setStatus(Status.FAILED);
                        status.setOutput(null);
                        return status;
                    }
                }
            }

            status.setStatus(Status.FAILED);
            status.setOutput(null);
            return status;
        }
    }
}
