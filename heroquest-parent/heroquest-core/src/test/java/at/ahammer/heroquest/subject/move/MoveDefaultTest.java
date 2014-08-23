package at.ahammer.heroquest.subject.move;

import at.ahammer.boardgame.cdi.GameContext;
import at.ahammer.boardgame.entity.board.Board;
import at.ahammer.boardgame.entity.board.DummyBoard;
import at.ahammer.boardgame.entity.object.field.Field;
import at.ahammer.boardgame.entity.subject.move.Move;
import at.ahammer.boardgame.test.util.ArquillianGameContext;
import at.ahammer.boardgame.test.util.ArquillianGameContextTest;
import at.ahammer.boardgame.test.util.RunAllMethodsInGameContext;
import at.ahammer.heroquest.object.field.Door;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

/**
 * Created by andreas on 8/24/14.
 */
@RunWith(ArquillianGameContext.class)
public class MoveDefaultTest extends ArquillianGameContextTest implements RunAllMethodsInGameContext {

    @Inject
    @MoveStrategy(MoveDefault.class)
    private Move move;

    @Test
    public void testMove() {
        DummyBoard board = DummyBoard.getNewInstance();
        // no FieldConnectionObject
        Assert.assertTrue(move.canMove(board.getField(4, 0), board.getField(4, 1)));
        Assert.assertTrue(move.canMove(board.getField(4, 1), board.getField(4, 0)));
    }

    @Test
    public void testMoveThroughWall() {
        DummyBoard board = DummyBoard.getNewInstance();
        // a wall
        Assert.assertFalse(move.canMove(board.getField(4, 0), board.getField(3, 0)));
        Assert.assertFalse(move.canMove(board.getField(3, 0), board.getField(4, 0)));
    }

    @Test
    public void testMoveThroughDoor() {
        DummyBoard board = DummyBoard.getNewInstance();
        // a closed door
        Assert.assertFalse(move.canMove(board.getField(3, 1), board.getField(4, 1)));
        Assert.assertFalse(move.canMove(board.getField(4, 1), board.getField(3, 1)));
        // open the door
        board.getFieldConnectionObject(Door.class, "door:3,1-4,1").open();
        Assert.assertTrue(move.canMove(board.getField(3, 1), board.getField(4, 1)));
        Assert.assertTrue(move.canMove(board.getField(4, 1), board.getField(3, 1)));

    }
}
