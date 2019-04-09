package ogo.marcin.ox.board;

import ogo.marcin.ox.board.Field;
import ogo.marcin.ox.board.Sign;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Marcin Ogorzalek
 */

@Test
public class FieldTest {
    public void testIfFieldHaveSign() {
        Field field = new Field(Sign.DEFAULT);
        assert field.sign.equals(Sign.DEFAULT) : "Field have different sign than DEFAULT";
    }

    @DataProvider
    public static Object[][] testEquality(){
        return new Object[][] {
            {Sign.DEFAULT, Sign.DEFAULT, true},
            {Sign.X, Sign.X, true},
            {Sign.O, Sign.O, true},
            {Sign.DEFAULT, Sign.O, false},
            {Sign.DEFAULT, Sign.X, false},
            {Sign.O, Sign.X, false},
            {Sign.O, Sign.DEFAULT, false},
            {Sign.X, Sign.DEFAULT, false},
            {Sign.X, Sign.O, false},
        };
    }

    @Test(dataProvider = "testEquality")
    public void testFieldsEquality(Sign sign1, Sign sign2, boolean equalityResult) {
        Field field1 = new Field(sign1);
        Field field2 = new Field(sign2);

        assert field1.equals(field2) == equalityResult: String.format("Equality for Fields should be %s",
                equalityResult);
    }

    @DataProvider
    public static Object[][] testHashCode(){
        return new Object[][] {
                {Sign.DEFAULT, Sign.DEFAULT, true},
                {Sign.X, Sign.X, true},
                {Sign.O, Sign.O, true},
                {Sign.DEFAULT, Sign.O, false},
                {Sign.DEFAULT, Sign.X, false},
                {Sign.O, Sign.X, false},
                {Sign.O, Sign.DEFAULT, false},
                {Sign.X, Sign.DEFAULT, false},
                {Sign.X, Sign.O, false},
        };
    }

    @Test(dataProvider = "testHashCode")
    public void testHashCodeEquality(Sign sign1, Sign sign2, boolean hashCodeEqualityResult) {
        Field field1 = new Field(sign1);
        Field field2 = new Field(sign2);
        boolean hashCodeComparisonResult = field1.hashCode() == field2.hashCode();
        assert  hashCodeComparisonResult == hashCodeEqualityResult: String.format("Comparison for hashCodes " +
                        "should return %s", hashCodeEqualityResult);
    }

    @DataProvider
    public static Object[][] testChangeSign(){
        return new Object[][] {
                {Sign.DEFAULT, Sign.DEFAULT},
                {Sign.X, Sign.X},
                {Sign.O, Sign.O},
                {Sign.DEFAULT, Sign.O},
                {Sign.DEFAULT, Sign.X},
                {Sign.O, Sign.X},
                {Sign.O, Sign.DEFAULT},
                {Sign.X, Sign.DEFAULT},
                {Sign.X, Sign.O},
        };
    }

    @Test(dataProvider = "testChangeSign")
    public void testIfFieldReturnFieldWithNewSign(Sign startingSign, Sign newSign) {
        Field field = new Field(startingSign);
        Field fieldWithNewValue = field.changeSign(newSign);
        assert fieldWithNewValue.equals(new Field(newSign)) : String.format("changeSign should return " +
                "Field with %s sign", newSign);
    }


    @DataProvider
    public static Object[][] testToString(){
        return new Object[][] {
                {Sign.DEFAULT, String.format("[%4d ]",0)},
                {Sign.X, String.format("[%4s ]","X")},
                {Sign.O, String.format("[%4s ]","O")},
        };
    }

    @Test(dataProvider = "testToString")
    public void testIfToStringReturnCorrectString(Sign sign, String toStringResult) {
        Field field = new Field(sign);
        assert field.toString().equals(toStringResult) : String.format("Produced string should be %s", toStringResult);
    }

    @DataProvider
    public static Object[][] createFieldWithNumber(){
        return new Object[][] {
                {Sign.DEFAULT, 1},
                {Sign.X, 2},
                {Sign.O, 30},
                {Sign.DEFAULT, 30},
                {Sign.O, 50},
                {Sign.X, 30},
                {Sign.X, 1},
                {Sign.O, 0},
        };
    }

    @Test(dataProvider = "createFieldWithNumber")
    public void testIfFieldHaveCorrectNumber(Sign sign, int fieldNumber) {
        Field field = new Field(sign, fieldNumber);
        assert field.getFieldNumber() == fieldNumber : String.format("Field should have %d field number", fieldNumber);
    }
}
