package utils.readers;

import data.Color;
import main.MainLab5;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Считыватель цвета.
 */
public class ColorReader {
    public static boolean checkExist(String toBeContained) {
        return Arrays.stream(Color.values()).anyMatch((color) -> color.name().equals(toBeContained));
    }

    public static Color read(String messageForConsole, boolean canBeNull) {
        Scanner in = MainLab5.scanner;
        System.out.print(messageForConsole + " Выберите цвет волос из представленных(" + Arrays.asList(Color.values()) + "): ");
        String toBeContained = in.nextLine().trim();

        if ((!checkExist(toBeContained)) && !canBeNull && !toBeContained.equals("") || !canBeNull && toBeContained.equals("") || canBeNull && !toBeContained.equals("")) {
            while (!checkExist(toBeContained)) {
                System.out.print("Вы ввели несуществующее значение из представленных. Попробуйте снова: ");
                toBeContained = in.nextLine().trim();
                checkExist(toBeContained);
            }
        }

        if (canBeNull && toBeContained.equals("")) { return null; }

        return Enum.valueOf(Color.class, toBeContained);
    }

    public static Color readFromScript(Scanner scanner, boolean canBeNull) {
        String toBeContained = scanner.nextLine().trim();

        if ((!checkExist(toBeContained)) && !canBeNull && !toBeContained.equals("")
                || !canBeNull && toBeContained.equals("") || canBeNull && !toBeContained.equals("")) {
            throw new IllegalArgumentException();
        }

        if (canBeNull && toBeContained.equals(""))
            return null;

        return Enum.valueOf(Color.class, toBeContained);
    }
}
