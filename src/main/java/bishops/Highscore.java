package bishops;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.tinylog.Logger;

public class Highscore {

    private StringProperty name = new SimpleStringProperty("");
    private int score;


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public StringProperty nameProperty(){
        return name;
    }

    public String getName() {
        return name.get();
    }
}
