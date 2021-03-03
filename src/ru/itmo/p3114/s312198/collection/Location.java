package ru.itmo.p3114.s312198.collection;

public class Location {
    private float x;
    private float y;
    private float z;
    private String name; //Строка не может быть пустой, Поле может быть null

    public Location() {
    }

    public Location(float X, float Y, float Z) {
        x = X;
        y = Y;
        z = Z;
        name = null;
    }

    public Location(float X, float Y, float Z, String Name) {
        this(X, Y, Z);
        name = Name;
    }

    public Location(Location loc, String name) {
        x = loc.getX();
        y = loc.getY();
        z = loc.getZ();
        this.name = name;
    }

    public void setCoords(float X, float Y, float Z) {
        x = X;
        y = Y;
        z = Z;
    }

    public void setCoords(Location loc) {
        if (loc != null) {
            setCoords(loc.getX(), loc.getY(), loc.getZ());
        }
    }

    public void setName(String Name) {
        name = Name;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return x + " " + y + " " + z + " " + (name == null ? "" : name);
    }

    public String toReadableLine() {
        return x + "," + y + "," + z + "," + name;
    }
}
