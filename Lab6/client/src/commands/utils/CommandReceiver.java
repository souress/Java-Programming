package commands.utils;

import client.*;
import commands.*;
import commands.serialized.*;
import data.*;
import utils.*;
import utils.readers.*;
import utils.readers.StringReader;

import java.io.*;
import java.nio.channels.SocketChannel;
import java.util.*;

import static client.Reciever.receive;

/**
 * Ресивер(получатель), описывает основную логику команд, при надобности делегирует ее менеджеру коллекции.
 */
public class CommandReceiver {
    private final CommandInvoker commandInvoker;
    private final LinkedHashSet<String> pathSet;
    private final Sender sender;
    private final SocketChannel socketChannel;
    private final Integer delay;

    public CommandReceiver(CommandInvoker commandInvoker, Sender sender, SocketChannel socketChannel, Integer delay) {
        this.commandInvoker = commandInvoker;
        this.sender = sender;
        this.socketChannel = socketChannel;
        this.delay = delay;
        pathSet = new LinkedHashSet<>();
    }

    public void add() throws IOException, InterruptedException, ClassNotFoundException {
        sender.sendObject(new SerializedObjectCommand(new AddCommand(), PersonCreator.createPerson()));
        Thread.sleep(delay);
        System.out.println(receive(socketChannel));
    }

    public void help() {
        for (AbstractCommand command : commandInvoker.getCommandMap().values()) {
            System.out.println(command.writeInfo());
        }
    }

    public void info() throws IOException, InterruptedException, ClassNotFoundException {
        sender.sendObject(new SerializedSimpleCommand(new InfoCommand()));
        Thread.sleep(delay);
        System.out.println(receive(socketChannel));
    }

    public void show() throws IOException, InterruptedException, ClassNotFoundException {
        sender.sendObject(new SerializedSimpleCommand(new ShowCommand()));
        Thread.sleep(delay);
        System.out.println(receive(socketChannel));
    }

    public void clear() throws IOException, InterruptedException, ClassNotFoundException {
        sender.sendObject(new SerializedSimpleCommand(new ClearCommand()));
        Thread.sleep(delay);
        System.out.println(receive(socketChannel));
    }

    public void execute_script(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        class ScriptPersonCreator {
            public Person createScriptPerson(Scanner scanner) {
                String name = StringReader.readFromScript(scanner, false);
                Long coordinateX = RefLongReader.readFromScript(scanner, 51, "MAX");
                double coordinateY = PrimitiveDoubleReader.readFromScript(scanner);
                double height = PrimitiveDoubleReader.readFromScript(scanner, 0, "MIN");
                String passportID = StringReader.readFromScript(scanner, true);
                Color hairColor = ColorReader.readFromScript(scanner, false);
                Country nationality = CountryReader.readFromScript(scanner, true);
                long locationX = PrimitiveLongReader.readFromScript(scanner);
                float locationY = PrimitiveFloatReader.readFromScript(scanner);
                Long locationZ = RefLongReader.readFromScript(scanner);

                return new Person(name, new Coordinates(coordinateX, coordinateY), height, passportID, hairColor, nationality,
                        new Location(locationX, locationY, locationZ));
            }
        }

        if (args.length != 2) System.out.println("Некорректное количество аргументов. Для справки напишите help.");
        else {
            Scanner scanner = new Scanner(new FileReader(args[1]));
            while (scanner.hasNextLine()) {
                String command = scanner.nextLine();
                if (command.matches("add|update|add_if_max|remove_greater|remove_lower")) {
                    Person person;
                    ScriptPersonCreator scriptPersonCreator = new ScriptPersonCreator();
                    switch (command) {
                        case "add":
                            try {
                                person = scriptPersonCreator.createScriptPerson(scanner);
                                sender.sendObject(new SerializedObjectCommand(new AddCommand(), person));
                                Thread.sleep(delay);
                                System.out.println(receive(socketChannel));
                            } catch (NullPointerException | IllegalArgumentException exception) {
                                System.out.println("Параметры команды add не прошли валидацию. Команда пропущена.");
                                continue;
                            }
                            break;
                        case "update":
                            try {
                                String id = scanner.nextLine();
                                sender.sendObject(new SerializedArgumentCommand(new UpdateCommand(), id));
                                Thread.sleep(delay);
                                System.out.println(receive(socketChannel));
                            } catch (NullPointerException | IllegalArgumentException exception) {
                                System.out.println("Параметры команды update не прошли валидацию. Команда пропущена.");
                                continue;
                            }
                            break;
                        case "add_if_max":
                            try {
                                person = scriptPersonCreator.createScriptPerson(scanner);
                                sender.sendObject(new SerializedObjectCommand(new AddIfMaxCommand(), person));
                                Thread.sleep(delay);
                                System.out.println(receive(socketChannel));
                            } catch (NullPointerException | IllegalArgumentException exception) {
                                System.out.println("Параметры команды add_if_max не прошли валидацию. Команда пропущена.");
                                continue;
                            }
                            break;
                        case "remove_greater":
                            try {
                                person = scriptPersonCreator.createScriptPerson(scanner);
                                sender.sendObject(new SerializedObjectCommand(new RemoveGreaterCommand(), person));
                                Thread.sleep(delay);
                                System.out.println(receive(socketChannel));
                            } catch (NullPointerException | IllegalArgumentException exception) {
                                System.out.println("Параметры команды remove_greater не прошли валидацию. Команда пропущена.");
                                continue;
                            }
                            break;
                        case "remove_lower":
                            try {
                                person = scriptPersonCreator.createScriptPerson(scanner);
                                sender.sendObject(new SerializedObjectCommand(new RemoveLowerCommand(), person));
                                Thread.sleep(delay);
                                System.out.println(receive(socketChannel));
                            } catch (NullPointerException | IllegalArgumentException exception) {
                                System.out.println("Параметры команды remove_lower не прошли валидацию. Команда пропущена.");
                                continue;
                            }
                            break;
                    }
                } else if (command.contains("execute_script")) {
                    pathSet.add(args[1]);
                    dante:
                    {
                        try {
                            for (String path : pathSet) {
                                if (command.contains(path))
                                    throw new StackOverflowError();
                            }
                        } catch (StackOverflowError error) {
                            System.out.println("Обнаружен рекурсивный вызов скрипта! Попытка переполнения стека пресечена.");
                        } finally {
                            for (String path : pathSet) {
                                if (command.contains(path))
                                    if (scanner.hasNextLine())
                                        command = scanner.nextLine();
                                    else break dante;
                            }
                            commandInvoker.executeCommand(command.split(" "));
                        }
                    }
                } else if (commandInvoker.getCommandMap().containsKey(command))
                    commandInvoker.executeCommand(command.split(" "));
            }
        }
    }

    public void removeByID(String id) throws InterruptedException, IOException, ClassNotFoundException {
        sender.sendObject(new SerializedArgumentCommand(new RemoveByIdCommand(), id));
        Thread.sleep(delay);
        System.out.println(receive(socketChannel));
    }

    public boolean checkExist(String id) throws IOException, InterruptedException, ClassNotFoundException {
        sender.sendObject(new SerializedArgumentCommand(new CheckExistCommand(), id));
        Thread.sleep(delay);
        return receive(socketChannel).equals("true");
    }

    public void update(String id) throws InterruptedException, IOException, ClassNotFoundException {
        try {
            Integer personId = Integer.parseInt(id);
            if (!checkExist(id)) System.out.println("Элемента с таким id нет в коллекции.");
            else {
                sender.sendObject(new SerializedCombinedCommand(new UpdateCommand(), PersonCreator.createPerson(), id));
                Thread.sleep(delay);
                System.out.println(receive(socketChannel));
            }
        } catch (NumberFormatException e) {
            System.out.println("Недопустимый аргумент!");
        }
    }

    public void removeGreater() throws IOException, InterruptedException, ClassNotFoundException {
        sender.sendObject(new SerializedObjectCommand(new RemoveGreaterCommand(), PersonCreator.createPerson()));
        Thread.sleep(delay);
        System.out.println(receive(socketChannel));
    }

    public void removeLower() throws InterruptedException, IOException, ClassNotFoundException {
        sender.sendObject(new SerializedObjectCommand(new RemoveLowerCommand(), PersonCreator.createPerson()));
        Thread.sleep(delay);
        System.out.println(receive(socketChannel));
    }

    public void addIfMax() throws InterruptedException, IOException, ClassNotFoundException {
        sender.sendObject(new SerializedObjectCommand(new AddIfMaxCommand(), PersonCreator.createPerson()));
        Thread.sleep(delay);
        System.out.println(receive(socketChannel));
    }

    public void printFieldDescendingHeight() throws IOException, InterruptedException, ClassNotFoundException {
        sender.sendObject(new SerializedSimpleCommand(new PrintFieldDescendingHeightCommand()));
        Thread.sleep(delay);
        System.out.println(receive(socketChannel));
    }

    public void printFieldDescendingPassportID() throws IOException, InterruptedException, ClassNotFoundException {
        sender.sendObject(new SerializedSimpleCommand(new PrintFieldDescendingPassportIdCommand()));
        Thread.sleep(delay);
        System.out.println(receive(socketChannel));
    }

    public void filterStartsWithName(String name) throws IOException, InterruptedException, ClassNotFoundException {
        sender.sendObject(new SerializedArgumentCommand(new FilterStartsWithNameCommand(), name));
        Thread.sleep(delay);
        System.out.println(receive(socketChannel));
    }

    public void exit() {
        System.out.println("Выход...");
        System.exit(0);
    }
}
