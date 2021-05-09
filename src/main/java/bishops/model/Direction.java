package bishops.model;

/**
 * A java interface for the different {@code Directions}
 */
public interface Direction {
    /**
     * Returns the change of the row after moving {@code Direction}.
     * @return the change of the row after moving {@code Direction}.
     */
    int getRowChange();
    /**
     * Returns the change of the col after moving {@code Direction}.
     * @return the change of the col after moving {@code Direction}.
     */
    int getColChange();
}
