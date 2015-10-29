package org.jabogaf.core.behavior.move;

import org.jabogaf.api.behavior.move.*;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.resource.NotEnoughResourceException;
import org.jabogaf.core.SimpleGridLayoutBoardWithSubject;
import org.jabogaf.core.resource.MovePoint;
import org.jabogaf.test.gamecontext.ArquillianGameContext;
import org.jabogaf.test.gamecontext.ArquillianGameContextTest;
import org.jabogaf.test.gamecontext.BeforeInGameContext;
import org.jabogaf.util.log.SLF4J;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(ArquillianGameContext.class)
public class MoveBehaviorBasicTest extends ArquillianGameContextTest {

    @Inject
    @SLF4J
    private Logger log;

    @Inject
    private MoveBehaviorDummy moveBehavior;

    private MovePath movePath;

    @Inject
    private SimpleGridLayoutBoardWithSubject game;

    @BeforeInGameContext
    public void before() {
        game.create(5, 5, 0, 0, 10);
        game.changeMoveBehaviorOfSubject(moveBehavior);
        movePath = new MovePathBasic(game.getField(2, 2), game.getField(2, 3), game.getField(3, 3), game.getField(3, 4));
    }

    @Test
    public void testCanMove() throws Exception {
        moveBehavior.setMoveBlockSet((m, t) -> false);
        CanMoveReport canMoveReport = moveBehavior.canMove(game.getGameSubject(), game.getField(0, 1), game.getGameSubject());
        assertTrue(canMoveReport.isPossible());
        assertEquals(1, canMoveReport.moveCost().getAmount());
        assertEquals(10, canMoveReport.maxPayment().getAmount());

        assertFalse(moveBehavior.canMove(null, game.getField(0, 1), game.getGameSubject()).isPossible());
        assertFalse(moveBehavior.canMove(game.getGameSubject(), game.getField(0, 1), null).isPossible());

        moveBehavior.setMoveBlockSet((m, t) -> true);
        assertFalse(moveBehavior.canMove(game.getGameSubject(), game.getField(0, 1), game.getGameSubject()).isPossible());

        game.getGameSubject().setResource(new MovePoint(0));
        assertFalse(moveBehavior.canMove(game.getGameSubject(), game.getField(0, 1), game.getGameSubject()).isPossible());
    }

    @Test
    public void testCanMovePath() throws Exception {
        game.setPositionOfSubject(game.getField(2, 2));
        moveBehavior.setMoveBlockSet((m, t) -> false);
        assertTrue(moveBehavior.canMove(game.getGameSubject(), movePath, game.getGameSubject()));

        assertFalse(moveBehavior.canMove(null, movePath, game.getGameSubject()));
        assertFalse(moveBehavior.canMove(game.getGameSubject(), movePath, null));

        moveBehavior.setMoveBlockSet((m, t) -> true);
        assertFalse(moveBehavior.canMove(game.getGameSubject(), movePath, game.getGameSubject()));

        game.getGameSubject().setResource(new MovePoint(2));
        assertFalse(moveBehavior.canMove(game.getGameSubject(), movePath, game.getGameSubject()));
    }

    @Test
    public void testMoveOk() throws Exception {
        assertEquals(10, game.getGameSubject().amountOf(MovePoint.class));
        moveBehavior.setMoveBlockSet((m, t) -> false);

        assertEquals(game.getField(0, 1), moveBehavior.move(game.getGameSubject(), game.getSetterOfPositionOfSubject(), game.getField(0, 1), game.getGameSubject()));
        assertEquals(game.getField(0, 1), game.getGameSubject().getPosition());
        assertEquals(9, game.getGameSubject().amountOf(MovePoint.class));

        game.getField(0, 0).setMovementCost(new MovePoint(3));
        assertEquals(game.getField(0, 0), moveBehavior.move(game.getGameSubject(), game.getSetterOfPositionOfSubject(), game.getField(0, 0), game.getGameSubject()));
        assertEquals(game.getField(0, 0), game.getGameSubject().getPosition());
        assertEquals(6, game.getGameSubject().amountOf(MovePoint.class));
    }

    @Test
    public void testMovePathOk() throws Exception {
        game.getSetterOfPositionOfSubject().setPosition(game.getField(2, 2));
        assertEquals(10, game.getGameSubject().amountOf(MovePoint.class));
        moveBehavior.setMoveBlockSet((m, t) -> false);
        moveBehavior.move(game.getGameSubject(), game.getSetterOfPositionOfSubject(), movePath, game.getGameSubject());
        assertEquals(game.getField(3, 4), game.getGameSubject().getPosition());
        assertEquals(7, game.getGameSubject().amountOf(MovePoint.class));
    }

    @Test
    public void testMoveBlock() throws FieldsNotConnectedException, NotEnoughResourceException {
        moveBehavior.setMoveBlockSet((m, t) -> false, new MoveBlock1(), new MoveBlock2());
        assertEquals(2, game.getGameSubject().canMove(game.getField(0, 1)).moveBlocks().size());

        try {
            game.getGameSubject().move(game.getField(0, 1));
            fail("exception " + MoveNotPossibleException.class.getSimpleName() + " should be thrown");
        } catch (MoveNotPossibleException e) {
            log.debug(e.getMessage());
            assertEquals(2, e.getMoveBlocks().size());
            for (MoveBlock moveBlock : e.getMoveBlocks()) {
                assertTrue(MoveBlock1.class == moveBlock.getClass() || MoveBlock2.class == moveBlock.getClass());
            }
        }
        assertEquals(game.getField(0, 0), game.getGameSubject().getPosition());
    }

    @Test(expected = FieldsNotConnectedException.class)
    public void testMoveNotConnected() throws Exception {
        game.getGameSubject().move(game.getField(1, 1));
    }

    @Test(expected = NotEnoughResourceException.class)
    public void testMoveNoMovePoints() throws NotEnoughResourceException, FieldsNotConnectedException, MoveNotPossibleException {
        moveBehavior.setMoveBlockSet((m, t) -> false);
        game.getGameSubject().setResource(new MovePoint(0));
        CanMoveReport canMoveReport = game.getGameSubject().canMove(game.getField(0, 1));
        assertFalse(canMoveReport.canPay());
        game.getGameSubject().move(game.getField(0, 1));
    }

    @Test(expected = NotEnoughResourceException.class)
    public void testMovePathNoMovePoints() throws NotEnoughResourceException, FieldsNotConnectedException, MoveNotPossibleException {
        game.getSetterOfPositionOfSubject().setPosition(game.getField(2, 2));
        game.getGameSubject().setResource(new MovePoint(0));
        moveBehavior.setMoveBlockSet((m, t) -> false);
        assertFalse(game.getGameSubject().canMove(movePath));
        game.getGameSubject().move(movePath);
    }

    @Test
    public void testMoveUnableToEnd() {
        moveBehavior.setMoveUnableToEndSet((m, t) -> false, new MoveUnableToEnd1(), new MoveUnableToEnd2());
        assertEquals(2, moveBehavior.checkMoveUnableToEnd(game.getGameSubject(), game.getField(0, 1)).size());
        assertFalse(game.getGameSubject().isMoveableTarget(game.getField(0, 1)));
        try {
            game.getGameSubject().move(game.getField(0, 1));
            fail("exception " + MoveNotPossibleException.class.getSimpleName() + " should be thrown");
        } catch (MoveNotPossibleException e) {
            log.debug(e.getMessage());
            assertEquals(2, e.getMoveUnableToEnd().size());
            for (MoveUnableToEnd moveUnableToEnd : e.getMoveUnableToEnd()) {
                assertTrue(MoveUnableToEnd1.class == moveUnableToEnd.getClass() || MoveUnableToEnd2.class == moveUnableToEnd.getClass());
            }
        }
        assertEquals(game.getField(0, 0), game.getGameSubject().getPosition());
    }

    @ApplicationScoped
    public static class MoveBehaviorDummy extends MoveBehaviorBasic {

        private Set<MoveBlock> moveBlockSet = new HashSet<>();

        private Set<MoveUnableToEnd> moveUnableToEndSet = new HashSet<>();

        @Override
        protected Set<MoveUnableToEnd> fillMoveUnableToEnds() {
            return new HashSet<>();
        }

        @Override
        protected Set<MoveBlock> fillMoveBlocks() {
            return new HashSet<>();
        }

        @Override
        public Set<MoveBlock> getMoveBlocks() {
            return moveBlockSet;
        }

        @Override
        public Set<MoveUnableToEnd> getMoveUnableToEnd() {
            return moveUnableToEndSet;
        }

        public void setMoveBlockSet(MoveBlock... moveBlockCollection) {
            this.moveBlockSet.clear();
            Collections.addAll(this.moveBlockSet, moveBlockCollection);
        }

        public void setMoveUnableToEndSet(MoveUnableToEnd... moveUnableToEndSet) {
            this.moveUnableToEndSet.clear();
            Collections.addAll(this.moveUnableToEndSet, moveUnableToEndSet);
        }
    }

    private static class MoveBlock1 implements MoveBlock {

        @Override
        public boolean blocks(Moveable moveable, Field target) {
            return true;
        }
    }

    private static class MoveBlock2 implements MoveBlock {

        @Override
        public boolean blocks(Moveable moveable, Field target) {
            return true;
        }
    }

    private static class MoveUnableToEnd1 implements MoveUnableToEnd {

        @Override
        public boolean unableToEnd(Moveable moveable, Field target) {
            return true;
        }
    }

    private static class MoveUnableToEnd2 implements MoveUnableToEnd {

        @Override
        public boolean unableToEnd(Moveable moveable, Field target) {
            return true;
        }
    }
}