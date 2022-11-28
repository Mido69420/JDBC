import model.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Film {
    private int id;
    private String name;

    public Film(ResultSet db) throws SQLException {
        this.id = db.getInt("film_id");
        this.name = db.getString("title");
    }

    public int getActorCount(){
        Connection c = null;
        int actorCount = 0;

        try{
            c = Database.getInstance();
            Statement s = c.createStatement();

            ResultSet res2 = s.executeQuery("SELECT COUNT(actor_id) AS anzahl_actor FROM film_actor WHERE film_id = " + id);

            if (res2.next()) {
                actorCount = res2.getInt("anzahl_actor");
            }

            s.close();
        }catch (Exception e){
            System.out.println(e);
        }

        return actorCount;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
