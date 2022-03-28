package utils;

import data.Person;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Менеджер коллекцией. Описывает логику команд, выполняющих работу с коллекцией
 */
public class CollectionManager {
    private static HashSet<Person> personHashSet;
    private static List<Person> personList;
    private static ZonedDateTime creationDate;

    public static void initHashSet() {
        if (personHashSet == null) {
            personHashSet = FileManager.getPersonHashSet();
            creationDate = ZonedDateTime.now();
        }
    }

    public static List<Person> getSortedByNamePersonList() {
        personList = new ArrayList<>(personHashSet);
        personList.sort(Comparator.comparing(Person::getName));
        return personList;
    }

    public static HashSet<Person> getHashSet() {
        return personHashSet;
    }

    public static void add(Person person) {
        person.setId(getMaxID() + 1);
        personHashSet.add(person);
    }

    public static Integer getMaxID() {
        if (!personHashSet.isEmpty()) {
            Integer maxId = personHashSet.iterator().next().getId();
            for (Person person : personHashSet)
                if (person.getId() > maxId) maxId = person.getId();
            return maxId;
        } else return 0;
    }

    public static String getInfo() {
        return  ("Тип коллекции - " + personHashSet.getClass().getName() +
                "\nДата инициализации коллекции - " + creationDate.toString() +
                "\nКоличество элементов в коллекции - " + personHashSet.size());
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

    public static void removeByID(Integer personId) {
        personList = new ArrayList<>(personHashSet);
        personList.removeIf(person -> person.getId().equals(personId));
        personHashSet = new HashSet<>(personList);
    }

    public static boolean checkExist(Integer id) {
        for (Person person : CollectionManager.getHashSet()) {
            if (person.getId().equals(id)) return true;
        }
        return false;
    }

    public static void clear() {
        personHashSet.clear();
    }

    public static String removeGreater(Person person) {
        int startSize = personHashSet.size();
        personHashSet.removeIf(person1 -> person1.compareTo(person) < 0);
        if (startSize > personHashSet.size()) return "Удалены элементы, превышающие заданный.";
        else return "Элементов, превышающих заданный, не найдено.";
    }

    public static String removeLower(Person person) {
        int startSize = personHashSet.size();
        personHashSet.removeIf(person1 -> person1.compareTo(person) > 0);
        if (startSize > personHashSet.size()) return "Удалены элементы, меньшие заданного.";
        else return "Элементов, меньших заданного, не найдено.";
    }

    public static String printFieldDescendingHeight() {
        StringBuilder result = new StringBuilder();
        if (personHashSet.isEmpty()) return "Коллекция пуста.";
        List<Person> personList = new ArrayList<>(personHashSet);
        Collections.sort(personList);
        for (Person person : personList) {
            result.append(person.getName()).append(": ").append(person.getHeight()).append("\n");
        }
        return result.toString();
    }

    public static String printFieldDescendingPassportID() {
        StringBuilder result = new StringBuilder();
        if (personHashSet.isEmpty()) return "Коллекция пуста.";
        List<Person> personList = new ArrayList<>(personHashSet);
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
        if (personHashSet.size() > 0) {
            for (Person person : personHashSet)
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
        if (!personHashSet.isEmpty()) {
            Person maxPerson = personHashSet.iterator().next();
            for (Person person : personHashSet)
                if (person.compareTo(maxPerson) < 0)
                    maxPerson = person;
            return maxPerson;
        } else throw new NullPointerException("Коллекция пуста.");
    }

    public static String addIfMax(Person person) {
        Person maxPerson = CollectionManager.getMaxElement();
        if (person.compareTo(maxPerson) < 0) {
            CollectionManager.add(person);
            return "Элемент добавлен в коллекцию";
        } else return  "Элемент меньше или равен максимальному - не добавлен в коллецию.";
    }

    public static void update(Person personToUpdate, Integer elementId) {
        personHashSet.forEach(person -> {
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
    }
}
