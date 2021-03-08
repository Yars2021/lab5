package ru.itmo.p3114.s312198.collection;

/**
 * Person class.
 * Describes StudyGroup`s admin
 */
public class Person implements Comparable<Person> {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private long height; //Значение поля должно быть больше 0
    private Color hairColor; //Поле может быть null
    private Country nationality; //Поле не может быть null
    private Location location; //Поле может быть null

    /**
     * Creates an empty instance of Person
     */
    public Person() {
    }

    /**
     * Creates an instance of Person
     * @param Name Name
     * @param Height Height (greater than 0)
     * @param Nationality Nationality (uses Country enum)
     */
    public Person(String Name, long Height, Country Nationality) {
        name = Name;
        height = Height;
        nationality = Nationality;
    }

    /**
     * Creates an instance of Person
     * @param Name Name
     * @param Height Height (greater than 0)
     * @param HairColor Hair color (uses Color enum)
     * @param Nationality Nationality (uses Country enum)
     * @param Loc An instance of Location (can be null)
     */
    public Person(String Name, long Height, Color HairColor, Country Nationality, Location Loc) {
        this(Name, Height, Nationality);
        hairColor = HairColor;
        location = Loc;
    }

    /**
     * Sets name
     * @param Name Name
     */
    public void setName(String Name) {
        name = Name;
    }

    /**
     * Sets height
     * @param Height Height
     */
    public void setHeight(Long Height) {
        height = Height;
    }

    /**
     * Sets hair color
     * @param HairColor Hair color
     */
    public void setHairColor(Color HairColor) {
        hairColor = HairColor;
    }

    /**
     * Sets nationality
     * @param Nationality Nationality
     */
    public void setNationality(Country Nationality) {
        nationality = Nationality;
    }

    /**
     * Sets Location
     * @param Loc Instance of Location
     */
    public void setLocation(Location Loc) {
        location = Loc;
    }

    /**
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * @return Height
     */
    public long getHeight() {
        return height;
    }

    /**
     * @return Hair color
     */
    public Color getHairColor() {
        return hairColor;
    }

    /**
     * @return Nationality
     */
    public Country getNationality() {
        return nationality;
    }

    /**
     * @return Location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Transforms Person int a String
     * @return String
     */
    @Override
    public String toString() {
        return name + "," + height + "," + hairColor.toString() + "," + nationality.toString() + "," +
                (location == null ? "0.0,0.0,0.0, " : location.toReadableLine());
    }

    /**
     * Transforms Person into a readable line showing all the fields
     * @return String
     */
    public String toReadableLine() {
        return "Name: " + name + "\nHeight: " + height + "\nHair color: " + hairColor.toString() +
                "\nNationality: " + nationality.toString() + "\nLocation: " +
                (location == null ? "-" : location.toReadableLine());
    }

    /**
     * Compares the instance to o
     * @param o 2-nd Person
     * @return 0 (this == o), > 0 (this > 0), < 0 (this < 0)
     */
    @Override
    public int compareTo(Person o) {
        return name.compareTo(o.getName());
    }
}
