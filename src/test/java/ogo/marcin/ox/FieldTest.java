package ogo.marcin.ox;

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

    public void testIfFieldsAreEqual() {
        Field field1 = new Field(Sign.DEFAULT);
        Field field2 = new Field(Sign.DEFAULT);
        assert field1.equals(field2) : "Fields should be equal";
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
        };
    }

    @Test(dataProvider = "testEquality")
    public void testFieldsEquality(Sign sign1, Sign sign2, boolean equalityResult) {
        Field field1 = new Field(sign1);
        Field field2 = new Field(sign2);

        assert field1.equals(field2) == equalityResult: String.format("Equality for fields should be %s",
                equalityResult);
    }
}
