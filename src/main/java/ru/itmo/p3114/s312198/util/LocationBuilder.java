package ru.itmo.p3114.s312198.util;

import ru.itmo.p3114.s312198.collection.Location;

/**
 * Helps creating a new instance of Location
 */
public class LocationBuilder {
    private final Location location;

    public LocationBuilder() {
        location = new Location();
    }

    public LocationBuilder addCoords(float x, float y, float z) {
        location.setCoords(x, y, z);
        return this;
    }

    public LocationBuilder addCoords(String coords) {
        location.setCoords(FieldParser.parseLocationCoords(coords));
        return this;
    }

    public LocationBuilder addName(String name) {
        location.setName(FieldParser.parseName(name));
        return this;
    }

    /**
     * @return A new Location
     */
    public Location toLocation() {
        return location;
    }
}
