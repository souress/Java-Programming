package data;

public class Coordinates {
    private Long x; //Максимальное значение поля: 51, Поле не может быть null
    private double y;

    public Coordinates(Long x, double y) {
        this.x = x;
        this.y =y;
    }

    public Long getCoordinateX() {
        return x;
    }

    public double getCoordinateY() {
        return y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
