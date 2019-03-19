package ogo.marcin.ox;

/**
 * @author Marcin Ogorzalek
 */
public enum Sign {
    DEFAULT(" "),
    X("X"),
    O("O");

    private String representation;

    Sign(String representation) {
        this.representation = representation;
    }

    @Override
    public String toString() {
        return representation;
    }
}
