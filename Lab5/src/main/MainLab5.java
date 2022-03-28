package main;

import utils.*;

import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * Main класс приложения
 * @author Клименко Кирилл P3114
 */
public class MainLab5 {
    public static Scanner scanner = new Scanner(new InputStreamReader(System.in, Charset.defaultCharset()));
    public static void main(String[] args) {
        try {
            FileManager fileManager = new FileManager(args[0]);
            fileManager.parseFromXml();
            ConsoleManager consoleManager = new ConsoleManager();
            consoleManager.startInteractiveMode();
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Укажите путь к файлу через аргумент командной строки.");
        }
    }
}
