package ogo.marcin.ox.board;

/**
 * @author Marcin Ogorzalek
 */
public enum Sign {
    DEFAULT(" "),
    X("X"),
    O("O");

    private final String representation;

    Sign(String representation) {
        this.representation = representation;
    }

    @Override
    public String toString() {
        return representation;
    }
}
