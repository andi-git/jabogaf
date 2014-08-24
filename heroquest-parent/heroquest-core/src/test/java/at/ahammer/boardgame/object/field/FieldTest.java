package at.ahammer.boardgame.object.field;

import at.ahammer.boardgame.board.TestWithDummyBoard;
import at.ahammer.boardgame.cdi.GameContext;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by andreas on 8/23/14.
 */
public class FieldTest extends TestWithDummyBoard {

    @Test
    public void testField() {
        Field field11 = GameContext.current().getNewInstanceInGameContext(Field.class, "Field:1,1");
        Assert.assertNotNull(field11);
        Assert.assertEquals("Field:1,1", field11.getId());
        Assert.assertTrue(field11.isVisible());
    }
}
