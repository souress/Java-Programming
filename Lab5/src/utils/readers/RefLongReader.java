package utils.readers;

import main.MainLab5;

import java.util.Scanner;

/**
 * Считыватель класса-оболочки long.
 */
public class RefLongReader {
    public static Long read(String messageForConsole, boolean canBeNull, int limit, String type) {
        System.out.print(messageForConsole);
        Scanner sc = MainLab5.scanner;
        Long result = 0L;
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
                if (canBeNull && sc.nextLine().trim().equals("")) {
                    return null;
                }
                System.out.print("Вы должны ввести число типа Long, попробуйте снова: ");
            }
        }
        return result;
    }

    public static Long read(String messageForConsole, boolean canBeNull) {
        System.out.print(messageForConsole);
        Scanner sc = MainLab5.scanner;
        Long result = 0L;
        boolean end = false;
        while (!end) {
            try {
                result = Long.parseLong(sc.nextLine().trim());
                end = true;
            } catch (NumberFormatException ex) {
                if (canBeNull && sc.nextLine().trim().equals("")) return null;
                System.out.print("Вы должны ввести число типа Long, попробуйте снова: ");
            }
        }
        return result;
    }

    public static Long readFromScript(Scanner scanner) {
        String readString = scanner.nextLine().trim();

        if(readString.equals(""))
            throw new IllegalArgumentException();
        else return Long.parseLong(readString);
    }

    public static Long readFromScript(Scanner scanner, int limit, String type) {
        Long result;
        result = Long.parseLong(scanner.nextLine().trim());
        switch (type) {
            case ("NO LIMIT"): break;
            case ("MIN"):
                if (result > limit) return result;
                else throw new IllegalArgumentException();
            case ("MAX"):
                if (result <= limit) return result;
                else throw new IllegalArgumentException();
        }
        return result;
    }
}
