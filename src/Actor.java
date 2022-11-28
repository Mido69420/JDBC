import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Actor {
    private int id;
    private String firstname;
    private String lastname;

    public Actor (ResultSet db) throws SQLException {
        this.id = db.getInt("actor_id");
        this.firstname = db.getString("first_name");
        this.lastname = db.getString("last_name");
    }

    public int getFilmLength() {
        Connection c = null;
        int length = 0;

        try {
            c = Database.getInstance();
            Statement s = c.createStatement();

            ResultSet res2 = s.executeQuery("SELECT SUM(length) AS laenge_filme FROM film  WHERE film_id IN (SELECT film_id FROM film_actor WHERE actor_id = " + id + " )");

            if (res2.next()) {
                length = res2.getInt("laenge_filme");
            }

            s.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return length;
    }

    public int getFilmAnzahl() {
        Connection c = null;
        int res = 0;

        try {
            c = Database.getInstance();
            Statement s = c.createStatement();

            ResultSet res2 = s.executeQuery("SELECT COUNT(*) AS anzahl_filme FROM film  WHERE film_id IN (SELECT film_id FROM film_actor WHERE actor_id = " + id + " )");

            if (res2.next()) {
                res = res2.getInt("anzahl_filme");
            }

            s.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    public static ObservableList<Actor> getList(){
        Connection c = null;
        ObservableList<Actor> actors = FXCollections.observableArrayList();
        try {
            c = Database.getInstance();
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

    public ObservableList<Film> getMovies(){
        ObservableList<Film> res = FXCollections.observableArrayList();
        Connection c = null;

        try {
            c = Database.getInstance();
            Statement s = c.createStatement();

            ResultSet results = s.executeQuery("SELECT * FROM film WHERE film_id IN (SELECT film_id FROM film_actor WHERE actor_id = "+ id + " )");
            //SELECT f.* FROM actor a INNER JOIN film_actor fa ON (a.actor_id = fa.actor_id) INNER JOIN file f ON (f.film_id = fa.film_id) WHERE a.actor_id = "+ this.id
            while (results.next()) {
                res.add(new Film(results));
            }

            results.close();
            s.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    @Override
    public String toString() {
        return firstname + " " + lastname;
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
}
