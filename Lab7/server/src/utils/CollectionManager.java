package utils;

import commands.serialized.SerializedMessage;
import data.*;
import utils.multithreading.Sender;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Менеджер коллекцией. Описывает логику команд, выполняющих работу с коллекцией
 */
public class CollectionManager {
    private static Set<Person> collection = Collections.synchronizedSet(new HashSet<>());
    private static List<Person> personList;

    public void updateCollection(ResultSet resultSet) throws SQLException {
            while (resultSet.next()) {
                Person person = new Person(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        new Coordinates(resultSet.getLong(3), resultSet.getDouble(4)),
                        resultSet.getDate(5).toLocalDate().atStartOfDay(TimeZone.getDefault().toZoneId()),
                        resultSet.getDouble(6),
                        resultSet.getString(7),
                        Color.valueOf(resultSet.getString(8)),
                        resultSet.getString(9) != null ? Country.valueOf(resultSet.getString(9)) : null,
                        new Location(resultSet.getLong(10),
                                resultSet.getFloat(11),
                                resultSet.getLong(12)));
                if (!Controller.getManager().getIdList().contains(resultSet.getInt(1)))
                    add(person, resultSet.getString(13));
                collection.add(person);
            }
    }

    public static List<Person> getSortedByNamePersonList() {
        personList = new ArrayList<>(collection);
        personList.sort(Comparator.comparing(Person::getName));
        return personList;
    }

    public static Set<Person> getSet() {
        return collection;
    }

    public static boolean add(Person person, String username) {
        person.setId(Controller.getManager().getNewId());
        if (Controller.getManager().addPerson(username, person)) {
            collection.add(person);
            return true;
        } else return false;
    }

    public static Integer getMaxID() {
        if (!collection.isEmpty()) {
            Integer maxId = collection.iterator().next().getId();
            for (Person person : collection)
                if (person.getId() > maxId) maxId = person.getId();
            return maxId;
        } else return 0;
    }

    public static String getInfo() {
        return  ("Тип коллекции - " + collection.getClass().getName() +
                "\nКоличество элементов в коллекции - " + collection.size());
    }

    public static String show() {
        personList = getSortedByNamePersonList();
        if (!personList.isEmpty())
            return personList
                    .stream()
                    .map(Person::toString)
                    .collect(Collectors.joining(", \n"));
        else return "Коллекция пуста";
    }

    public static void removeByID(Integer personId, String username) {
        switch (Controller.getManager().removeById(username, personId)) {
            case GOOD:
                personList = new ArrayList<>(collection);
                personList.removeIf(person -> person.getId().equals(personId));
                collection = Collections.synchronizedSet(new HashSet<>(personList));
                Sender.sendObject(new SerializedMessage("Элемент с ID " + personId + " успешно удален из коллекции."));
                break;
            case PERMISSION:
                Sender.sendObject(new SerializedMessage("У вас недостаточно прав на удаление этого объекта"));
                break;
            case SQL:
                Sender.sendObject(new SerializedMessage("SQL error"));
                break;
            case NO_ID:
                Sender.sendObject(new SerializedMessage("Элемента с таким ID нет в коллекции."));
                break;
        }
    }

    public static void removeCalledFromAnotherCommand(Integer personId, String username) {
        switch (Controller.getManager().removeById(username, personId)) {
            case GOOD:
                personList = new ArrayList<>(collection);
                personList.removeIf(person -> person.getId().equals(personId));
                collection = new HashSet<>(personList);
                break;
            case PERMISSION:
            case SQL:
            case NO_ID:
                break;
        }
    }

    public static boolean checkExist(Integer id, String username) {
        if (Controller.getManager().getByIdAndOwner(username, id))
            for (Person person : CollectionManager.getSet()) {
                if (person.getId().equals(id)) return true;
            }
        return false;
    }

    public static boolean clear(String username) {
        for (Person person : Controller.getManager().getByOwner(username))
            removeCalledFromAnotherCommand(person.getId(), username);
        if (Controller.getManager().clear(username)) {
            System.out.println("\"" + username + "\" удалил свои объекты из БД");
            return true;
        } else {
            System.out.println("Произошла ошибка выполнения команды clear пользователя \"" + username + "\"");
            return false;
        }
    }

    public static String removeGreater(Person person, String username) {
        int startSize = collection.size();
        for (Person person1 : collection) {
            if (person1.compareTo(person) < 0)
                removeCalledFromAnotherCommand(person1.getId(), username);
        }
        if (startSize > collection.size())
            return "Удалены элементы, превышающие заданный.";
        else
            return "Элементов, превышающих заданный, не найдено.";
    }

    public static String removeLower(Person person, String username) {
        int startSize = collection.size();
        for (Person person1 : collection) {
            if (person1.compareTo(person) > 0)
                removeCalledFromAnotherCommand(person1.getId(), username);
        }
        if (startSize > collection.size())
            return "Удалены элементы пользователя \"" + username + "\", меньшие заданного.";
        else
            return "Элементов, меньших заданного пользователем \"" + username + "\", не найдено.";
    }

    public static String printFieldDescendingHeight() {
        StringBuilder result = new StringBuilder();
        if (collection.isEmpty()) return "Коллекция пуста.";
        List<Person> personList = new ArrayList<>(collection);
        Collections.sort(personList);
        for (Person person : personList) {
            result.append(person.getName()).append(": ").append(person.getHeight()).append("\n");
        }
        return result.toString();
    }

    public static String printFieldDescendingPassportID() {
        StringBuilder result = new StringBuilder();
        if (collection.isEmpty()) return "Коллекция пуста.";
        List<Person> personList = new ArrayList<>(collection);
        personList.sort(Comparator.comparing(Person::getPassportID));
        Collections.reverse(personList);
        for (Person person : personList) {
            result.append(person.getName()).append(": ").append(person.getPassportID()).append("\n");
        }
        return result.toString();
    }

    public static String filterStartsWithName(String name) {
        StringBuilder result = new StringBuilder();
        int count = 0;
        if (collection.size() > 0) {
            for (Person person : collection)
                if (person.getName().startsWith(name)) {
                    result.append(person.toString()).append("\n");
                    count++;
                }
            if (count == 0)
                result = new StringBuilder("Элементы не найдены.");
        } else result = new StringBuilder("Коллекция пуста.");
        return result.toString();
    }

    public static Person getMaxElement() throws NullPointerException{
        if (!collection.isEmpty()) {
            Person maxPerson = collection.iterator().next();
            for (Person person : collection)
                if (person.compareTo(maxPerson) < 0)
                    maxPerson = person;
            return maxPerson;
        } else throw new NullPointerException("Коллекция пуста.");
    }

    public static String addIfMax(Person person, String username) {
        Person maxPerson = CollectionManager.getMaxElement();
        if (person.compareTo(maxPerson) < 0) {
            CollectionManager.add(person, username);
            return "Элемент добавлен в коллекцию";
        } else return  "Элемент меньше или равен максимальному - не добавлен в коллецию.";
    }

    public static void update(Person personToUpdate, Integer elementId, String username) {
        switch (Controller.getManager().update(username, personToUpdate, elementId)) {
            case GOOD:
                collection.forEach(person -> {
                    if (person.getId().equals(elementId)) {
                        person.setName(personToUpdate.getName());
                        person.setCoordinates(personToUpdate.getCoordinates());
                        person.setHeight(personToUpdate.getHeight());
                        person.setPassportID(personToUpdate.getPassportID());
                        person.setHairColor(personToUpdate.getHairColor());
                        person.setNationality(personToUpdate.getNationality());
                        person.setLocation(personToUpdate.getLocation());
                    }
                });
                Sender.sendObject(new SerializedMessage("Элемент обновлен"));
            break;
            case NO_ID:
                Sender.sendObject(new SerializedMessage("Элемент с таким id не найден"));
                break;
            case PERMISSION:
                Sender.sendObject(new SerializedMessage("Элемент не принадлежит пользователю \"" + username + "\""));
                break;
            case SQL:
                Sender.sendObject(new SerializedMessage("SQL error"));
                break;
        }
    }
}
