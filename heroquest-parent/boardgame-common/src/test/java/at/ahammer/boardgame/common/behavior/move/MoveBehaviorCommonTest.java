package at.ahammer.boardgame.common.behavior.move;

import at.ahammer.boardgame.api.behavior.move.*;
import at.ahammer.boardgame.common.TestWithExampleGridLayoutBoard;
import at.ahammer.boardgame.common.object.field.Door;
import at.ahammer.boardgame.core.test.ArquillianGameContext;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.*;

@RunWith(ArquillianGameContext.class)
public class MoveBehaviorCommonTest extends TestWithExampleGridLayoutBoard {

    @Inject
    @MoveBehaviorType(MoveBehaviorCommon.class)
    private MoveBehavior moveBehavior;

    @Test
    public void testGetMoveBlocks() throws Exception {
        assertEquals(2, moveBehavior.getMoveBlocks().size());
        for (MoveBlock moveBlock : moveBehavior.getMoveBlocks()) {
            assertTrue(moveBlock instanceof MoveBlockDoor ||
                    moveBlock instanceof MoveBlockWall);
        }
    }

    @Test
    public void testMoveOk() throws FieldsNotConnectedException, MoveNotPossibleException {
        assertEquals(getField(0, 1), moveBehavior.move(getPlayer1(), getPlayer1().getSetterOfPosition(), getField(0, 1)));
        assertEquals(getField(0, 1), getPlayer1().getPosition());
    }

    @Test
    public void testMoveBlock() throws FieldsNotConnectedException, MoveNotPossibleException {
        try {
            moveBehavior.move(getPlayer1(), getPlayer1().getSetterOfPosition(), getField(2, 1));
            fail("should throw a " + MoveNotPossibleException.class);
        } catch (MoveNotPossibleException e) {
            assertEquals(1, e.getMoveBlocks().size());
            assertEquals(1, e.getMoveBlocks().stream().filter(mb -> mb instanceof MoveBlockDoor).count());
        }
        Door door = (Door) getField(1, 1).getConnectionTo(getField(2, 1)).getObjectsOnConnection().stream().findFirst().get();
        door.open();
        assertEquals(getField(2, 1), moveBehavior.move(getPlayer1(), getPlayer1().getSetterOfPosition(), getField(2, 1)));
        assertEquals(getField(2, 1), getPlayer1().getPosition());
    }
}