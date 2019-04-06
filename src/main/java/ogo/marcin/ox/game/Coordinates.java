package ogo.marcin.ox.game;

/**
 * @author Marcin Ogorzalek
 */
public class Coordinates {
    private static final int MIN_COMPONENT_OF_MOVE_INDEX = 0;
    static private int MAX_COMPONENT_OF_MOVE_INDEX;

    private final int xOfMove;
    private final int yOfMove;

    public static void setCoordinatesMaxXY(int boardEdge) {
        Coordinates.MAX_COMPONENT_OF_MOVE_INDEX = boardEdge;
    }

    private Coordinates(int xOfMove, int yOfMove) {
        this.xOfMove = xOfMove;
        this.yOfMove = yOfMove;
    }

    private Coordinates(CoordinatesBuilder coordinatesBuilder) {
        this(coordinatesBuilder.xOfMove, coordinatesBuilder.yOfMove);
    }

    public int getxOfMove() {
        return xOfMove;
    }

    public int getyOfMove() {
        return yOfMove;
    }

    public static class CoordinatesBuilder {
        private int xOfMove;
        private int yOfMove;

        public Coordinates build() {
            return new Coordinates(this);
        }

        public CoordinatesBuilder withMovePosition(int move) throws IllegalArgumentException {
            setXOfMove(move);
            setYOfMove(move);
            return this;
        }

        private void setXOfMove(int move) throws IllegalArgumentException {
            int xOfMove = recalculateUserInputToX(move);
            if(isComponentOfMoveNotCorrect(xOfMove)) {
                throw new IllegalArgumentException("Move is not inside board");
            }
            this.xOfMove = xOfMove;
        }

        private int recalculateUserInputToX(int move) {
            return (move - 1) % MAX_COMPONENT_OF_MOVE_INDEX;
        }

        private boolean isComponentOfMoveNotCorrect(int componentOfMove) {
            return MIN_COMPONENT_OF_MOVE_INDEX > componentOfMove || componentOfMove >= MAX_COMPONENT_OF_MOVE_INDEX ;
        }

        private void setYOfMove(int move) throws IllegalArgumentException {
            int yOfMove = recalculateUserInputToY(move);
            if(isComponentOfMoveNotCorrect(yOfMove)) {
                throw new IllegalArgumentException("Move is not inside board");
            }
            this.yOfMove = yOfMove;
        }

        private int recalculateUserInputToY(int move) {
            return (move - 1) / MAX_COMPONENT_OF_MOVE_INDEX;
        }

    }
}