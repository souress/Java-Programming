package utils.database;

import data.*;
import utils.CollectionManager;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.sql.*;
import java.util.*;

public class Manager {
    private final Connection connection;
    private final MessageDigest messageDigest;
    private final CollectionManager collectionManager;

    public Manager(Connection connection, CollectionManager collectionManager) throws NoSuchAlgorithmException {
        this.connection = connection;
        this.collectionManager = collectionManager;
        messageDigest = MessageDigest.getInstance("SHA-224");
    }

    public boolean checkName(String username) {
        try{
            PreparedStatement check = connection.prepareStatement(Statements.CHECK_NAME);
            check.setString(1, username);
            ResultSet resultSet = check.executeQuery();
            return resultSet.next();
        } catch (SQLException exception) {
                exception.printStackTrace();
                return false;
        }
    }

    public void registerUser(String name, String password) {
        try {
            PreparedStatement register = connection.prepareStatement(Statements.ADD_USER);
            register.setString(1, name);
            register.setBytes(2, messageDigest.digest(password.getBytes(StandardCharsets.UTF_8)));
            register.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public boolean checkUser(String username, String password) {
        try {
            PreparedStatement check = connection.prepareStatement(Statements.CHECK_USER);
            check.setString(1, username);
            check.setBytes(2, messageDigest.digest(password.getBytes(StandardCharsets.UTF_8)));
            ResultSet result = check.executeQuery();
            return result.next();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public void updateCollection() {
        try {
            PreparedStatement updateCollection = connection.prepareStatement(Statements.GET_PERSONS);
            collectionManager.updateCollection(updateCollection.executeQuery());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public boolean addPerson(String username, Person person) {
        try {
            PreparedStatement addPerson = connection.prepareStatement(Statements.ADD_PERSON);
            addPerson.setLong(1, person.getId());
            addPerson.setString(2, person.getName());
            addPerson.setLong(3, person.getCoordinates().getCoordinateX());
            addPerson.setDouble(4, person.getCoordinates().getCoordinateY());
            addPerson.setDouble(5, person.getHeight());
            addPerson.setString(6, person.getPassportID());
            addPerson.setString(7, person.getHairColor().toString());
            if (person.getNationality() == null)
                addPerson.setString(8, null);
            else addPerson.setString(8, person.getNationality().toString());
            addPerson.setLong(9, person.getLocation().getLocationX());
            addPerson.setFloat(10, person.getLocation().getLocationY());
            addPerson.setLong(11, person.getLocation().getLocationZ());
            addPerson.setString(12, username);
            addPerson.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean clear(String username) {
        try {
            PreparedStatement clear = connection.prepareStatement(Statements.CLEAR_PERSONS);
            clear.setString(1, username);
            clear.executeUpdate();
            Statement updateId = connection.createStatement();
            updateId.executeUpdate("ALTER SEQUENCE IF EXISTS ids RESTART WITH " + CollectionManager.getMaxID()+1);
        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
        return true;
    }

    public CommandResult removeById(String username, Integer id) {
        try {
            PreparedStatement getById = connection.prepareStatement(Statements.GET_BY_ID);
            getById.setInt(1, id);
            ResultSet worker = getById.executeQuery();
            if (worker.next()) {
                if (worker.getString("owner").equals(username)) {
                    PreparedStatement remove = connection.prepareStatement(Statements.DELETE_BY_ID);
                    remove.setInt(1, id);
                    remove.executeUpdate();
                    Statement updateId = connection.createStatement();
                    updateId.executeUpdate("ALTER SEQUENCE IF EXISTS ids RESTART WITH " + CollectionManager.getMaxID()+1);
                } else return CommandResult.PERMISSION;
            } else return CommandResult.NO_ID;
        } catch (SQLException exception) {
            exception.printStackTrace();
            return CommandResult.SQL;
        }
        return CommandResult.GOOD;
    }

    public boolean getByIdAndOwner(String username, Integer id) {
        try {
            PreparedStatement getByIdAndOwner = connection.prepareStatement(Statements.GET_BY_ID_AND_OWNER);
            getByIdAndOwner.setInt(1, id);
            getByIdAndOwner.setString(2, username);
            ResultSet result = getByIdAndOwner.executeQuery();
            if (result.next())
                return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
        return false;
    }

    public CommandResult update(String username, Person person, Integer id) {
        try {
            PreparedStatement getById = connection.prepareStatement(Statements.GET_BY_ID);
            getById.setInt(1, id);
            ResultSet result = getById.executeQuery();
            if (result.next()){
                if (result.getString("owner").equals(username)){
                    PreparedStatement update = connection.prepareStatement(Statements.UPDATE_PERSON);
                    update.setString(1, person.getName());
                    update.setLong(2, person.getCoordinates().getCoordinateX());
                    update.setDouble(3, person.getCoordinates().getCoordinateY());
                    update.setDouble(4, person.getHeight());
                    update.setString(5, person.getPassportID());
                    update.setString(6, person.getHairColor().toString());
                    if (person.getNationality() == null)
                        update.setString(7, null);
                    else update.setString(7, person.getNationality().toString());
                    update.setLong(8, person.getLocation().getLocationX());
                    update.setFloat(9, person.getLocation().getLocationY());
                    update.setLong(10, person.getLocation().getLocationZ());
                    update.setString(11, username);
                    update.setInt(12, id);
                    update.executeUpdate();
                } else return CommandResult.PERMISSION;
            } else return CommandResult.NO_ID;
        } catch (SQLException exception) {
            exception.printStackTrace();
            return CommandResult.SQL;
        }
        return CommandResult.GOOD;
    }

    public ArrayList<Integer> getIdList() {
        ArrayList<Integer> idList = new ArrayList<>();
        try {
            PreparedStatement getIdList = connection.prepareStatement(Statements.GET_IDS);
            ResultSet resultSet = getIdList.executeQuery();
            while (resultSet.next()) {
                idList.add(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idList;
    }

    public Integer getNewId() {
        try {
            PreparedStatement getNewId = connection.prepareStatement(Statements.INCREASE_ID);
            ResultSet resultSet = getNewId.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt("nextval");
            }
            return null;
        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public ArrayList<Person> getByOwner(String username) {
        try {
            PreparedStatement getByOwner = connection.prepareStatement(Statements.GET_PERSONS_BY_OWNER);
            getByOwner.setString(1, username);
            ResultSet result = getByOwner.executeQuery();
            ArrayList<Person> resultCollection = new ArrayList<>();
            while (result.next()) {
                resultCollection.add(new Person(
                        result.getInt(1),
                        result.getString(2),
                        new Coordinates(result.getLong(3), result.getDouble(4)),
                        result.getDate(5).toLocalDate().atStartOfDay(TimeZone.getDefault().toZoneId()),
                        result.getDouble(6),
                        result.getString(7),
                        Color.valueOf(result.getString(8)),
                        result.getString(9) != null ? Country.valueOf(result.getString(9)) : null,
                        new Location(result.getLong(10), result.getFloat(11), result.getLong(12))));
            }
            return resultCollection;
        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
