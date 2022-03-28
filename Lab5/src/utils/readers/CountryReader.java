package utils.readers;

import data.Country;
import main.MainLab5;

import java.util.*;

/**
 * Считыватель страны.
 */
public class CountryReader {
    public static boolean checkExist(String toBeContained) {
        return Arrays.stream(Country.values()).anyMatch((country) -> country.name().equals(toBeContained));
    }

    public static Country read(String messageForConsole, boolean canBeNull) {
        Scanner in = MainLab5.scanner;
        System.out.print(messageForConsole + " Выберите страну из представленных("
                + Arrays.asList(Country.values()) + ") или оставьте пустым: ");
        String toBeContained = in.nextLine().trim();

        if (canBeNull && toBeContained.equals("") || canBeNull && toBeContained.equals("null")) return null;

        if ((!checkExist(toBeContained)) && !canBeNull && !toBeContained.equals("") ||
                !canBeNull && toBeContained.equals("") || canBeNull && !toBeContained.equals("")) {
            while (!checkExist(toBeContained)) {
                System.out.print("Вы ввели несуществующее значение из представленных. Попробуйте снова: ");
                toBeContained = in.nextLine().trim();
                if (canBeNull && toBeContained.equals("") || canBeNull && toBeContained.equals("null")) return null;
                checkExist(toBeContained);
            }
        }


        return Enum.valueOf(Country.class, toBeContained);
    }

    public static Country readFromScript(Scanner scanner, boolean canBeNull) {
        String toBeContained = scanner.nextLine().trim();

        if ((!checkExist(toBeContained)) && !canBeNull && !toBeContained.equals("") ||
                !canBeNull && toBeContained.equals("")) {
            throw new IllegalArgumentException();
        } else if (canBeNull && toBeContained.equals("") || canBeNull && toBeContained.equals("null"))
            return null;
        else return Enum.valueOf(Country.class, toBeContained);
    }
}
