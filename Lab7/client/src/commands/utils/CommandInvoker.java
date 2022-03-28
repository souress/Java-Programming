package commands.utils;

import commands.AbstractCommand;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Инвокер(вызыватель), выполняет команды. Хранит зарегистрированные команды.
 */
public class CommandInvoker {
    private final HashMap<String, AbstractCommand> commandMap = new HashMap<>();

    public void register(String commandName, AbstractCommand command) {
        commandMap.put(commandName, command);
    }

    public void executeCommand(String[] commandName) {
        try {
            if (commandName.length > 0) {
                AbstractCommand command = commandMap.get(commandName[0]);
                command.execute(commandName);
            } else System.out.println("Вы не ввели команду.");
        } catch (NullPointerException e) {
            System.out.println("Не существует команды " + commandName[0] + ". Для справки используйте – help");
        } catch (IllegalStateException | ClassNotFoundException | InterruptedException | IOException exception) {
            if (exception.getMessage().equals("Connection reset by peer")) {
                System.out.println("Ошибка соединения с сервером");
                System.exit(0);
            }
            System.out.println(exception.getMessage());
        }
    }

    public  HashMap<String, AbstractCommand> getCommandMap() {
        return commandMap;
    }
}
