package bishops.model;

/**
 * {@code Directions} that can be used
 */
public enum Directions implements Direction{
    /**
     * Up left direction by one.
     */
    UP_LEFT_ONE(-1, -1),
    /**
     * Up left direction by two.
     */
    UP_LEFT_TWO(-2, -2),
    /**
     *Up left direction by three.
     */
    UP_LEFT_THREE(-3 , -3),
    /**
     * Up right direction by one.
     */
    UP_RIGHT_ONE(-1, 1),
    /**
     * Up right direction by two.
     */
    UP_RIGHT_TWO(-2, 2),
    /**
     * Up right direction by three.
     */
    UP_RIGHT_THREE(-3,3),
    /**
     * Down left direction by one.
     */
    DOWN_LEFT_ONE(1, -1),
    /**
     * Down left direction by two.
     */
    DOWN_LEFT_TWO(2, -2),
    /**
     * Down left direction by three.
     */
    DOWN_LEFT_THREE(3,-3),
    /**
     * Down right direction by one.
     */
    DOWN_RIGHT_ONE(1,1),
    /**
     * Down right direction by two.
     */
    DOWN_RIGHT_TWO(2,2),
    /**
     *Down right direction by three.
     */
    DOWN_RIGHT_THREE(3,3);
    /**
     * The change of the {@code row} after moving a {@code direction}
     */
    private final int rowChange;
    /**
     * The change of the {@code col} after moving a {@code direction}
     */
    private final int colChange;

    /**
     * Constructs a {@code direction}.
     * @param rowChange the change of the {@code row} after moving a {@code direction}
     * @param colChange the change of the {@code col} after moving a {@code direction}
     */
    Directions(int rowChange, int colChange){
        this.rowChange = rowChange;
        this.colChange = colChange;
    }

    /**
     * Returns the change of the {@code row}.
     * @return the change of the {@code row}.
     */
    public int getRowChange() {
        return rowChange;
    }
    /**
     * Returns the change of the {@code col}.
     * @return the change of the {@code col}.
     */
    public int getColChange() {
        return colChange;
    }

    /**
     * Returns a {@code direction} based on the changes of {@code row} and {@code col}.
     * @param rowChange the change of the {@code row}.
     * @param colChange the change of the {@code col}.
     * @return a {@code direction} based on the changes of {@code row} and {@code col}.
     */
    public static Directions of(int rowChange, int colChange){
        for (Directions direction : values()){
            if (direction.rowChange == rowChange && direction.colChange == colChange){
                return direction;
            }
        }
        throw new IllegalArgumentException();
    }

}

