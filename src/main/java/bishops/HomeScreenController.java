package bishops;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.IOException;

public class HomeScreenController {


    @FXML
    private void onNewGame(ActionEvent event) throws IOException{
        Logger.debug("Click on New Game");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/ui.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML private void onQuit() throws IOException{
        Logger.debug("Click on Quit");
        Logger.debug("Exiting...");
        Platform.exit();
    }

    @FXML
    private void onAbout() throws IOException{
        Logger.debug("Click on About");
        Alert about = new Alert(Alert.AlertType.INFORMATION);
        about.setTitle("About");
        about.setHeaderText("Bishops Application");
        about.setContentText("""
            Author: Roland Pusztai
            Java version: %s, %s
            JavaFX version: %s
            """.formatted(System.getProperty("java.version"), System.getProperty("java.vendor"), System.getProperty("javafx.version")));
        about.showAndWait();
    }
}
