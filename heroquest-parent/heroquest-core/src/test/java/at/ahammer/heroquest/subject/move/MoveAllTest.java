package at.ahammer.heroquest.subject.move;

import at.ahammer.boardgame.board.TestWithDummyBoard;
import at.ahammer.boardgame.cdi.GameContext;
import at.ahammer.boardgame.subject.move.Move;
import at.ahammer.heroquest.object.field.Door;
import org.junit.Assert;
import org.junit.Test;

import javax.inject.Inject;

/**
 * Created by andreas on 8/24/14.
 */
public class MoveAllTest extends TestWithDummyBoard {

    @Inject
    @MoveStrategy(MoveAll.class)
    private Move move;

    @Test
    public void testMove() {
        // no FieldConnectionObject
        Assert.assertTrue(move.canMove(getLayout().getField(4, 0), getLayout().getField(4, 1)));
        Assert.assertTrue(move.canMove(getLayout().getField(4, 1), getLayout().getField(4, 0)));
    }

    @Test
    public void testMoveThroughWall() {
        // a wall
        Assert.assertTrue(move.canMove(getLayout().getField(4, 0), getLayout().getField(3, 0)));
        Assert.assertTrue(move.canMove(getLayout().getField(3, 0), getLayout().getField(4, 0)));
    }

    @Test
    public void testMoveThroughDoor() {
        // a closed door
        Assert.assertTrue(move.canMove(getLayout().getField(3, 1), getLayout().getField(4, 1)));
        Assert.assertTrue(move.canMove(getLayout().getField(4, 1), getLayout().getField(3, 1)));
        // open the door
        GameContext.current().getNewInstanceInGameContext(Door.class, "Door:3,1-4,1").open();
        Assert.assertTrue(move.canMove(getLayout().getField(3, 1), getLayout().getField(4, 1)));
        Assert.assertTrue(move.canMove(getLayout().getField(4, 1), getLayout().getField(3, 1)));
    }
}
