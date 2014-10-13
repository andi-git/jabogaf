package at.ahammer.heroquest.action.discover;

import at.ahammer.boardgame.action.ActionNotPossibleException;
import at.ahammer.boardgame.board.TestWithDummyBoard;
import at.ahammer.heroquest.object.field.Door;
import org.junit.Assert;
import org.junit.Test;

import javax.inject.Inject;

/**
 * Created by andreas on 13.10.14.
 */
public class DiscoverActionTest extends TestWithDummyBoard {

    @Inject
    private DiscoverAction discoverAction;

    @Test
    public void testDiscoverAction() throws ActionNotPossibleException {
        Door door = getById("Door:1,3-1,4", Door.class);
        Assert.assertFalse(door.isVisible());
        discoverAction.discover(barbarian, getField(1, 4));
        Assert.assertTrue(door.isVisible());
    }

    @Test(expected = ActionNotPossibleException.class)
    public void testDiscoverActionNotCurrentPlayer() throws ActionNotPossibleException {
        playerController.setCurrentPlayer(mage);
        discoverAction.discover(barbarian, getField(1, 4));
    }
}
