package at.ahammer.heroquest.subject.look;

import at.ahammer.boardgame.board.TestWithDummyBoard;
import at.ahammer.boardgame.cdi.GameContext;
import at.ahammer.heroquest.object.field.Door;
import org.junit.Assert;
import org.junit.Test;

import javax.inject.Inject;

/**
 * Created by andreas on 8/29/14.
 */
public class LookDefaultTest extends TestWithDummyBoard {

    @Inject
    private LookDefault look;

    @Test
    public void testOk() {
        Assert.assertTrue(look.canLook(getField(0, 4), getField(1, 4)));
    }

    @Test
    public void testNotOk() {
        Assert.assertFalse(look.canLook(getField(0, 4), getField(0, 3)));
    }

    @Test
    public void testLook() {
        Assert.assertFalse(look.canLook(getField(4, 1), getField(2, 3)));
        Assert.assertTrue(look.canLook(getField(3, 1), getField(2, 3)));

        Assert.assertFalse(look.canLook(getField(3, 1), getField(3, 3)));
        Assert.assertFalse(look.canLook(getField(3, 1), getField(4, 3)));
        GameContext.current().getNewInstanceInGameContext(Door.class, "Door:3,2-3,3").open();
        Assert.assertTrue(look.canLook(getField(3, 1), getField(3, 3)));
        Assert.assertFalse(look.canLook(getField(3, 1), getField(4, 3)));
    }
}
