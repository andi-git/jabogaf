package at.ahammer.heroquest.subject.move;

import at.ahammer.boardgame.board.TestWithDummyBoard;
import at.ahammer.boardgame.cdi.GameContext;
import at.ahammer.boardgame.subject.move.Move;
import at.ahammer.boardgame.subject.move.MoveStrategy;
import at.ahammer.heroquest.object.field.Door;
import org.junit.Assert;
import org.junit.Test;

import javax.inject.Inject;

/**
 * Created by andreas on 8/24/14.
 */
public class MoveDefaultTest extends TestWithDummyBoard {

    @Inject
    @MoveStrategy(MoveDefault.class)
    private Move move;

    @Test
    public void testMove() {
        // no FieldConnectionObject
        Assert.assertTrue(move.canMove(getLayout().getField(0, 4), getLayout().getField(1, 4)));
        Assert.assertTrue(move.canMove(getLayout().getField(1, 4), getLayout().getField(0, 4)));
    }

    @Test
    public void testMoveThroughWall() {
        // a wall
        Assert.assertFalse(move.canMove(getLayout().getField(0, 4), getLayout().getField(0, 3)));
        Assert.assertFalse(move.canMove(getLayout().getField(0, 3), getLayout().getField(0, 4)));
    }

    @Test
    public void testMoveThroughDoor() {
        // a closed door
        Assert.assertFalse(move.canMove(getLayout().getField(1, 3), getLayout().getField(1, 4)));
        Assert.assertFalse(move.canMove(getLayout().getField(1, 4), getLayout().getField(1, 3)));
        // open the door
        GameContext.current().getNewInstanceInGameContext(Door.class, "Door:1,3-1,4").open();
        Assert.assertTrue(move.canMove(getLayout().getField(1, 3), getLayout().getField(1, 4)));
        Assert.assertTrue(move.canMove(getLayout().getField(1, 4), getLayout().getField(1, 3)));
    }
}
