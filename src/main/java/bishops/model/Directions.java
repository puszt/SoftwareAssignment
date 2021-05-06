package bishops.model;

public enum Directions {

    UP_LEFT_ONE(-1, -1),
    UP_LEFT_TWO(-2, -2),
    UP_LEFT_THREE(-3 , -3),
    UP_RIGHT_ONE(-1, 1),
    UP_RIGHT_TWO(-2, 2),
    UP_RIGHT_THREE(-3,3),
    DOWN_LEFT_ONE(1, -1),
    DOWN_LEFT_TWO(2, -2),
    DOWN_LEFT_THREE(3,-3),
    DOWN_RIGHT_ONE(1,1),
    DOWN_RIGHT_TWO(2,2),
    DOWN_RIGHT_THREE(3,3);

    private final int rowChange;
    private final int colChange;

    Directions(int rowChange, int colChange){
        this.rowChange = rowChange;
        this.colChange = colChange;
    }

    public int getRowChange() {
        return rowChange;
    }

    public int getColChange() {
        return colChange;
    }


    public static Directions of(int rowChange, int colChange){
        for (Directions direction : values()){
            if (direction.rowChange == rowChange && direction.colChange == colChange){
                return direction;
            }
        }
        throw new IllegalArgumentException();
    }

}

