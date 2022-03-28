package utils.readers;

import main.MainLab5;

import java.util.Scanner;

/**
 * Считыватель примитива float.
 */
public class PrimitiveFloatReader {
    public static float read(String messageForConsole, float limit, String type) {
        System.out.print(messageForConsole);
        Scanner sc = MainLab5.scanner;
        float result = 0;
        boolean end = false;
        while (!end) {
            try {
                result = Float.parseFloat(sc.nextLine().trim());
                switch (type) {
                    case ("NO LIMIT"): break;
                    case ("MIN"):
                        if (result > limit) end = true;
                        else System.out.print("Вы ввели не подходящее значение. " + "Оно должно быть больше " + limit + ". Попробуйте снова: ");
                        break;
                    case ("MAX"):
                        if (result < limit) end = true;
                        else System.out.print("Вы ввели не подходящее значение. " + "Оно должно быть меньше " + limit + ". Попробуйте снова: ");
                        break;
                }
            } catch (NumberFormatException ex) {
                System.out.print("Вы должны ввести число типа float, попробуйте снова: ");
            }
        }
        return result;
    }

    public static float read(String messageForConsole) {
        System.out.print(messageForConsole);
        Scanner sc = MainLab5.scanner;
        float result = 0;
        boolean end = false;
        while (!end) {
            try {
                result = Float.parseFloat(sc.nextLine().trim());
                end = true;
            } catch (NumberFormatException ex) {
                System.out.print("Вы должны ввести число типа float, попробуйте снова: ");
            }
        }
        return result;
    }

    public static float readFromScript(Scanner scanner, double limit, String type) {
        float result;
        result = Float.parseFloat(scanner.nextLine().trim());
        switch (type) {
            case ("MIN"):
                if (result > limit)
                    return result;
                else throw new IllegalArgumentException();
            case ("MAX"):
                if (result < limit)
                    return result;
                else throw new IllegalArgumentException();
        }
        return result;
    }

    public static float readFromScript(Scanner scanner) {
        float result;
        try {
            result = Float.parseFloat(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
        return result;
    }
}
