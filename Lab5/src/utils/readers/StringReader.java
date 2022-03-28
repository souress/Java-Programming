package utils.readers;

import main.MainLab5;

import java.util.Scanner;

/**
 * Считыватель строк.
 */
public class StringReader {
    public static String read(String messageForConsole, boolean canBeNull) {
        Scanner in = MainLab5.scanner;
        System.out.print(messageForConsole);

        String readString = in.nextLine();

        while(!canBeNull && readString.equals("")) {
            System.out.print("Данное поле не может быть пустым. " + messageForConsole);
            readString = in.nextLine().trim();
        }

        if(canBeNull && readString.equals(""))
            readString = null;
        return readString;
    }

    public static String readFromScript(Scanner scanner, boolean canBeNull) {
        String readString = scanner.nextLine().trim();

        if(!canBeNull && readString.equals(""))
            throw new IllegalArgumentException();

        if(canBeNull && readString.equals(""))
            readString = null;
        return readString;
    }
}
