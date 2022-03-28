package utils.readers;

import main.MainLab5;

import java.util.Scanner;

/**
 * Считыватель примитива long.
 */
public class PrimitiveLongReader {
    public static long read(String messageForConsole, int limit, String type) {
        System.out.print(messageForConsole);
        Scanner sc = MainLab5.scanner;
        long result = 0;
        boolean end = false;
        while (!end) {
            try {
                result = Long.parseLong(sc.nextLine().trim());
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
                System.out.print("Вы должны ввести число типа long, попробуйте снова: ");
            }
        }
        return result;
    }

    public static long read(String messageForConsole) {
        System.out.print(messageForConsole);
        Scanner sc = MainLab5.scanner;
        long result = 0;
        boolean end = false;
        while (!end) {
            try {
                result = Long.parseLong(sc.nextLine().trim());
                end = true;
            } catch (NumberFormatException ex) {
                System.out.print("Вы должны ввести число типа long, попробуйте снова: ");
            }
        }
        return result;
    }

    public static long readFromScript(Scanner scanner, double limit, String type) {
        long result;
        result = Long.parseLong(scanner.nextLine().trim());
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

    public static long readFromScript(Scanner scanner) {
        long result;
        try {
            result = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
        return result;
    }
}
