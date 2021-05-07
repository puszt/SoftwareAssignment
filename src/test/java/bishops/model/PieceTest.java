package bishops.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {
    Piece blackPiece = new Piece(PieceType.BLACK,new Position(0,0));
    Piece whitePiece = new Piece(PieceType.WHITE,new Position(4,3));

    @Test
    void testToString() {
        assertEquals("BLACK(0,0)",blackPiece.toString());
        assertEquals("WHITE(4,3)",whitePiece.toString());
    }

    @Test
    void moveTo() {
        assertNotEquals(new Position(1,1),blackPiece.getPosition());
        blackPiece.moveTo(Directions.DOWN_RIGHT_ONE);
        assertEquals(new Position(1,1),blackPiece.getPosition());
    }
}