package utils.readers;

import main.MainLab5;

import java.util.Scanner;

/**
 * Считыватель примитива double.
 */
public class PrimitiveDoubleReader {
    public static double read(String messageForConsole, double limit, String type) {
        System.out.print(messageForConsole);
        Scanner sc = MainLab5.scanner;
        double result = 0;
        boolean end = false;
        while (!end) {
            try {
                result = Double.parseDouble(sc.nextLine().trim());
                switch (type) {
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
                System.out.print("Вы должны ввести число типа double, попробуйте снова: ");
            }
        }
        return result;
    }

    public static double read(String messageForConsole) {
        System.out.print(messageForConsole);
        Scanner sc = MainLab5.scanner;
        double result = 0;
        boolean end = false;
        while (!end) {
            try {
                result = Double.parseDouble(sc.nextLine().trim());
                end = true;
            } catch (NumberFormatException ex) {
                System.out.print("Вы должны ввести число типа double, попробуйте снова: ");
            }
        }
        return result;
    }

    public static double readFromScript(Scanner scanner, double limit, String type) {
        double result;
        result = Double.parseDouble(scanner.nextLine().trim());
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

    public static double readFromScript(Scanner scanner) {
        double result;
        try {
            result = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
        return result;
    }
}
