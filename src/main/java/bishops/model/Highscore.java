package bishops.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.tinylog.Logger;

/**
 * Class to store the scores of the players.
 */
public class Highscore {
    /**
     * The {@code name} of the player.
     */
    private StringProperty name = new SimpleStringProperty("");
    /**
     * The {@code score} of the player.
     */
    private int score;

    /**
     * Returns the score of the player.
     * @return the score of the player.
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the {@code score} of the player.
     * @param score the {@code int} you want to set the score to.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Returns the {@code name} of the player as a {@code StringProperty}
     * @return the name of the player.
     */
    public StringProperty nameProperty(){
        return name;
    }

    /**
     * Returns the {@code name} of the player.
     * @return the name of the player.
     */
    public String getName() {
        return name.get();
    }

    /**
     *Sets the {@code name} of the player.
     * @param name the {@code string} you want to set the name to.
     */
    public void setName(String name) {
        this.name.set(name);
    }
}
