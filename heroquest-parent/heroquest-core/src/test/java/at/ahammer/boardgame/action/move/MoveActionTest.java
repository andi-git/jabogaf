package at.ahammer.boardgame.action.move;

import at.ahammer.boardgame.action.ActionNotPossibleException;
import at.ahammer.boardgame.board.TestWithDummyBoard;
import at.ahammer.boardgame.controller.PlayerController;
import at.ahammer.boardgame.test.util.BeforeInGameContext;
import at.ahammer.heroquest.object.field.Door;
import at.ahammer.heroquest.subject.Barbarian;
import at.ahammer.heroquest.subject.Mage;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.inject.Inject;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MoveActionTest extends TestWithDummyBoard {

    @Inject
    private MoveAction moveAction;

    @Test
    public void testPerformAction() throws Exception {
        Door door = getById("Door:1,3-1,4", Door.class);
        Assert.assertFalse(door.isVisible());
        moveAction.move(barbarian, getField(1, 4));
        // after every move the DiscoverAction is triggered
        Assert.assertTrue(door.isVisible());

    }

    @Test(expected = ActionNotPossibleException.class)
    public void testPerformActionNotPossibleBecauseOfWall() throws Exception {
        moveAction.move(barbarian, getField(0, 3));
    }

    @Test(expected = ActionNotPossibleException.class)
    public void testPerformActionNotPossibleBecauseNotActionPlayer() throws Exception {
        playerController.setCurrentPlayer(mage);
        moveAction.move(barbarian, getField(1, 4));
    }
}