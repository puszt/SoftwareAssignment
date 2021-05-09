// CHECKSTYLE:OFF
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
import org.tinylog.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ArrayList.*;
import java.util.Collections;
import java.util.List;
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

    private List<Label> names = new ArrayList<>();
    private List<Label> scores = new ArrayList<>();

    @FXML
    private void initialize(){
        Collections.addAll(names,name1,name2,name3,name4,name5,name6,name7,name8,name9,name10);
        Collections.addAll(scores,score1,score2,score3,score4,score5,score6,score7,score8,score9,score10);
        for (int i = 0; i < HomeScreenController.highscoresList.size(); i++) {
            names.get(i).setText(HomeScreenController.highscoresList.get(i).getName());
            scores.get(i).setText(String.valueOf(HomeScreenController.highscoresList.get(i).getScore()));
        }
    }

    @FXML
    private void onBack(ActionEvent event) throws IOException{
        Stage stage = (Stage)  ((MenuItem)event.getSource()).getParentPopup().getOwnerWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/HomeScreen.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
        Logger.debug("Click on Back");
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
            }
            HomeScreenController.highscoresList.clear();
            for(var name:names){
                name.setText("");
            }
            for(var score:scores){
                score.setText("");
            }
            Logger.debug("Click on Reset Leaderboard");
        }
    }
}
