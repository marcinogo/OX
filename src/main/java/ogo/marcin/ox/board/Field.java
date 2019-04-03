package ogo.marcin.ox.board;

import java.util.Objects;

/**
 * @author Marcin Ogorzalek
 */
class Field {
    final Sign sign;
    private int fieldNumber;

    Field(Sign sign) {
        this.sign = sign;
    }

    Field(Sign sign, int fieldNumber) {
        this.sign = sign;
        this.fieldNumber = fieldNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;
        return sign == field.sign;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sign);
    }

    Field changeSign(Sign newSign) {
        return new Field(newSign);
    }

    @Override
    public String toString() {
        if(!sign.equals(Sign.DEFAULT))return String.format("[%s]", sign);
        return String.format("[%d]", fieldNumber);
    }
}
