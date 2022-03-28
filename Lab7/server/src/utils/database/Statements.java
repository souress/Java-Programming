package utils.database;

public class Statements {
    public static final String CHECK_NAME = "SELECT * FROM users315710 WHERE login=?;";
    public static final String GET_IDS = "SELECT id FROM persons315710";
    public static final String ADD_USER = "INSERT INTO users315710 (login, password) VALUES(?,?);";
    public static final String CHECK_USER = "SELECT * FROM users315710 WHERE login=? AND password=?;";
    public static final String CLEAR_PERSONS = "DELETE FROM persons315710 WHERE owner=?";
    public static final String INCREASE_ID = "SELECT nextval('ids')";
    public static final String GET_BY_ID = "SELECT * FROM persons315710 WHERE id = ?";
    public static final String GET_BY_ID_AND_OWNER = "SELECT * FROM persons315710 WHERE id = ? AND owner = ?";
    public static final String DELETE_BY_ID = "DELETE FROM persons315710 WHERE id = ?";
    public static final String ADD_PERSON = "INSERT INTO persons315710 (id, name, coordinateX, coordinateY, height, passportID, hairColor, nationality, locationX, locationY, locationZ, owner) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
    public static final String UPDATE_PERSON = "UPDATE persons315710 SET name=?, coordinateX=?, coordinateY=?, height=?, passportID=?, hairColor=?, nationality=?, locationX=?, locationY=?, locationZ=?, owner=? WHERE id=?";
    public static final String GET_PERSONS = "SELECT * FROM persons315710";
    public static final String GET_PERSONS_BY_OWNER = "SELECT * FROM persons315710 WHERE owner = ?";
}
