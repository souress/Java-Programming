package utils;

import data.*;
import utils.readers.*;

/**
 * Класс, содержащий методы для создание объекта Person
 */
public class PersonCreator {
    public static Person createPerson() {
        String name = StringReader.read("Введите name: ", false);
        Long coordinateX = RefLongReader.read("Введите coordinateX: ", false, 51, "MAX");
        double coordinateY = PrimitiveDoubleReader.read("Введите coordinateY: ");
        double height = PrimitiveDoubleReader.read("Введите height: ", 0, "MIN");
        String passportID = StringReader.read("Введите passportID: ", false);
        Color hairColor = ColorReader.read("", false);
        Country nationality = CountryReader.read("", true);
        long locationX = PrimitiveLongReader.read("Введите locationX: ");
        float locationY = PrimitiveFloatReader.read("Введите locationY: ");
        Long locationZ = RefLongReader.read("Введите locationZ: ", false);

        return new Person(name, new Coordinates(coordinateX, coordinateY), height, passportID, hairColor, nationality,
                new Location(locationX, locationY, locationZ));
    }
}
