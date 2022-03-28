package main;

import utils.*;

import java.io.IOException;

/**
 * Main класс приложения
 * @author Клименко Кирилл P3114
 */
public class Main {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(FileManager :: parseToXml));
        Controller controller = new Controller();
        try {
            FileManager fileManager = new FileManager(args[0]);
            fileManager.parseFromXml();
            controller.run(args[1]);
        } catch (ArrayIndexOutOfBoundsException | IOException | NullPointerException exception) {
            System.out.println("Введено некорректное количество аргументов.\n" +
                    "Требуются 2 аргумента: путь к коллекции и порт.\nПример: java -jar [jarName] [collectionPath] [port]");
        }
    }
}
