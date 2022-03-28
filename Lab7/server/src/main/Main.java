package main;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import utils.*;

import java.io.IOException;

/**
 * Main класс приложения
 * @author Клименко Кирилл P3114
 */
public class Main {
    public static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        BasicConfigurator.configure();
        Controller controller = new Controller();
        try {
            controller.run(args[0]);
        } catch (ArrayIndexOutOfBoundsException | IOException | NullPointerException exception) {
            System.out.println("Введено некорректное количество аргументов.\n" +
                    "Требуются один аргумент: порт.\nПример: java -jar [jarName] [port]");
        }
    }
}
