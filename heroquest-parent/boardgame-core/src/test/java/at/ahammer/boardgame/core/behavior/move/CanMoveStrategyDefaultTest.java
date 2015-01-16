package at.ahammer.boardgame.core.behavior.move;

import at.ahammer.boardgame.api.behavior.move.CanMoveStrategy;
import at.ahammer.boardgame.api.behavior.move.MoveBlock;
import at.ahammer.boardgame.api.behavior.move.Moveable;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.core.board.AbstractBoardTest;
import at.ahammer.boardgame.core.board.field.FieldNull;
import at.ahammer.boardgame.core.subject.GameSubjectNull;
import at.ahammer.boardgame.core.test.ArquillianGameContext;
import at.ahammer.boardgame.core.test.ArquillianGameContextTest;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(ArquillianGameContext.class)
public class CanMoveStrategyDefaultTest extends ArquillianGameContextTest {

    @Inject
    private CanMoveStrategy canMoveStrategy;

    @Test
    public void testCanMove() throws Exception {
        Moveable moveable = new GameSubjectNull();
        Field target = new FieldNull();
        Set<MoveBlock> moveBlocks = new HashSet<>();
        moveBlocks.add((m, t) -> false);
        assertTrue(canMoveStrategy.canMove(moveable, target, moveBlocks));

        assertFalse(canMoveStrategy.canMove(null, target, moveBlocks));
        assertFalse(canMoveStrategy.canMove(moveable, null, moveBlocks));
        assertTrue(canMoveStrategy.canMove(moveable, target, null));

        moveBlocks.add((m, t) -> true);
        assertFalse(canMoveStrategy.canMove(moveable, target, moveBlocks));
    }
}