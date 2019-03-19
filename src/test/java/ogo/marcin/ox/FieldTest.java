package ogo.marcin.ox;

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
}
