package ru.itmo.p3114.s312198.collection;

import ru.itmo.p3114.s312198.exception.IncorrectLineFormat;
import ru.itmo.p3114.s312198.util.IdGenerator;

import java.time.LocalDate;

/**
 * Study group class
 */
public class StudyGroup implements Comparable<StudyGroup> {
    private long id = IdGenerator.getNextId(); //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate = LocalDate.now(); //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer studentsCount; //Значение поля должно быть больше 0, Поле может быть null
    private int shouldBeExpelled; //Значение поля должно быть больше 0
    private int transferredStudents; //Значение поля должно быть больше 0
    private FormOfEducation formOfEducation; //Поле не может быть null
    private Person groupAdmin; //Поле может быть null

    /**
     * Creates an empty instance of StudyGroup
     */
    public StudyGroup() {
    }

    /**
     * Creates an instance of StudyGroup
     * @param n Name
     * @param c Coordinates
     * @param sc Students count
     * @param se The number of students that should be expelled
     * @param ts The number of transferred students
     * @param fe Form of education
     * @param ga Group admin
     */
    public StudyGroup(String n, Coordinates c, Integer sc, int se, int ts, FormOfEducation fe, Person ga) {
        setName(n);
        setCoordinates(c);
        setStudentsCount(sc);
        setShouldBeExpelled(se);
        setTransferredStudents(ts);
        setFormOfEducation(fe);
        setGroupAdmin(ga);
    }

    /**
     * Sets name
     * @param name Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets coordinates
     * @param coordinates Coordinates
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Sets creation date (this method is used when reading data from a CSV file)
     * @param date Date
     */
    public void setCreationDate(LocalDate date) {
        creationDate = date;
    }

    /**
     * Sets students count
     * @param studentsCount Students count
     */
    public void setStudentsCount(Integer studentsCount) {
        this.studentsCount = studentsCount;
    }

    /**
     * Sets the number of students, who should be expelled
     * @param shouldBeExpelled The number of these students
     */
    public void setShouldBeExpelled(Integer shouldBeExpelled) {
        this.shouldBeExpelled = shouldBeExpelled;
    }

    /**
     * Sets the number of transferred students
     * @param transferredStudents The number of transferred students
     */
    public void setTransferredStudents(Integer transferredStudents) {
        this.transferredStudents = transferredStudents;
    }

    /**
     * Sets form of education
     * @param formOfEducation For of education
     */
    public void setFormOfEducation(FormOfEducation formOfEducation) {
        this.formOfEducation = formOfEducation;
    }

    /**
     * Sets group admin
     * @param groupAdmin Group admin (an instance of Person)
     */
    public void setGroupAdmin(Person groupAdmin) {
        this.groupAdmin = groupAdmin;
    }

    /**
     * @return ID in collection
     */
    public long getId() {
        return id;
    }

    /**
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * @return Coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * @return Creation date
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * @return Students counts
     */
    public Integer getStudentsCount() {
        return studentsCount;
    }

    /**
     * @return The number of students, who should be expelled
     */
    public int getShouldBeExpelled() {
        return shouldBeExpelled;
    }

    /**
     * @return The number of transferred students
     */
    public int getTransferredStudents() {
        return transferredStudents;
    }

    /**
     * @return Form of education
     */
    public FormOfEducation getFormOfEducation() {
        return formOfEducation;
    }

    /**
     * @return Group admin
     */
    public Person getGroupAdmin() {
        return groupAdmin;
    }

    /**
     * Transforms StudyGroup into a readable String
     * @return String
     */
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
                "\n\t\t\tName: " + (groupAdmin.getLocation().getName() == null ? "-" : groupAdmin.getLocation().getName()))) + "\n";
    }

    /**
     * Transforms StudyGroup into a String
     * @return
     */
    @Override
    public String toString() {
        return id + ": " + toCSVLine();
    }

    /**
     * Converts StudyGroup into CSV
     * @return A valid CSV line
     */
    public String toCSVLine() {
        return name + "," + coordinates.getX() + "," + coordinates.getY() + "," + creationDate.getYear() + "-" +
                String.format("%02d", creationDate.getMonth().getValue()) + "-" +
                String.format("%02d", creationDate.getDayOfMonth()) +
                "," + studentsCount + "," + shouldBeExpelled + "," + transferredStudents + "," +
                formOfEducation.toString() + "," +
                (groupAdmin == null ? " ,0,UNDEFINED,UNDEFINED,0.0,0.0,0.0, " : groupAdmin.toString()) + ";" + id + ";";
    }

    /**
     * Compares the StudyGroup to obj
     * @param obj Another StudyGroup
     * @return true or false
     */
    @Override
    public boolean equals(Object obj) {
        if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            StudyGroup studyGroup = (StudyGroup) obj;
            return id == studyGroup.getId();
        }
    }

    /**
     * Compares StudyGroup to o
     * @param o 2-nd StudyGroup
     * @return 0 (this == o), > 0 (this > 0), < 0 (this < 0)
     */
    @Override
    public int compareTo(StudyGroup o) {
        return name.compareTo(o.getName());
    }

    public void setID(long id) throws IncorrectLineFormat {
        if (!IdGenerator.checkID(id)) {
            this.id = id;
            IdGenerator.addId(id);
        } else {
            throw new IncorrectLineFormat("Invalid ID");
        }
    }
}
