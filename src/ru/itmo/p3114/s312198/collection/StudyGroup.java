package ru.itmo.p3114.s312198.collection;

import ru.itmo.p3114.s312198.util.IdGenerator;

import java.time.LocalDate;

/**
 * Study group class
 */
public class StudyGroup implements Comparable<StudyGroup> {
    private final long id = IdGenerator.getNextId(); //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate = LocalDate.now(); //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer studentsCount; //Значение поля должно быть больше 0, Поле может быть null
    private int shouldBeExpelled; //Значение поля должно быть больше 0
    private int transferredStudents; //Значение поля должно быть больше 0
    private FormOfEducation formOfEducation; //Поле не может быть null
    private Person groupAdmin; //Поле может быть null

    public StudyGroup() {
    }

    public StudyGroup(String n, Coordinates c, Integer sc, int se, int ts, FormOfEducation fe, Person ga) {
        setName(n);
        setCoordinates(c);
        setStudentsCount(sc);
        setShouldBeExpelled(se);
        setTransferredStudents(ts);
        setFormOfEducation(fe);
        setGroupAdmin(ga);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate(LocalDate date) {
        creationDate = date;
    }

    public void setStudentsCount(Integer studentsCount) {
        this.studentsCount = studentsCount;
    }

    public void setShouldBeExpelled(Integer shouldBeExpelled) {
        this.shouldBeExpelled = shouldBeExpelled;
    }

    public void setTransferredStudents(Integer transferredStudents) {
        this.transferredStudents = transferredStudents;
    }

    public void setFormOfEducation(FormOfEducation formOfEducation) {
        this.formOfEducation = formOfEducation;
    }

    public void setGroupAdmin(Person groupAdmin) {
        this.groupAdmin = groupAdmin;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Integer getStudentsCount() {
        return studentsCount;
    }

    public int getShouldBeExpelled() {
        return shouldBeExpelled;
    }

    public int getTransferredStudents() {
        return transferredStudents;
    }

    public FormOfEducation getFormOfEducation() {
        return formOfEducation;
    }

    public Person getGroupAdmin() {
        return groupAdmin;
    }

    public String toReadableString() {
        return "ID: " + id + "\n\tName: " + name + "\n\tCoordinates: (" + coordinates.getX() + "; " + coordinates.getY() +
                ")\n\tCreation date (YYYY-MM-DD): " + creationDate.getYear() + "-" +
                String.format("%02d", creationDate.getMonth().getValue()) + "-" +
                String.format("%02d", creationDate.getDayOfMonth()) + "\n\tStudents count: " +
                (studentsCount == null ? "-" : studentsCount) +
                "\n\tShould be expelled: " + shouldBeExpelled + "\n\tTransferred students: " + transferredStudents +
                "\n\tForm of education: " + formOfEducation.toString() + "\n\tGroup admin: " +
                (groupAdmin == null ? "-" :
                "\n\t\tName: " + groupAdmin.getName() + "\n\t\tHeight: " + groupAdmin.getHeight() +
                "\n\t\tHair color: " +
                ((groupAdmin.getHairColor() == null || groupAdmin.getHairColor() == Color.UNDEFINED) ? "-" : groupAdmin.getHairColor().toString()) +
                "\n\t\tNationality: " + groupAdmin.getNationality().toString() + "\n\t\tLocation: " +
                (groupAdmin.getLocation() == null ? "-" :
                "\n\t\t\tCoordinates: (" + groupAdmin.getLocation().getX() + "; " +
                groupAdmin.getLocation().getY() + "; " + groupAdmin.getLocation().getZ() + ")" +
                "\n\t\t\tName: " + (groupAdmin.getLocation().getName() == null ? "-" : groupAdmin.getLocation().getName())));
    }

    @Override
    public String toString() {
        return id + ": " + toCSVLine();
    }

    public String toCSVLine() {
        return name + "," + coordinates.getX() + "," + coordinates.getY() + "," + creationDate.getYear() + "-" +
                String.format("%02d", creationDate.getMonth().getValue()) + "-" +
                String.format("%02d", creationDate.getDayOfMonth()) +
                "," + studentsCount + "," + shouldBeExpelled + "," + transferredStudents + "," +
                formOfEducation.toString() + "," +
                (groupAdmin == null ? " ,0,UNDEFINED,UNDEFINED,0.0,0.0,0.0, " : groupAdmin.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            StudyGroup studyGroup = (StudyGroup) obj;
            return id == studyGroup.getId();
        }
    }

    @Override
    public int compareTo(StudyGroup o) {
        return name.compareTo(o.getName());
    }
}
