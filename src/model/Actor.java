package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Actor {
    private int id;
    private String firstname;
    private String lastname;

    public Actor(ResultSet db) throws SQLException{
        this.id = db.getInt("actor_id");
        this.firstname = db.getString("first_name");
        this.lastname = db.getString("last_name");
    }

    public static ObservableList<Actor> getList() {
        ObservableList<Actor> actors = FXCollections.observableArrayList();
        try {
            Connection c = model.Database.getInstance();
            Statement s = c.createStatement();

            ResultSet results = s.executeQuery("SELECT * FROM actor");

            while (results.next()) {
                actors.add(new Actor(results));
            }

            results.close();
            s.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return actors;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return firstname + " " + lastname;
    }
}
