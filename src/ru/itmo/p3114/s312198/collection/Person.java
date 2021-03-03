package ru.itmo.p3114.s312198.collection;

import java.util.Comparator;

public class Person implements Comparable<Person> {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private long height; //Значение поля должно быть больше 0
    private Color hairColor; //Поле может быть null
    private Country nationality; //Поле не может быть null
    private Location location; //Поле может быть null

    public Person() {
    }

    public Person(String Name, long Height, Country Nationality) {
        name = Name;
        height = Height;
        nationality = Nationality;
    }

    public Person(String Name, long Height, Color HairColor, Country Nationality, Location Loc) {
        this(Name, Height, Nationality);
        hairColor = HairColor;
        location = Loc;
    }

    public void setName(String Name) {
        name = Name;
    }

    public void setHeight(Long Height) {
        height = Height;
    }

    public void setHairColor(Color HairColor) {
        hairColor = HairColor;
    }

    public void setNationality(Country Nationality) {
        nationality = Nationality;
    }

    public void setLocation(Location Loc) {
        location = Loc;
    }

    public String getName() {
        return name;
    }

    public long getHeight() {
        return height;
    }

    public Color getHairColor() {
        return hairColor;
    }

    public Country getNationality() {
        return nationality;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return name + "," + height + "," + hairColor.toString() + "," + nationality.toString() + "," +
                (location == null ? "0.0,0.0,0.0, " : location.toReadableLine());
    }

    public String toReadableLine() {
        return "Name: " + name + "\nHeight: " + height + "\nHair color: " + hairColor.toString() +
                "\nNationality: " + nationality.toString() + "\nLocation: " +
                (location == null ? "-" : location.toReadableLine());
    }

    @Override
    public int compareTo(Person o) {
        return name.compareTo(o.getName());
    }
}
