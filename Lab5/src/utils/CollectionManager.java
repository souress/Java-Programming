package utils;

import data.Person;

import java.time.ZonedDateTime;
import java.util.*;

/**
 * Менеджер коллекцией. Описывает логику команд, выполняющих работу с коллекцией
 */
public class CollectionManager {
    private static HashSet<Person> personHashSet;
    private static ZonedDateTime creationDate;

    public static void initHashSet() {
        if (personHashSet == null) {
            personHashSet = FileManager.getPersonHashSet();
            creationDate = ZonedDateTime.now();
        }
    }

    public static HashSet<Person> getHashSet() {
        return personHashSet;
    }

    public static void add(Person person) {
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

    public static void getInfo() {
        System.out.printf("Тип коллекции - %s\n" +
                "Дата инициализации коллекции - %s\n" +
                "Количество элементов в коллекции - %d\n",
                personHashSet.getClass().getName(), creationDate.toString(), personHashSet.size());
    }

    public static void show() {
        if (!personHashSet.isEmpty()) for (Person person : personHashSet) System.out.println(person.toString());
        else System.out.println("Коллекция пуста");
    }

    public static void removeByID(Integer personId) {
        while (personHashSet.iterator().hasNext()){
            Iterator<Person> personIterator = personHashSet.iterator();
            if (personIterator.next().getId().equals(personId)) {
                personIterator.remove();
                break;
            }
        }
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

    public static void removeGreater(Person person) {
        int startSize = personHashSet.size();
        personHashSet.removeIf(person1 -> person1.compareTo(person) < 0);
        if (startSize > personHashSet.size()) System.out.println("Удалены элементы, превышающие заданный.");
        else System.out.println("Элементов, превышающих заданный, не найдено.");
    }

    public static void removeLower(Person person) {
        int startSize = personHashSet.size();
        personHashSet.removeIf(person1 -> person1.compareTo(person) > 0);
        if (startSize > personHashSet.size()) System.out.println("Удалены элементы, меньшие заданного.");
        else System.out.println("Элементов, меньших заданного, не найдено.");
    }

    public static void printFieldDescendingHeight() {
        List<Person> personList = new ArrayList<>(personHashSet);
        Collections.sort(personList);
        for (Person person : personList) {
            System.out.println(person.getName() + ": " + person.getHeight());
        }
    }

    public static void printFieldDescendingPassportID() {
        List<Person> personList = new ArrayList<>(personHashSet);
        personList.sort(Comparator.comparing(Person::getPassportID));
        for (Person person : personList) {
            System.out.println(person.getName() + ": " + person.getPassportID());
        }
    }

    public static void filterStartsWithName(String name) {
        for (Person person : personHashSet) {
            if (person.getName().contains(name))
                System.out.println(person.toString());
        }
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

    public static void addIfMax(Person person) {
        try {
            Person maxPerson = CollectionManager.getMaxElement();
            if (person.compareTo(maxPerson) < 0) {
                CollectionManager.add(person);
                System.out.println("Элемент добавлен в коллекцию");
            } else System.out.println("Элемент меньше или равен максимальному - не добавлен в коллецию.");
        } catch (NullPointerException exception) {
            System.out.println(exception.getMessage());
        }
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
