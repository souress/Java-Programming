package commands.utils;

import commands.serialized.SerializedMessage;
import data.*;
import utils.*;

import java.io.*;

/**
 * Ресивер(получатель), описывает основную логику команд, при надобности делегирует ее менеджеру коллекции.
 */
public class CommandReceiver {

    public void add(Object o) throws IOException {
        Person person = (Person) o;
        CollectionManager.add(person);
        Sender.sendObject(new SerializedMessage("Элемент добавлен в коллекцию"));
    }

    public void info() throws IOException {
        Sender.sendObject(new SerializedMessage(CollectionManager.getInfo()));
    }

    public void show() throws IOException {
        Sender.sendObject(new SerializedMessage(CollectionManager.show()));
    }

    public void clear() throws IOException {
        CollectionManager.clear();
        Sender.sendObject(new SerializedMessage("Коллекция очищена"));
    }

    public void removeByID(String id) throws IOException {
        Integer personId;
        try {
            personId = Integer.parseInt(id);
            if (!CollectionManager.getHashSet().isEmpty())
                if (CollectionManager.checkExist(personId)) {
                    CollectionManager.removeByID(personId);
                    Sender.sendObject(new SerializedMessage("Элемент с ID " + personId + " успешно удален из коллекции."));
                } else Sender.sendObject(new SerializedMessage("Элемента с таким ID нет в коллекции."));
        } catch (NumberFormatException e) {
            Sender.sendObject(new SerializedMessage("Команда не выполнена. Вы ввели некорректный аргумент."));
        }
    }

    public void update(String id, Person person) throws IOException {
        try {
            Integer personId = Integer.parseInt(id);
            if (CollectionManager.checkExist(personId)) {
                CollectionManager.update(person, personId);
                Sender.sendObject(new SerializedMessage("Элемент обновлен."));
            } else {
                Sender.sendObject(new SerializedMessage("Элемента с таким id не найдено."));
            }
        } catch (NumberFormatException e) {
            Sender.sendObject(new SerializedMessage("Команда не выполнена. Проверьте аргумент."));
        }
    }

    public void removeGreater(Person person) throws IOException {
        Sender.sendObject(new SerializedMessage(CollectionManager.removeGreater(person)));
    }

    public void removeLower(Person person) throws IOException {
        Sender.sendObject(new SerializedMessage(CollectionManager.removeLower(person)));
    }

    public void addIfMax(Object o) throws IOException {
        Person person = (Person) o;
        Sender.sendObject(new SerializedMessage(CollectionManager.addIfMax(person)));
    }

    public void printFieldDescendingHeight() throws IOException {
        Sender.sendObject(new SerializedMessage(CollectionManager.printFieldDescendingHeight()));
    }

    public void printFieldDescendingPassportID() throws IOException {
        Sender.sendObject(new SerializedMessage(CollectionManager.printFieldDescendingPassportID()));
    }

    public void filterStartsWithName(String name) throws IOException {
        Sender.sendObject(new SerializedMessage(CollectionManager.filterStartsWithName(name)));
    }

    public void checkExist(String id) throws IOException {
        Integer personId = Integer.parseInt(id);
        boolean result = CollectionManager.checkExist(personId);
        Sender.sendObject(new SerializedMessage(Boolean.toString(result)));
    }
}
