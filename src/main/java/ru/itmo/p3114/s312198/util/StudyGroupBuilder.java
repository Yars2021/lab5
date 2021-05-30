package ru.itmo.p3114.s312198.util;

import ru.itmo.p3114.s312198.collection.Coordinates;
import ru.itmo.p3114.s312198.collection.FormOfEducation;
import ru.itmo.p3114.s312198.collection.Person;
import ru.itmo.p3114.s312198.collection.StudyGroup;
import ru.itmo.p3114.s312198.exception.ValueOutOfBoundsException;

import java.time.LocalDate;

/**
 * Helps creating a new instance of StudyGroup
 */
public class StudyGroupBuilder {
    private final StudyGroup studyGroup;

    public StudyGroupBuilder() {
        studyGroup = new StudyGroup();
    }

    public StudyGroupBuilder addId(long id) {
        studyGroup.setId(id);
        return this;
    }

    public StudyGroupBuilder addName(String name) {
        studyGroup.setName(FieldParser.parseName(name));
        return this;
    }

    public StudyGroupBuilder addCoordinates(Coordinates coords) {
        if (coords.getY() == null || coords.getY() > 426) {
            studyGroup.setCoordinates(null);
        } else {
            studyGroup.setCoordinates(coords);
        }
        return this;
    }

    public StudyGroupBuilder addCoordinates(String coords) {
        studyGroup.setCoordinates(FieldParser.parseCoordinates(coords));
        return this;
    }

    public StudyGroupBuilder addCreationDate(LocalDate date) {
        studyGroup.setCreationDate(date);
        return this;
    }

    public StudyGroupBuilder addCreationDate(String date) {
        studyGroup.setCreationDate(FieldParser.parseDate(date));
        return this;
    }

    public StudyGroupBuilder addStudentsCount(Integer studentsCount) {
        if (studentsCount != null && studentsCount < 0) {
            studyGroup.setStudentsCount(null);
        } else {
            studyGroup.setStudentsCount(studentsCount);
        }
        return this;
    }

    public StudyGroupBuilder addStudentsCount(String studentsCount) {
        studyGroup.setStudentsCount(FieldParser.parseStudentsCount(studentsCount));
        return this;
    }

    public StudyGroupBuilder addShouldBeExpelled(int shouldBeExpelled) throws ValueOutOfBoundsException {
        if (shouldBeExpelled <= 0) {
            throw new ValueOutOfBoundsException("ShouldBeExpelled should be greater than 0. Current value is " + shouldBeExpelled);
        } else {
            studyGroup.setShouldBeExpelled(shouldBeExpelled);
            return this;
        }
    }

    public StudyGroupBuilder addShouldBeExpelled(String shouldBeExpelled) throws ValueOutOfBoundsException {
        studyGroup.setShouldBeExpelled(FieldParser.parseShouldBeExpelled(shouldBeExpelled));
        return this;
    }

    public StudyGroupBuilder addTransferredStudents(int transferredStudents) throws ValueOutOfBoundsException {
        if (transferredStudents <= 0) {
            throw new ValueOutOfBoundsException("TransferredStudents should be greater than 0. Current value is " + transferredStudents);
        } else {
            studyGroup.setTransferredStudents(transferredStudents);
            return this;
        }
    }

    public StudyGroupBuilder addTransferredStudents(String transferredStudents) throws ValueOutOfBoundsException {
        studyGroup.setTransferredStudents(FieldParser.parseTransferredStudents(transferredStudents));
        return this;
    }

    public StudyGroupBuilder addFormOfEducation(FormOfEducation formOfEducation) {
        studyGroup.setFormOfEducation(formOfEducation);
        return this;
    }

    public StudyGroupBuilder addFormOfEducation(String formOfEducation) {
        studyGroup.setFormOfEducation(FieldParser.parseFormOfEducation(formOfEducation));
        return this;
    }

    public StudyGroupBuilder addGroupAdmin(Person admin) {
        studyGroup.setGroupAdmin(admin);
        return this;
    }

    /**
     * @return A new StudyGroup
     */
    public StudyGroup toStudyGroup() {
        return studyGroup;
    }
}
