package utils.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Initializer {
    private final Connection connection;


    public Initializer(Connection connection) {
        this.connection = connection;
    }

    public void initialize() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE SEQUENCE IF NOT EXISTS ids START 1");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS persons315710 (" +
                "id int PRIMARY KEY," +
                "name varchar (255) NOT NULL," +
                "coordinateX bigint NOT NULL CHECK (coordinateX <= 51)," +
                "coordinateY real NOT NULL," +
                "creationDate date DEFAULT (current_date)," +
                "height real NOT NULL CHECK (height > 0)," +
                "passportID varchar (255) NOT NULL," +
                "hairColor varchar (10) NOT NULL," +
                "nationality varchar (20)," +
                "locationX bigint NOT NULL," +
                "locationY float NOT NULL," +
                "locationZ bigint NOT NULL," +
                "owner varchar(255) NOT NULL)");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS users315710(" +
                "login varchar (255) PRIMARY KEY," +
                "password BYTEA DEFAULT (null)" +
                ")");
    }
}
