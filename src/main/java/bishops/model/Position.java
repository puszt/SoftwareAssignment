package bishops.model;
/**
 * Class for representing positions of the board.
 * @param col the column of the {@code position} on the board.
 * @param row the row of the {@code position} on the board.
 */
public record Position(int row, int col) {
    /**
     * Moves a {@code Piece} by {@code Direction}.
     * @param direction the direction where you want to move.
     * @return the new {@code Position} after moving
     */
    public Position moveTo(Direction direction){
        return new Position(row + direction.getRowChange(), col + direction.getColChange());
    }

    /**
     * Returns the string representation of this object. The result is
     * {@code "row"},{@code "col"}.
     * @return the string representation of this position.
     */
    public String toString(){
        return String.format("(%d,%d)",row,col);
    }
}
