package at.ahammer.boardgame.object.field;

import at.ahammer.boardgame.board.TestWithDummyBoard;
import at.ahammer.boardgame.cdi.GameContext;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by andreas on 8/23/14.
 */
public class FieldGroupTest extends TestWithDummyBoard {

    @Test
    public void testFieldGroup() {
        Field field01 = GameContext.current().getNewInstanceInGameContext(Field.class, "Field:0,1");
        Field field11 = GameContext.current().getNewInstanceInGameContext(Field.class, "Field:1,1");
        Field field33 = GameContext.current().getNewInstanceInGameContext(Field.class, "Field:3,3");
        FieldGroup fieldGroup = GameContext.current().getNewInstanceInGameContext(FieldGroup.class, "FieldGroup:1");
        Assert.assertEquals(6, fieldGroup.getFields().size());
        Assert.assertTrue(fieldGroup.contains(field01));
        Assert.assertTrue(fieldGroup.contains(field11));
        Assert.assertFalse(fieldGroup.contains(field33));
    }
}
