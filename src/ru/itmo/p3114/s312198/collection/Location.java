package ru.itmo.p3114.s312198.collection;

/**
 * Location class.
 * Describes any person`s location
 */
public class Location {
    private float x;
    private float y;
    private float z;
    private String name; //Строка не может быть пустой, Поле может быть null

    /**
     * Creates an empty instance of Location
     */
    public Location() {
    }

    /**
     * Creates an instance of a nameless Location
     * @param X X coordinate
     * @param Y Y coordinate
     * @param Z Z coordinate
     */
    public Location(float X, float Y, float Z) {
        x = X;
        y = Y;
        z = Z;
        name = null;
    }

    /**
     * Creates an instance of Location
     * @param X X coordinate
     * @param Y Y coordinate
     * @param Z Z coordinate
     * @param Name Name
     */
    public Location(float X, float Y, float Z, String Name) {
        this(X, Y, Z);
        name = Name;
    }

    /**
     * Creates an instance of Location using a nameless Location
     * @param loc Nameless location (the instance will have it`s coordinates)
     * @param name Name
     */
    public Location(Location loc, String name) {
        x = loc.getX();
        y = loc.getY();
        z = loc.getZ();
        this.name = name;
    }

    /**
     * Sets coordinates
     * @param X X coordinate
     * @param Y Y coordinate
     * @param Z Z coordinate
     */
    public void setCoords(float X, float Y, float Z) {
        x = X;
        y = Y;
        z = Z;
    }

    /**
     * Sets coordinates
     * @param loc Sets loc`s coordinates to the instance
     */
    public void setCoords(Location loc) {
        if (loc != null) {
            setCoords(loc.getX(), loc.getY(), loc.getZ());
        }
    }

    /**
     * Sets name
     * @param Name Name
     */
    public void setName(String Name) {
        name = Name;
    }

    /**
     * @return X coordinate
     */
    public float getX() {
        return x;
    }

    /**
     * @return Y coordinate
     */
    public float getY() {
        return y;
    }

    /**
     * @return Z coordinate
     */
    public float getZ() {
        return z;
    }

    /**
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * Transforms Location into a String
     * @return String (x y z name)
     */
    @Override
    public String toString() {
        return x + " " + y + " " + z + " " + (name == null ? "" : name);
    }

    /**
     * Transforms Location into a readable String
     * @return String (x,y,z,name)
     */
    public String toReadableLine() {
        return x + "," + y + "," + z + "," + name;
    }
}
