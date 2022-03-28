package commands.utils;

import commands.serialized.SerializedMessage;
import data.*;
import utils.*;
import utils.multithreading.Sender;

import static main.Main.logger;

/**
 * Ресивер(получатель), описывает основную логику команд, при надобности делегирует ее менеджеру коллекции.
 */
public class CommandReceiver {

    public CommandReceiver() {
    }

    public void register(String name, String password) {
        Controller.getManager().registerUser(name, password);
        Sender.sendObject(new SerializedMessage("Пользователь " + name + " зарегистрирован."));
        logger.debug("Пользователь " + name + " зарегистрирован.");
    }

    public void signIn(String name, String password) {
        Sender.sendObject(new SerializedMessage(Boolean.toString(Controller.getManager().checkUser(name, password))));
        if (Controller.getManager().checkUser(name, password))
            logger.debug("Пользователь " + name + " аутентифицирован");
    }

    public void checkRegistration(String name) {
        Sender.sendObject(new SerializedMessage(Boolean.toString(Controller.getManager().checkName(name))));
    }

    public void add(Object o, String username) {
        Person person = (Person) o;
        if (CollectionManager.add(person, username))
            Sender.sendObject(new SerializedMessage("Элемент добавлен в коллекцию"));
        else Sender.sendObject(new SerializedMessage("Возникла ошибка во время выполнения команды add пользователя \"" + username + "\""));
        logger.debug("Результат выполнения команды \"add\" отправлен клиенту " + username);
    }

    public void info() {
        Sender.sendObject(new SerializedMessage(CollectionManager.getInfo()));
        logger.debug("Результат выполнения команды \"info\" отправлен клиенту");
    }

    public void show() {
        Sender.sendObject(new SerializedMessage(CollectionManager.show()));
        logger.debug("Результат выполнения команды \"show\" отправлен клиенту");
    }

    public void clear(String username) {
        if (CollectionManager.clear(username))
            Sender.sendObject(new SerializedMessage("Все элементы принадлежащие пользователю \"" + username + "\" удалены"));
        else Sender.sendObject(new SerializedMessage("Произошла ошибка выполнения команды clear пользователя \"" + username + "\""));
        logger.debug("Результат выполнения команды \"clear\" отправлен клиенту " + username);
    }

    public void removeByID(String id, String username) {
        int personId;
        try {
            personId = Integer.parseInt(id);
            if (!CollectionManager.getSet().isEmpty())
                CollectionManager.removeByID(personId, username);
        } catch (NumberFormatException e) {
            Sender.sendObject(new SerializedMessage("Команда не выполнена. Вы ввели некорректный аргумент."));
        }
        logger.debug("Результат выполнения команды \"remove_by_id\" отправлен клиенту " + username);
    }

    public void update(String id, Person person, String username)  {
        Integer personId = Integer.parseInt(id);
        CollectionManager.update(person, personId, username);
        logger.debug("Результат выполнения команды \"update\" отправлен клиенту " + username);
    }

    public void removeGreater(Person person, String username) {
        Sender.sendObject(new SerializedMessage(CollectionManager.removeGreater(person, username)));
        logger.debug("Результат выполнения команды \"remove_greater\" отправлен клиенту " + username);
    }

    public void removeLower(Person person, String username) {
        Sender.sendObject(new SerializedMessage(CollectionManager.removeLower(person, username)));
        logger.debug("Результат выполнения команды \"remove_lower\" отправлен клиенту " + username);
    }

    public void addIfMax(Object o, String username) {
        Person person = (Person) o;
        Sender.sendObject(new SerializedMessage(CollectionManager.addIfMax(person, username)));
        logger.debug("Результат выполнения команды \"add_if_max\" отправлен клиенту " + username);
    }

    public void printFieldDescendingHeight() {
        Sender.sendObject(new SerializedMessage(CollectionManager.printFieldDescendingHeight()));
        logger.debug("Результат выполнения команды \"print_field_descending_height\" отправлен клиенту");
    }

    public void printFieldDescendingPassportID() {
        Sender.sendObject(new SerializedMessage(CollectionManager.printFieldDescendingPassportID()));
        logger.debug("Результат выполнения команды \"print_field_descending_passport_id\" отправлен клиенту");
    }

    public void filterStartsWithName(String name) {
        Sender.sendObject(new SerializedMessage(CollectionManager.filterStartsWithName(name)));
        logger.debug("Результат выполнения команды \"filter_starts_with_name\" отправлен клиенту");
    }

    public void checkExist(String id, String username) {
        Integer personId = Integer.parseInt(id);
        boolean result = CollectionManager.checkExist(personId, username);
        Sender.sendObject(new SerializedMessage(Boolean.toString(result)));
    }
}
