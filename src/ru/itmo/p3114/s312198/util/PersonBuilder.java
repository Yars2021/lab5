package ru.itmo.p3114.s312198.util;

import ru.itmo.p3114.s312198.collection.Color;
import ru.itmo.p3114.s312198.collection.Country;
import ru.itmo.p3114.s312198.collection.Location;
import ru.itmo.p3114.s312198.collection.Person;
import ru.itmo.p3114.s312198.exception.ValueOutOfBoundsException;

public class PersonBuilder {
    private final Person person;

    public PersonBuilder() {
        person = new Person();
    }

    public PersonBuilder addName(String name) {
        person.setName(FieldParser.parseName(name));
        return this;
    }

    public PersonBuilder addHeight(long height) {
        if (height > 0) {
            person.setHeight(height);
        }
        return this;
    }

    public PersonBuilder addHeight(String height) throws ValueOutOfBoundsException {
        person.setHeight(FieldParser.parseHeight(height));
        return this;
    }

    public PersonBuilder addHairColor(Color color) {
        person.setHairColor(color);
        return this;
    }

    public PersonBuilder addHairColor(String color) {
        person.setHairColor(FieldParser.parseHairColor(color));
        return this;
    }

    public PersonBuilder addNationality(Country country) {
        person.setNationality(country);
        return this;
    }

    public PersonBuilder addNationality(String country) {
        person.setNationality(FieldParser.parseNationality(country));
        return this;
    }

    public PersonBuilder addLocation(Location location) {
        person.setLocation(location);
        return this;
    }

    public Person toPerson() {
        return person;
    }
}
