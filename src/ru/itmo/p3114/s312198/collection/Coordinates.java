package ru.itmo.p3114.s312198.collection;

/**
 * StudyGroup Coordinates class
 */
public class Coordinates {
    private long x;
    private Double y; //Максимальное значение поля: 426, Поле не может быть null

    /**
     * Creates an instance of Coordinates
     * @param X long X coordinate
     * @param Y Double Y coordinate (less than 427)
     */
    public Coordinates(long X, Double Y) {
        x = X;
        y = Y;
    }

    /**
     * Sets X value
     * @param x X coordinate
     */
    public void setX(long x) {
        this.x = x;
    }

    /**
     * Sets Y value
     * @param y Y coordinate
     */
    public void setY(Double y) {
        this.y = y;
    }

    /**
     * @return X coordinate
     */
    public long getX() {
        return x;
    }

    /**
     * @return Y coordinate
     */
    public Double getY() {
        return y;
    }
}
