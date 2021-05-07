package bishops.model;

import javafx.beans.property.ObjectProperty;

import java.util.*;

public class BishopsModel{

    public static int HEIGHT = 5;
    public static int WIDTH = 4;

    private final Piece[] pieces;


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

    public BishopsModel(Piece... pieces){
        checkPieces(pieces);
        this.pieces = pieces.clone();
    }

    private void checkPieces(Piece[] pieces){
        var seen = new HashSet<Position>();
        for (var piece : pieces){
            if (! isOnBoard(piece.getPosition()) ||seen.contains(piece.getPosition())){
                throw new IllegalArgumentException();
            }
            seen.add(piece.getPosition());
        }
    }

    public static boolean isOnBoard(Position position){
        return 0 <= position.row() && position.row() < HEIGHT
                && 0 <= position.col() && position.col() < WIDTH;
    }

    public int getPieceCount(){
        return pieces.length;
    }

    public PieceType getPieceType(int pieceNumber) {
        return pieces[pieceNumber].getType();
    }

    public Position getPiecePosition(int pieceNumber) {
        return pieces[pieceNumber].getPosition();
    }


    public ObjectProperty<Position> positionProperty(int pieceNumber) {
        return pieces[pieceNumber].positionProperty();
    }

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
        for (var guardedDirection : Directions.values()){
            Position guardedPosition = newPosition.moveTo(guardedDirection);
            for(var piece : pieces){
                if(piece.getPosition().equals(guardedPosition)){
                    if (piece.getType() != pieces[pieceNumber].getType()){
                        return false;
                    }
                }
            }
        }
        return true;
    }


    public Set<Directions> getValidMoves(int pieceNumber){
        EnumSet<Directions> validMoves = EnumSet.noneOf(Directions.class);
        for (var direction : Directions.values()){
            if (isValidMove(pieceNumber, direction)){
                validMoves.add(direction);
            }
        }
        return validMoves;
    }

    public void move(int pieceNumber, Directions direction){
        pieces[pieceNumber].moveTo(direction);
    }

    public List<Position> getPiecePositions() {
        List<Position> positions = new ArrayList<>(pieces.length);
        for (var piece : pieces) {
            positions.add(piece.getPosition());
        }
        return positions;
    }

    public List<ObjectProperty<Position>> positionProperties(){
        List<ObjectProperty<Position>> positions = new ArrayList<>(pieces.length);
        for (var piece : pieces) {
            positions.add(piece.positionProperty());
        }
        return positions;
    }

    public OptionalInt getPieceNumber(Position position) {
        for (int i = 0; i < pieces.length; i++) {
            if (pieces[i].getPosition().equals(position)) {
                return OptionalInt.of(i);
            }
        }
        return OptionalInt.empty();
    }

    public String toString() {
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        for (var piece : pieces) {
            joiner.add(piece.toString());
        }
        return joiner.toString();
    }



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

    public static void main(String[] args) {
        BishopsModel model = new BishopsModel();
        System.out.println(model);
        model.move(4,Directions.DOWN_LEFT_ONE);
        model.move(4,Directions.UP_RIGHT_ONE);
    }
}
