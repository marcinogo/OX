package ogo.marcin.ox.board;

import java.util.Objects;

/**
 * @author Marcin Ogorzalek
 */
class Field {

  final Sign sign;
  private int fieldNumber;

  Field(Sign sign) {
    this(sign, 0);
  }

  public Field(int fieldNumber) {
    this(Sign.DEFAULT, fieldNumber);
  }

  Field(Sign sign, int fieldNumber) {
    this.sign = sign;
    this.fieldNumber = fieldNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
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
    if (!sign.equals(Sign.DEFAULT)) {
      return String.format("[%4s ]", sign);
    }
    return String.format("[%4d ]", fieldNumber);
  }

  int getFieldNumber() {
    return fieldNumber;
  }
}
