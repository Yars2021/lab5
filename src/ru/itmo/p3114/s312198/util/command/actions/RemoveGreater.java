package ru.itmo.p3114.s312198.util.command.actions;

import ru.itmo.p3114.s312198.collection.Location;
import ru.itmo.p3114.s312198.collection.Person;
import ru.itmo.p3114.s312198.collection.StudyGroup;
import ru.itmo.p3114.s312198.exception.ValueOutOfBoundsException;
import ru.itmo.p3114.s312198.util.LocationBuilder;
import ru.itmo.p3114.s312198.util.PersonBuilder;
import ru.itmo.p3114.s312198.util.StudyGroupBuilder;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * Removes all the elements, greater than current
 */
public class RemoveGreater extends AbstractCommand {
    private final LinkedHashSet<StudyGroup> studyGroups;

    /**
     * Creates an instance of RemoveGreater command
     * @param studyGroups The collection it`s working with
     */
    public RemoveGreater(LinkedHashSet<StudyGroup> studyGroups) {
        setCommand("remove_greater");
        this.studyGroups = studyGroups;
    }

    /**
     * Creates an instance of RemoveGreater command
     * @param arguments Arguments
     * @param studyGroups The collection it`s working with
     */
    public RemoveGreater(ArrayList<String> arguments, LinkedHashSet<StudyGroup> studyGroups) {
        super("remove_greater", arguments);
        this.studyGroups = studyGroups;
    }

    /**
     * Executes the RemoveGreater command
     * @return Status
     */
    @Override
    public Status execute() {
        if (getArguments() == null || (arguments.size() != 12 && arguments.size() != 10 && arguments.size() != 6)) {
            status = Status.INCORRECT_ARGUMENTS;
            return Status.INCORRECT_ARGUMENTS;
        } else {
            StudyGroup studyGroup;
            try {
                Location location = null;
                Person admin = null;

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

                studyGroups.removeIf(sg -> sg.compareTo(studyGroup) < 0);
            } catch (ValueOutOfBoundsException voob) {
                status = Status.FAILED;
                return Status.FAILED;
            }
            status = Status.OK;
            return Status.OK;
        }
    }
}
