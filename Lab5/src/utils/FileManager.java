package utils;

import java.io.*;
import java.time.ZonedDateTime;
import java.util.*;
import data.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 * Класс, отвечающий за сохранение и загрузку данных в xml
 */
public class FileManager {
    private static final HashSet<Person> personHashSet = new HashSet<>();

    private static String filePath;

    public FileManager(String filePath) {
        FileManager.filePath = filePath;
    }

    /**
     * Метод, преобразующий xml в коллекцию
     * @throws NullPointerException если в файле нет объектов или коллекция повреждена
     * @throws SecurityException если недостаточно прав для чтения файла
     * @throws IllegalArgumentException если поля объектов в файле сожержат недопустимые значения
     */
    public void parseFromXml() throws NullPointerException, SecurityException {
        Integer id = null;
        String name = null;
        Long coordinateX = null;
        double coordinateY = 0;
        ZonedDateTime creationDate = null;
        double height = 0;
        String passportID = null;
        Color hairColor = null;
        Country nationality = null;
        long locationX = 0;
        float locationY = 0;
        Long locationZ = null;
        try {
            File in = new File(filePath);
            if (!in.exists()) throw new FileNotFoundException();
            if (!in.canRead() || !in.canWrite()) throw new SecurityException();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(in);
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("person");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());

                    name = element.getElementsByTagName("name").item(0).getTextContent();
                    if (name.equals("")) throw new IllegalArgumentException();

                    NodeList nodeListCoordinates = element.getElementsByTagName("coordinates");
                    Element elementCoordinates = (Element) nodeListCoordinates.item(0);
                    coordinateX = Long.parseLong(elementCoordinates.getElementsByTagName("coordinateX").item(0).getTextContent());
                    if (coordinateX > 51) throw new IllegalArgumentException();
                    coordinateY = Double.parseDouble(elementCoordinates.getElementsByTagName("coordinateY").item(0).getTextContent());

                    creationDate = ZonedDateTime.parse(element.getElementsByTagName("creationDate").item(0).getTextContent());

                    height = Double.parseDouble(element.getElementsByTagName("height").item(0).getTextContent());
                    if (height <= 0) throw new IllegalArgumentException();
                    passportID = element.getElementsByTagName("passportID").item(0).getTextContent();
                    if (passportID.equals("")) throw new IllegalArgumentException();
                        hairColor = Color.valueOf(element.getElementsByTagName("hairColor").item(0).getTextContent());
                    if (!element.getElementsByTagName("nationality").item(0).getTextContent().equals(""))
                        nationality = Country.valueOf(element.getElementsByTagName("nationality").item(0).getTextContent());
                    else nationality = null;

                    NodeList nodeListLocation = element.getElementsByTagName("location");
                    Element elementLocation = (Element) nodeListLocation.item(0);
                    locationX = Long.parseLong(elementLocation.getElementsByTagName("locationX").item(0).getTextContent());
                    locationY = Float.parseFloat(elementLocation.getElementsByTagName("locationY").item(0).getTextContent());
                    locationZ = Long.parseLong(elementLocation.getElementsByTagName("locationZ").item(0).getTextContent());
                }

                Person person = new Person(id, name, new Coordinates(coordinateX, coordinateY), creationDate, height,
                        passportID, hairColor, nationality, new Location(locationX, locationY, locationZ));

                personHashSet.add(person);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден! Выход...");
            System.exit(0);
        } catch (IOException | ParserConfigurationException e) {
            System.out.println(e.getMessage());
        } catch (SecurityException e) {
            System.out.println("Недостаточно прав! Проверьте наличие прав на чтение и запись.");
            System.exit(0);
        } catch (NullPointerException e) {
            System.out.println("В файле нет объектов или коллекция повреждена.");
        } catch (IllegalArgumentException | SAXException e){
            System.out.println("Ошибка! Некоторые поля файла содержат недопустимые данные.");
            System.exit(0);
        }
    }

    public static HashSet<Person> getPersonHashSet() {
        return personHashSet;
    }

    /**
     * Метод, преобразующий коллекцию в xml
     * @param personCollection коллекция, которая будет записана в файл
     */
    public static void parseToXml(Collection<Person> personCollection) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element elementCollection = doc.createElement("collection");

            for (Person person : personCollection) {
                Element elementPerson = doc.createElement("person");

                Element elementId = doc.createElement("id");
                elementId.appendChild(doc.createTextNode(person.getId().toString()));
                elementPerson.appendChild(elementId);

                Element elementName = doc.createElement("name");
                elementName.appendChild(doc.createTextNode(person.getName()));
                elementPerson.appendChild(elementName);

                Element elementCoordinates = doc.createElement("coordinates");
                Element elementCoordinateX = doc.createElement("coordinateX");
                elementCoordinateX.appendChild(doc.createTextNode(person.getCoordinates().getCoordinateX().toString()));
                elementCoordinates.appendChild(elementCoordinateX);
                Element elementCoordinateY = doc.createElement("coordinateY");
                elementCoordinateY.appendChild(doc.createTextNode(((Double) person.getCoordinates().getCoordinateY()).toString()));
                elementCoordinates.appendChild(elementCoordinateY);
                elementPerson.appendChild(elementCoordinates);

                Element elementCreationDate = doc.createElement("creationDate");
                elementCreationDate.appendChild(doc.createTextNode(person.getCreationDate().toString()));
                elementPerson.appendChild(elementCreationDate);

                Element elementHeight = doc.createElement("height");
                elementHeight.appendChild(doc.createTextNode(((Double) person.getHeight()).toString()));
                elementPerson.appendChild(elementHeight);

                Element elementPassportID = doc.createElement("passportID");
                elementPassportID.appendChild(doc.createTextNode(person.getPassportID()));
                elementPerson.appendChild(elementPassportID);

                Element elementHairColor = doc.createElement("hairColor");
                elementHairColor.appendChild(doc.createTextNode(person.getHairColor().toString()));
                elementPerson.appendChild(elementHairColor);

                Element elementNationality = doc.createElement("nationality");
                if (person.getNationality() != null)
                    elementNationality.appendChild(doc.createTextNode(person.getNationality().toString()));
                else elementNationality.appendChild(doc.createTextNode(""));
                elementPerson.appendChild(elementNationality);

                Element elementLocation = doc.createElement("location");
                Element elementLocationX = doc.createElement("locationX");
                elementLocationX.appendChild(doc.createTextNode(((Long) person.getLocation().getLocationX()).toString()));
                elementLocation.appendChild(elementLocationX);
                Element elementLocationY = doc.createElement("locationY");
                elementLocationY.appendChild(doc.createTextNode(((Float) person.getLocation().getLocationY()).toString()));
                elementLocation.appendChild(elementLocationY);
                Element elementLocationZ = doc.createElement("locationZ");
                elementLocationZ.appendChild(doc.createTextNode((person.getLocation().getLocationZ().toString())));
                elementLocation.appendChild(elementLocationZ);
                elementPerson.appendChild(elementLocation);

                elementCollection.appendChild(elementPerson);
            }
            doc.appendChild(elementCollection);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(doc), new StreamResult(new BufferedOutputStream(new FileOutputStream(filePath))));
            System.out.println("Коллекция записана в файл.");
        } catch (ParserConfigurationException | TransformerException | IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
