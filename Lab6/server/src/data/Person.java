package data;

import utils.CollectionManager;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Person implements Comparable<Person>, Serializable {
    private static final long serialVersionUID = -9045846405562465921L;
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private final java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private double height; //Значение поля должно быть больше 0
    private String passportID; //Поле не может быть null
    private Color hairColor; //Поле не может быть null
    private Country nationality; //Поле может быть null
    private Location location; //Поле не может быть null

    // конструктор для создания объекта на основе данных прочитаннных из файла
    public Person(Integer id, String name, Coordinates coordinates, ZonedDateTime creationDate, double height,
                  String passportID, Color hairColor, Country nationality, Location location) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.height = height;
        this.passportID = passportID;
        this.hairColor = hairColor;
        this.nationality = nationality;
        this.location = location;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public double getHeight() {
        return height;
    }

    public String getPassportID() {
        return passportID;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    public void setHairColor(Color hairColor) {
        this.hairColor = hairColor;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, coordinates, height, passportID, hairColor, nationality, location);
    }

    @Override
    public int compareTo(Person person) {
//        return this.name.compareTo(person.name);
        return Double.compare(person.height, this.height);
    }

    @Override
    public String toString() {
        return "Person:\n" +
                "\tid: " + id +
                "\n\tname: " + name +
                "\n\tcoordinates: " + coordinates.toString() +
                "\n\tcreationDate: " + DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm").format(creationDate) +
                "\n\theight: " + height +
                "\n\tpassportID: " + passportID +
                "\n\thairColor: " + hairColor +
                "\n\tnationality: " + nationality +
                "\n\tlocation: " + location.toString();
    }
}
