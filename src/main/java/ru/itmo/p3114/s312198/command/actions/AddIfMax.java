package ru.itmo.p3114.s312198.command.actions;

import ru.itmo.p3114.s312198.collection.Location;
import ru.itmo.p3114.s312198.collection.Person;
import ru.itmo.p3114.s312198.collection.StudyGroup;
import ru.itmo.p3114.s312198.command.actions.marker.CollectionInteracting;
import ru.itmo.p3114.s312198.command.actions.marker.DatabaseInteracting;
import ru.itmo.p3114.s312198.exception.ValueOutOfBoundsException;
import ru.itmo.p3114.s312198.command.CommandOutput;
import ru.itmo.p3114.s312198.util.LocationBuilder;
import ru.itmo.p3114.s312198.util.PersonBuilder;
import ru.itmo.p3114.s312198.util.StudyGroupBuilder;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * Adds a new element to the collection, but only if it`s the greatest
 */
public class AddIfMax extends AbstractCommand implements CollectionInteracting, DatabaseInteracting {
    private LinkedHashSet<StudyGroup> studyGroups;

    /**
     * Creates a new instance of AddIfMax command
     * @param studyGroups The collection, it`s working with
     */
    public AddIfMax(LinkedHashSet<StudyGroup> studyGroups) {
        setCommand("add_if_max");
        this.studyGroups = studyGroups;
    }

    /**
     * Creates a new instance of AddIfMax command
     * @param arguments Arguments
     * @param studyGroups The collection, it`s working with
     */
    public AddIfMax(ArrayList<String> arguments, LinkedHashSet<StudyGroup> studyGroups) {
        super("add_if_max", arguments);
        this.studyGroups = studyGroups;
    }

    public void setTargetCollection(LinkedHashSet<StudyGroup> studyGroups) {
        this.studyGroups = studyGroups;
    }

    /**
     * Executes the AddIfMax command
     * @return Status (OK, FAILED if the element already exists or there are values out of bounds, INCORRECT_ARGUMENTS)
     */
    @Override
    public CommandOutput execute() {
        if (getArguments() == null || (arguments.size() != 12 && arguments.size() != 10 && arguments.size() != 6)) {
            status.setStatus(Status.INCORRECT_ARGUMENTS);
            status.setOutput(null);
            return status;
        } else {
            Location location = null;
            Person admin = null;
            StudyGroup studyGroup;

            try {
                if (arguments.size() > 6) {
                    if (arguments.size() > 10) {
                        location = new LocationBuilder()
                                .addCoords(arguments.get(10))
                                .addName(arguments.get(11))
                                .toLocation();
                    }

                    admin = new PersonBuilder()
                            .addName(arguments.get(6))
                            .addHeight(arguments.get(7))
                            .addHairColor(arguments.get(8))
                            .addNationality(arguments.get(9))
                            .addLocation(location)
                            .toPerson();
                }

                studyGroup = new StudyGroupBuilder()
                        .addName(arguments.get(0))
                        .addCoordinates(arguments.get(1))
                        .addStudentsCount(arguments.get(2))
                        .addShouldBeExpelled(arguments.get(3))
                        .addTransferredStudents(arguments.get(4))
                        .addFormOfEducation(arguments.get(5))
                        .addGroupAdmin(admin)
                        .toStudyGroup();
                studyGroup.setId(id);
            } catch (ValueOutOfBoundsException voob) {
                status.setStatus(Status.FAILED);
                status.setOutput(null);
                return status;
            }

            for (StudyGroup sg : studyGroups) {
                if (sg.compareTo(studyGroup) > 0) {
                    status.setStatus(Status.OK);
                    status.setOutput(null);
                    return status;
                }
            }

            if (studyGroups.contains(studyGroup)) {
                status.setStatus(Status.FAILED);
                status.setOutput(null);
                return status;
            } else {
                studyGroups.add(studyGroup);
                status.setStatus(Status.OK);
                status.setOutput(null);
                return status;
            }
        }
    }
}
