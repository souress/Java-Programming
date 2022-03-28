package main;

import utils.*;

import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * Main класс клиента
 * @author Клименко Кирилл P3114
 */
public class Main {
    public static Scanner scanner = new Scanner(new InputStreamReader(System.in, Charset.defaultCharset()));
    public static void main(String[] args) {
        try {
            ConsoleManager consoleManager = new ConsoleManager();
            consoleManager.startInteractiveMode(args[0], args[1], args[2]);
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Введено некорректное количество аргументов.\n" +
                    "Требуются 3 аргумента: адрес узла, порт, задержка (в мс)");
        }
    }
}
