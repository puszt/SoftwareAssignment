package bishops;

import bishops.HomeScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

import java.io.IOException;
import java.util.Optional;

public class LeaderboardController {
    private HomeScreenController homeScreenController;

    @FXML
    private Label name1;
    @FXML
    private Label name2;
    @FXML
    private Label name3;
    @FXML
    private Label name4;
    @FXML
    private Label name5;
    @FXML
    private Label name6;
    @FXML
    private Label name7;
    @FXML
    private Label name8;
    @FXML
    private Label name9;
    @FXML
    private Label name10;
    @FXML
    private Label score1;
    @FXML
    private Label score2;
    @FXML
    private Label score3;
    @FXML
    private Label score4;
    @FXML
    private Label score5;
    @FXML
    private Label score6;
    @FXML
    private Label score7;
    @FXML
    private Label score8;
    @FXML
    private Label score9;
    @FXML
    private Label score10;

    @FXML
    private void initialize(){
        if (HomeScreenController.highscoresList.size() > 0){
            name1.setText(HomeScreenController.highscoresList.get(0).getName());
            score1.setText(String.valueOf(HomeScreenController.highscoresList.get(0).getScore()));
        }
        if (HomeScreenController.highscoresList.size() > 1){
            name2.setText(HomeScreenController.highscoresList.get(1).getName());
            score2.setText(String.valueOf(HomeScreenController.highscoresList.get(1).getScore()));
        }
        if (HomeScreenController.highscoresList.size() > 2){
            name3.setText(HomeScreenController.highscoresList.get(2).getName());
            score3.setText(String.valueOf(HomeScreenController.highscoresList.get(2).getScore()));
        }
        if (HomeScreenController.highscoresList.size() > 3){
            name4.setText(HomeScreenController.highscoresList.get(3).getName());
            score4.setText(String.valueOf(HomeScreenController.highscoresList.get(3).getScore()));
        }
        if (HomeScreenController.highscoresList.size() > 4){
            name5.setText(HomeScreenController.highscoresList.get(4).getName());
            score5.setText(String.valueOf(HomeScreenController.highscoresList.get(4).getScore()));
        }
        if (HomeScreenController.highscoresList.size() > 5){
            name6.setText(HomeScreenController.highscoresList.get(5).getName());
            score6.setText(String.valueOf(HomeScreenController.highscoresList.get(5).getScore()));
        }
        if (HomeScreenController.highscoresList.size() > 6){
            name7.setText(HomeScreenController.highscoresList.get(6).getName());
            score7.setText(String.valueOf(HomeScreenController.highscoresList.get(6).getScore()));
        }
        if (HomeScreenController.highscoresList.size() > 7){
            name8.setText(HomeScreenController.highscoresList.get(7).getName());
            score8.setText(String.valueOf(HomeScreenController.highscoresList.get(7).getScore()));
        }
        if (HomeScreenController.highscoresList.size() > 8){
            name9.setText(HomeScreenController.highscoresList.get(8).getName());
            score9.setText(String.valueOf(HomeScreenController.highscoresList.get(8).getScore()));
        }
        if (HomeScreenController.highscoresList.size() > 9){
            name10.setText(HomeScreenController.highscoresList.get(9).getName());
            score10.setText(String.valueOf(HomeScreenController.highscoresList.get(9).getScore()));
        }
    }

    @FXML
    private void onBack(ActionEvent event) throws IOException{
        Stage stage = (Stage)  ((MenuItem)event.getSource()).getParentPopup().getOwnerWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/HomeScreen.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void onResetLeaderboard(ActionEvent event) throws IOException {
        Alert quit = new Alert(Alert.AlertType.CONFIRMATION);
        quit.setTitle("Quit");
        quit.setHeaderText("Are you sure you want to quit?");
        quit.setContentText("All your previous results will be lost!");
        Optional<ButtonType> result = quit.showAndWait();
        if (result.get() == ButtonType.OK) {
            ClassLoader loader = BishopsController.class.getClassLoader();
            String pathUrl = "jdbc:h2:file:" + loader.getResource("Highscores.mv.db").getPath();
            String url = pathUrl.substring(0, pathUrl.length() - 6);
            Jdbi jdbi = Jdbi.create(url);
            try (Handle handle = jdbi.open()) {
                handle.execute("DELETE FROM HIGHSCORES");
                HomeScreenController.highscoresList.clear();
                initialize();
            }
        }
    }
}
