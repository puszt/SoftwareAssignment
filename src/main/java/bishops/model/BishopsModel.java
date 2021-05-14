package bishops.model;

import javafx.beans.property.ObjectProperty;
import java.util.Optional;

import java.util.*;

/**
 * Class that represents a model of the Bishops game board.
 */
public class BishopsModel{
    /**
     * The {@code height} of the board.
     */
    public static int HEIGHT = 5;
    /**
     * The {@code width} of the board.
     */
    public static int WIDTH = 4;
    /**
     * The {@code array} that contains the {@code pieces}.
     */
    private Piece[] pieces;

    /**
     * Constructs a model with the {@code pieces} on the starting {@code Positions}.
     */
    public BishopsModel() {
        this(new Piece(PieceType.BLACK, new Position(0,0)),
                new Piece(PieceType.BLACK, new Position(0,1)),
                new Piece(PieceType.BLACK, new Position(0,2)),
                new Piece(PieceType.BLACK, new Position(0,3)),
                new Piece(PieceType.WHITE, new Position(4,0)),
                new Piece(PieceType.WHITE, new Position(4,1)),
                new Piece(PieceType.WHITE, new Position(4,2)),
                new Piece(PieceType.WHITE, new Position(4,3)));
    }
    /**
     * Constructs a model by giving it's {@code pieces} one-by-one, in an {@code list}.
     * @param pieces the {@code list} that contains the {@code pieces}.
     */
    public BishopsModel(Piece... pieces){
        checkPieces(pieces);
        this.pieces = pieces.clone();
    }

    /**
     * Checks if the {@code piese positions} you gave to construct the model are valid or not.
     * @param pieces the array of {@code pieces} you want to check.
     */
    private void checkPieces(Piece[] pieces){
        if (pieces.length != 8){
            throw new IllegalArgumentException();
        }
        var seen = new HashSet<Position>();
        int blackPieces = 0;
        int whitePieces = 0;
        for (var piece : pieces){
            if (! isOnBoard(piece.getPosition()) || seen.contains(piece.getPosition())){
                throw new IllegalArgumentException();
            }
            if(piece.getType() == PieceType.BLACK){
                blackPieces++;
            }
            if(piece.getType() == PieceType.WHITE){
                whitePieces++;
            }
            seen.add(piece.getPosition());
        }
        if (whitePieces != 4 || blackPieces != 4){
            throw new IllegalArgumentException();
        }
    }

    /**
     * Checks if a {@code position} is on the board or not.
     * @param position the {@code position} you want to check.
     * @return if a {@code position} is on the board or not.
     */
    public static boolean isOnBoard(Position position){
        return 0 <= position.row() && position.row() < HEIGHT
                && 0 <= position.col() && position.col() < WIDTH;
    }

    /**
     * Returns the number of pieces on the board.
     * @return the number of pieces on the board.
     */
    public int getPieceCount(){
        return pieces.length;
    }

    /**
     * Returns the {@code type} of the {@code piece} with the specific number.
     * @param pieceNumber the number of the {@code piece} you want to get the type of.
     * @return the {@code type} of the {@code piece} with the specific number.
     */
    public PieceType getPieceType(int pieceNumber) {
        return pieces[pieceNumber].getType();
    }

    /**
     * Returns the {@code position} of the {@code piece} with the specific number.
     * @param pieceNumber the number of the {@code piece} you want to get the position of.
     * @return the {@code position} of the {@code piece} with the specific number.
     */
    public Position getPiecePosition(int pieceNumber) {
        return pieces[pieceNumber].getPosition();
    }

    /**
     * Returns the {@code position} of the {@code piece} with the specific number as an {@code Object Property}.
     * @param pieceNumber the number of the {@code piece} you want to get the position of.
     * @return the {@code position} of the {@code piece} with the specific number as an {@code Object Property}.
     */
    public ObjectProperty<Position> positionProperty(int pieceNumber) {
        return pieces[pieceNumber].positionProperty();
    }

    /**
     * Checks if a {@code move} is valid or not.
     * @param pieceNumber the number of the {@code piece} you want to move.
     * @param direction the direction you want to move the {@code piece} by.
     * @return if a {@code move} is valid or not.
     */
    public boolean isValidMove(int pieceNumber, Directions direction) {
        if (pieceNumber < 0 || pieceNumber >= pieces.length) {
            throw new IllegalArgumentException();
        }
        Position newPosition = pieces[pieceNumber].getPosition().moveTo(direction);
        if (! isOnBoard(newPosition)){
            return false;
        }
        for (var piece : pieces) {
            if (piece.getPosition().equals(newPosition)) {
                return false;
            }
        }
        for (var checkDirection : Directions.values()){
            Position checkPosition = newPosition.moveTo(checkDirection);
            for(var piece : pieces){
                if(piece.getPosition().equals(checkPosition)){
                    if (piece.getType() != pieces[pieceNumber].getType()){
                        return false;
                    }
                }
            }
        }
        for (var reverseDirection : Directions.values()){
            if (Math.signum(reverseDirection.getRowChange()) == Math.signum(direction.getRowChange()) ||
                    Math.signum(reverseDirection.getColChange()) == Math.signum(direction.getColChange())){
                continue;
            }else {
                Position reversePosition = newPosition.moveTo(reverseDirection);
                for(var piece : pieces){
                    if(piece.getPosition().equals(reversePosition)){
                        if (piece != pieces[pieceNumber]){
                            return false;
                        }else {
                            return true;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Returns a set of the valid moves of a {@code piece} with a specific number.
     * @param pieceNumber the number of the {@code piece} you want to get the valid moves of.
     * @return a set of the valid moves of a {@code piece} with a specific number.
     */
    public Set<Directions> getValidMoves(int pieceNumber){
        EnumSet<Directions> validMoves = EnumSet.noneOf(Directions.class);
        for (var direction : Directions.values()){
            if (isValidMove(pieceNumber, direction)){
                validMoves.add(direction);
            }
        }
        return validMoves;
    }

    /**
     * Moves a {@code piece} with a specific number by {@code direction}.
     * @param pieceNumber the number of the {@code piece} you want to move.
     * @param direction the direction you want to move the {@code piece} by.
     */
    public void move(int pieceNumber, Directions direction){
        pieces[pieceNumber].moveTo(direction);
    }

    /**
     * Returns a list of the {@code positions} of the pieces.
     * @return a list of the {@code positions} of the pieces.
     */
    public List<Position> getPiecePositions() {
        List<Position> positions = new ArrayList<>(pieces.length);
        for (var piece : pieces) {
            positions.add(piece.getPosition());
        }
        return positions;
    }

    /**
     * Returns the number of the {@code piece} on a specific {@code position}.
     * @param position the {@code position} you want to get the piece number of.
     * @return the number of the {@code piece} on a specific {@code position}.
     */
    public OptionalInt getPieceNumber(Position position) {
        for (int i = 0; i < pieces.length; i++) {
            if (pieces[i].getPosition().equals(position)) {
                return OptionalInt.of(i);
            }
        }
        return OptionalInt.empty();
    }

    /**
     * Checks if a current state of a board is goal state or not.
     * @return if a current state of a board is goal state or not.
     */
    public boolean isGoal(){
        for(var piece : pieces){
            var pieceType = piece.getType();
            switch (pieceType){
                case BLACK -> {
                    if (piece.getPosition().row() != 4){
                        return false;
                    }
                }
                case WHITE -> {
                    if (piece.getPosition().row() != 0){
                        return false;
                    }
                }
            }
        }
        return true;
    }


    /**
     * Resets the board to it's initial state.
     */
    public void restart(){
        int blackColumns = 0;
        int whiteColumns = 0;
        for(var piece : pieces){
            if (piece.getType() == PieceType.BLACK){
                var position = new Position(0,blackColumns);
                piece.positionProperty().set(position);
                blackColumns++;
            }else {
                var position = new Position(4,whiteColumns);
                piece.positionProperty().set(position);
                whiteColumns++;
            }
        }

    }

    /**
     * Returns the string representation of this object. The result is
     * {@code [} piece {@code ,} piece {@code ,} {@code ...} {@code ,}piece{@code ]}.
     * @return the string representation of this position.
     */
    public String toString() {
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        for (var piece : pieces) {
            joiner.add(piece.toString());
        }
        return joiner.toString();
    }
}
