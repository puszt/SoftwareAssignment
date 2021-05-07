package bishops.model;

import javafx.beans.property.ObjectProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BishopsModelTest {
    BishopsModel model1 = new BishopsModel();

    BishopsModel model2 = new BishopsModel(
            new Piece(PieceType.BLACK, new Position(4,0)),
            new Piece(PieceType.BLACK, new Position(4,1)),
            new Piece(PieceType.BLACK, new Position(4,2)),
            new Piece(PieceType.BLACK, new Position(4,3)),
            new Piece(PieceType.WHITE, new Position(0,0)),
            new Piece(PieceType.WHITE, new Position(0,1)),
            new Piece(PieceType.WHITE, new Position(0,2)),
            new Piece(PieceType.WHITE, new Position(0,3))
    );

    BishopsModel model3 = new BishopsModel(
            new Piece(PieceType.BLACK, new Position(0,0)),
            new Piece(PieceType.BLACK, new Position(1,2)),
            new Piece(PieceType.BLACK, new Position(0,2)),
            new Piece(PieceType.BLACK, new Position(3,0)),
            new Piece(PieceType.WHITE, new Position(4,0)),
            new Piece(PieceType.WHITE, new Position(1,0)),
            new Piece(PieceType.WHITE, new Position(4,2)),
            new Piece(PieceType.WHITE, new Position(4,3))
    );
    BishopsModel model4 = new BishopsModel(
            new Piece(PieceType.BLACK, new Position(4,0)),
            new Piece(PieceType.BLACK, new Position(4,1)),
            new Piece(PieceType.BLACK, new Position(4,2)),
            new Piece(PieceType.BLACK, new Position(4,3)),
            new Piece(PieceType.WHITE, new Position(0,0)),
            new Piece(PieceType.WHITE, new Position(1,2)),
            new Piece(PieceType.WHITE, new Position(0,2)),
            new Piece(PieceType.WHITE, new Position(0,3))
    );

    @Test
    void testConstructor_invalid(){
        assertThrows(IllegalArgumentException.class, () -> new BishopsModel(
                new Piece(PieceType.BLACK, new Position(0,0))
        ));
        assertThrows(IllegalArgumentException.class, () -> new BishopsModel(
                new Piece(PieceType.BLACK, new Position(0,0)),
                new Piece(PieceType.BLACK, new Position(0,4)),
                new Piece(PieceType.BLACK, new Position(0,2)),
                new Piece(PieceType.BLACK, new Position(0,3)),
                new Piece(PieceType.WHITE, new Position(4,0)),
                new Piece(PieceType.WHITE, new Position(4,1)),
                new Piece(PieceType.WHITE, new Position(4,2)),
                new Piece(PieceType.WHITE, new Position(4,3))
        ));
        assertThrows(IllegalArgumentException.class, () -> new BishopsModel(
                new Piece(PieceType.BLACK, new Position(0,0)),
                new Piece(PieceType.BLACK, new Position(0,0)),
                new Piece(PieceType.BLACK, new Position(0,2)),
                new Piece(PieceType.BLACK, new Position(0,3)),
                new Piece(PieceType.WHITE, new Position(4,0)),
                new Piece(PieceType.WHITE, new Position(4,1)),
                new Piece(PieceType.WHITE, new Position(4,2)),
                new Piece(PieceType.WHITE, new Position(4,3))
        ));
        assertThrows(IllegalArgumentException.class, () -> new BishopsModel(
                new Piece(PieceType.BLACK, new Position(0,0)),
                new Piece(PieceType.BLACK, new Position(0,0)),
                new Piece(PieceType.BLACK, new Position(0,2)),
                new Piece(PieceType.BLACK, new Position(0,3)),
                new Piece(PieceType.BLACK, new Position(4,0)),
                new Piece(PieceType.WHITE, new Position(4,1)),
                new Piece(PieceType.WHITE, new Position(4,2)),
                new Piece(PieceType.WHITE, new Position(4,3))
        ));
    }

    @Test
    void isOnBoard() {
        assertFalse(model1.isOnBoard(new Position(-1,0)));
        assertFalse(model1.isOnBoard(new Position(5,0)));
        assertFalse(model1.isOnBoard(new Position(0,-1)));
        assertFalse(model1.isOnBoard(new Position(0,4)));
        assertTrue(model1.isOnBoard(new Position(2,2)));

    }

    @Test
    void getPieceCount() {
        assertEquals(8,model1.getPieceCount());
        assertEquals(model1.getPieceCount(),model2.getPieceCount());
        assertEquals(model1.getPieceCount(),model3.getPieceCount());
    }

    @Test
    void getPieceType() {
        assertEquals(PieceType.BLACK, model1.getPieceType(0));
        assertEquals(PieceType.BLACK, model1.getPieceType(1));
        assertEquals(PieceType.BLACK, model1.getPieceType(2));
        assertEquals(PieceType.BLACK, model1.getPieceType(3));
        assertEquals(PieceType.WHITE, model1.getPieceType(4));
        assertEquals(PieceType.WHITE, model1.getPieceType(5));
        assertEquals(PieceType.WHITE, model1.getPieceType(6));
        assertEquals(PieceType.WHITE, model1.getPieceType(7));
    }

    @Test
    void getPiecePosition() {
        assertEquals(new Position(0,0),model1.getPiecePosition(0));
        assertEquals(new Position(0,1),model1.getPiecePosition(1));
        assertEquals(new Position(0,2),model1.getPiecePosition(2));
        assertEquals(new Position(0,3),model1.getPiecePosition(3));
        assertEquals(new Position(4,0),model1.getPiecePosition(4));
        assertEquals(new Position(4,1),model1.getPiecePosition(5));
        assertEquals(new Position(4,2),model1.getPiecePosition(6));
        assertEquals(new Position(4,3),model1.getPiecePosition(7));
    }


    @Test
    void isValidMove() {
        assertThrows(IllegalArgumentException.class, () -> model1.isValidMove(-1,Directions.DOWN_LEFT_ONE));
        assertThrows(IllegalArgumentException.class, () -> model1.isValidMove(10,Directions.DOWN_LEFT_ONE));
        assertFalse(model1.isValidMove(0,Directions.UP_LEFT_THREE));
        assertFalse(model4.isValidMove(6,Directions.UP_RIGHT_ONE));
        assertFalse(model1.isValidMove(0,Directions.DOWN_RIGHT_TWO));
        assertTrue(model1.isValidMove(0,Directions.DOWN_RIGHT_ONE));
    }

    @Test
    void getValidMoves_0() {
        EnumSet<Directions> validMoves0 = EnumSet.noneOf(Directions.class);
        EnumSet<Directions> validMoves1 = EnumSet.noneOf(Directions.class);
        EnumSet<Directions> validMoves2 = EnumSet.noneOf(Directions.class);
        EnumSet<Directions> validMoves3 = EnumSet.noneOf(Directions.class);
        EnumSet<Directions> validMoves4 = EnumSet.noneOf(Directions.class);
        EnumSet<Directions> validMoves5 = EnumSet.noneOf(Directions.class);
        EnumSet<Directions> validMoves6 = EnumSet.noneOf(Directions.class);
        EnumSet<Directions> validMoves7 = EnumSet.noneOf(Directions.class);
        validMoves0.add(Directions.DOWN_RIGHT_ONE);
        validMoves1.add(Directions.DOWN_RIGHT_ONE);
        validMoves2.add(Directions.DOWN_LEFT_ONE);
        validMoves3.add(Directions.DOWN_LEFT_ONE);
        validMoves4.add(Directions.UP_RIGHT_ONE);
        validMoves5.add(Directions.UP_RIGHT_ONE);
        validMoves6.add(Directions.UP_LEFT_ONE);
        validMoves7.add(Directions.UP_LEFT_ONE);
        assertEquals(validMoves0,model1.getValidMoves(0));
        assertEquals(validMoves1,model1.getValidMoves(1));
        assertEquals(validMoves2,model1.getValidMoves(2));
        assertEquals(validMoves3,model1.getValidMoves(3));
        assertEquals(validMoves4,model1.getValidMoves(4));
        assertEquals(validMoves5,model1.getValidMoves(5));
        assertEquals(validMoves6,model1.getValidMoves(6));
        assertEquals(validMoves7,model1.getValidMoves(7));
    }

    @Test
    void move_down_right() {
        var oldPosition0 = model1.getPiecePosition(0);
        var oldPosition1 = model1.getPiecePosition(1);
        var oldPosition2 = model1.getPiecePosition(2);
        model1.move(0,Directions.DOWN_RIGHT_ONE);
        model1.move(1,Directions.DOWN_RIGHT_TWO);
        model1.move(2,Directions.DOWN_RIGHT_THREE);
        assertEquals(new Position(oldPosition0.row()+Directions.DOWN_RIGHT_ONE.getRowChange(),
                oldPosition0.col()+Directions.DOWN_RIGHT_ONE.getColChange())
                ,model1.getPiecePosition(0));
        assertEquals(new Position(oldPosition1.row()+Directions.DOWN_RIGHT_TWO.getRowChange(),
                        oldPosition1.col()+Directions.DOWN_RIGHT_TWO.getColChange())
                ,model1.getPiecePosition(1));
        assertEquals(new Position(oldPosition2.row()+Directions.DOWN_RIGHT_THREE.getRowChange(),
                        oldPosition2.col()+Directions.DOWN_RIGHT_THREE.getColChange())
                ,model1.getPiecePosition(2));

    }
    @Test
    void move_down_left() {
        var oldPosition0 = model1.getPiecePosition(0);
        var oldPosition1 = model1.getPiecePosition(1);
        var oldPosition2 = model1.getPiecePosition(2);
        model1.move(0,Directions.DOWN_LEFT_ONE);
        model1.move(1,Directions.DOWN_LEFT_TWO);
        model1.move(2,Directions.DOWN_LEFT_THREE);
        assertEquals(new Position(oldPosition0.row()+Directions.DOWN_LEFT_ONE.getRowChange(),
                        oldPosition0.col()+Directions.DOWN_LEFT_ONE.getColChange())
                ,model1.getPiecePosition(0));
        assertEquals(new Position(oldPosition1.row()+Directions.DOWN_LEFT_TWO.getRowChange(),
                        oldPosition1.col()+Directions.DOWN_LEFT_TWO.getColChange())
                ,model1.getPiecePosition(1));
        assertEquals(new Position(oldPosition2.row()+Directions.DOWN_LEFT_THREE.getRowChange(),
                        oldPosition2.col()+Directions.DOWN_LEFT_THREE.getColChange())
                ,model1.getPiecePosition(2));

    }
    @Test
    void move_up_right() {
        var oldPosition0 = model1.getPiecePosition(0);
        var oldPosition1 = model1.getPiecePosition(1);
        var oldPosition2 = model1.getPiecePosition(2);
        model1.move(0,Directions.UP_RIGHT_ONE);
        model1.move(1,Directions.UP_RIGHT_TWO);
        model1.move(2,Directions.UP_RIGHT_THREE);
        assertEquals(new Position(oldPosition0.row()+Directions.UP_RIGHT_ONE.getRowChange(),
                        oldPosition0.col()+Directions.UP_RIGHT_ONE.getColChange())
                ,model1.getPiecePosition(0));
        assertEquals(new Position(oldPosition1.row()+Directions.UP_RIGHT_TWO.getRowChange(),
                        oldPosition1.col()+Directions.UP_RIGHT_TWO.getColChange())
                ,model1.getPiecePosition(1));
        assertEquals(new Position(oldPosition2.row()+Directions.UP_RIGHT_THREE.getRowChange(),
                        oldPosition2.col()+Directions.UP_RIGHT_THREE.getColChange())
                ,model1.getPiecePosition(2));

    }
    @Test
    void move_up_left() {
        var oldPosition0 = model1.getPiecePosition(0);
        var oldPosition1 = model1.getPiecePosition(1);
        var oldPosition2 = model1.getPiecePosition(2);
        model1.move(0,Directions.UP_LEFT_ONE);
        model1.move(1,Directions.UP_LEFT_TWO);
        model1.move(2,Directions.UP_LEFT_THREE);
        assertEquals(new Position(oldPosition0.row()+Directions.UP_LEFT_ONE.getRowChange(),
                        oldPosition0.col()+Directions.UP_LEFT_ONE.getColChange())
                ,model1.getPiecePosition(0));
        assertEquals(new Position(oldPosition1.row()+Directions.UP_LEFT_TWO.getRowChange(),
                        oldPosition1.col()+Directions.UP_LEFT_TWO.getColChange())
                ,model1.getPiecePosition(1));
        assertEquals(new Position(oldPosition2.row()+Directions.UP_LEFT_THREE.getRowChange(),
                        oldPosition2.col()+Directions.UP_LEFT_THREE.getColChange())
                ,model1.getPiecePosition(2));

    }

    @Test
    void getPiecePositions() {
        List<Position> piecePositions = new ArrayList<>();
        piecePositions.add(0, new Position(0,0));
        piecePositions.add(1, new Position(0,1));
        piecePositions.add(2, new Position(0,2));
        piecePositions.add(3, new Position(0,3));
        piecePositions.add(4, new Position(4,0));
        piecePositions.add(5, new Position(4,1));
        piecePositions.add(6, new Position(4,2));
        piecePositions.add(7, new Position(4,3));
        assertEquals(piecePositions,model1.getPiecePositions());
    }

    @Test
    void getPieceNumber() {
        assertEquals(OptionalInt.of(0),model1.getPieceNumber(new Position(0,0)));
        assertEquals(OptionalInt.of(1),model1.getPieceNumber(new Position(0,1)));
        assertEquals(OptionalInt.of(2),model1.getPieceNumber(new Position(0,2)));
        assertEquals(OptionalInt.of(3),model1.getPieceNumber(new Position(0,3)));
        assertEquals(OptionalInt.of(4),model1.getPieceNumber(new Position(4,0)));
        assertEquals(OptionalInt.of(5),model1.getPieceNumber(new Position(4,1)));
        assertEquals(OptionalInt.of(6),model1.getPieceNumber(new Position(4,2)));
        assertEquals(OptionalInt.of(7),model1.getPieceNumber(new Position(4,3)));
    }

    @Test
    void testToString() {
        assertEquals("[BLACK(0,0),BLACK(0,1),BLACK(0,2),BLACK(0,3),WHITE(4,0),WHITE(4,1),WHITE(4,2),WHITE(4,3)]", model1.toString());
        assertEquals("[BLACK(4,0),BLACK(4,1),BLACK(4,2),BLACK(4,3),WHITE(0,0),WHITE(0,1),WHITE(0,2),WHITE(0,3)]", model2.toString());
        assertEquals("[BLACK(0,0),BLACK(1,2),BLACK(0,2),BLACK(3,0),WHITE(4,0),WHITE(1,0),WHITE(4,2),WHITE(4,3)]", model3.toString());
    }

    @Test
    void isGoal() {
        assertFalse(model1.isGoal());
        assertTrue(model2.isGoal());
        assertFalse(model3.isGoal());
    }

    @Test
    void restart() {
    }
}