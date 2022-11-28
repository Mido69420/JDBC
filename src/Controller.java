import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;

public class Controller {

    public ListView<Film> moviesListView = new ListView<>();
    public ListView<Actor> actorsListView = new ListView<>();
    public Connection c = null;

    public String actorSelection = null;
    public Label film_count;
    public Label films_len;
    public Label countSchauspieler;

    public void initialize() {
        actorsListView.setItems(Actor.getList());
    }

    public void actorsLVClicked(MouseEvent mouseEvent) {
        moviesListView.setItems(actorsListView.getSelectionModel().getSelectedItem().getMovies());

        film_count.setText(String.valueOf(actorsListView.getSelectionModel().getSelectedItem().getFilmAnzahl()));
        films_len.setText(String.valueOf(actorsListView.getSelectionModel().getSelectedItem().getFilmLength()));

        /*
        String actor = (String) actorsListView.getSelectionModel().getSelectedItem();
        String firstname = actor.split(" ")[0];
        String lastname = actor.split(" ")[1];

        try {
            Statement s = c.createStatement();
            ResultSet results = s.executeQuery(
                    "SELECT f.* FROM actor a INNER JOIN film_actor fa ON (a.actor_id = fa.actor_id) " +
                            "INNER JOIN film f ON (f.film_id = fa.film_id)  first_name = '" + firstname + "' " +
                            "AND last_name = '" + lastname + "')");




            moviesListView.getItems().clear();

            while (results.next()) {
                moviesListView.getItems().add(results.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

         */
    }

    public void moviesLVClicked(MouseEvent mouseEvent) {
        countSchauspieler.setText(String.valueOf(moviesListView.getSelectionModel().getSelectedItem().getActorCount()));
    }
}