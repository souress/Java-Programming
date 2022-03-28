package data;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

public class Person implements Comparable<Person>, Serializable {
    private static final long serialVersionUID = -9045846405562465921L;
    private final String name; //Поле не может быть null, Строка не может быть пустой
    private final Coordinates coordinates; //Поле не может быть null
    private final java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private final double height; //Значение поля должно быть больше 0
    private final String passportID; //Поле не может быть null
    private final Color hairColor; //Поле не может быть null
    private final Country nationality; //Поле может быть null
    private final Location location; //Поле не может быть null

    // конструктор для команд консоли
    public Person(String name, Coordinates coordinates, double height,
                  String passportID, Color hairColor, Country nationality, Location location) {
        this.name = name;
        this.coordinates = coordinates;
        creationDate = ZonedDateTime.now();
        this.height = height;
        this.passportID = passportID;
        this.hairColor = hairColor;
        this.nationality = nationality;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, coordinates, height, passportID, hairColor, nationality, location);
    }

    @Override
    public int compareTo(Person person) {
        Double heightThis = this.height;
        Double heightPerson = person.height;
        int result = this.name.compareTo(person.name);
        if (result == 0) {
            result = heightPerson.compareTo(heightThis);
        }
        return result;
    }

    @Override
    public String toString() {
        return "Person:\n" +
                "\n\tname: " + name +
                "\n\tcoordinates: " + coordinates.toString() +
                "\n\tcreationDate: " + creationDate +
                "\n\theight=" + height +
                "\n\tpassportID: " + passportID +
                "\n\thairColor: " + hairColor +
                "\n\tnationality: " + nationality +
                "\n\tlocation: " + location.toString();
    }
}
