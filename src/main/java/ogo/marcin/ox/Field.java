package ogo.marcin.ox;

import java.util.Objects;

/**
 * @author Marcin Ogorzalek
 */
public class Field {
    final Sign sign;

    Field(Sign sign) {
        this.sign = sign;
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
        return String.format("[%s]", sign);
    }
}
