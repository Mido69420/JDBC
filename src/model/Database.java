package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static Connection connection = null;
    private String type = "postgresql";
    private String user = "reader";
    private String password = "reader";
    private String db = "pagila";
    private String host = "xserv";
    private int port = 5432;


    private Database() {
        try {
            connection = DriverManager.getConnection("jdbc:" + type + "://" + host + ":" + port + "/" + db, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            String driverFromConfig = "org.postgresql.Driver"; //muss nicht neu Kompilieren wenn andere DB -> nimmt DB aus Config File

            Class.forName(driverFromConfig);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getInstance() {
        if (connection == null) {
            new Database();
        }
        return connection;
    }

}