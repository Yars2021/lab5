package ru.itmo.p3114.s312198.collection;

/**
 * StudyGroup Coordinates class
 */
public class Coordinates {
    private long x;
    private Double y; //Максимальное значение поля: 426, Поле не может быть null

    public Coordinates(long X, Double Y) {
        x = X;
        y = Y;
    }

    public void setX(long x) {
        this.x = x;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public long getX() {
        return x;
    }

    public Double getY() {
        return y;
    }
}
