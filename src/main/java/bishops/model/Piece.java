package bishops.model;

import bishops.model.PieceType;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Class for representing Pieces.
 */
public class Piece {
    /**
     * The type of the piece
     */
    private final PieceType type;
    /**
     * The {@code Poisition} of the piece.
     */
    private final ObjectProperty<Position> position = new SimpleObjectProperty<>();

    /**
     * Constructs a {@code Piece} object.
     * @param type the {@code type} of the piece.
     * @param position the {@code position} where you want to construct a piece.
     */
    public Piece(PieceType type, Position position){
        this.type = type;
        this.position.set(position);
    }

    /**
     * Returns the {@code type} of the {@code piece} as a {@code PieceType}.
     * @return the type of the piece.
     */
    public PieceType getType() {
        return type;
    }
    /**
     * Returns the {@code Position} of the {@code piece} as a {@code Position Object Property}.
     * @return the position of the piece.
     */
    public ObjectProperty<Position> positionProperty(){
        return position;
    }
    /**
     * Returns the {@code Position} of the {@code piece} as a {@code Position} object.
     * @return the position of the piece.
     */
    public Position getPosition() {
        return position.get();
    }

    /**
     * Returns the string representation of this object. The result is
     * {@code "tpye"} + {@code "position"}.
     * @return the string representation of this position.
     */
    public String toString(){
        return type.toString() + position.get().toString();
    }

    /**
     * Moves the piece by the {@code Direction}.
     * @param direction the direction which by you want to move the piece.
     */
    public void moveTo(Direction direction){
        Position newPosition = position.get().moveTo(direction);
        position.set(newPosition);
    }
}
