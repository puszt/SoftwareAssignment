package bishops.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    Position position;

    void assertPosition(int expectedRow, int expectedCol, Position position) {
        assertAll("position",
                () -> assertEquals(expectedRow, position.row()),
                () -> assertEquals(expectedCol, position.col())
        );
    }


    @BeforeEach
    void init() {
        position = new Position(0, 0);
    }

    @Test
    void moveTo(){
        assertPosition(-1, -1,position.moveTo(Directions.UP_LEFT_ONE));
        assertPosition(-2, -2,position.moveTo(Directions.UP_LEFT_TWO));
        assertPosition(-3, -3,position.moveTo(Directions.UP_LEFT_THREE));
        assertPosition(-1, 1,position.moveTo(Directions.UP_RIGHT_ONE));
        assertPosition(-2, 2,position.moveTo(Directions.UP_RIGHT_TWO));
        assertPosition(-3, 3,position.moveTo(Directions.UP_RIGHT_THREE));
        assertPosition(1, -1,position.moveTo(Directions.DOWN_LEFT_ONE));
        assertPosition(2, -2,position.moveTo(Directions.DOWN_LEFT_TWO));
        assertPosition(3, -3,position.moveTo(Directions.DOWN_LEFT_THREE));
        assertPosition(1, 1,position.moveTo(Directions.DOWN_RIGHT_ONE));
        assertPosition(2, 2,position.moveTo(Directions.DOWN_RIGHT_TWO));
        assertPosition(3, 3,position.moveTo(Directions.DOWN_RIGHT_THREE));
    }

    @Test
    void testToString(){
        assertEquals("(0,0)", position.toString());
    }
}