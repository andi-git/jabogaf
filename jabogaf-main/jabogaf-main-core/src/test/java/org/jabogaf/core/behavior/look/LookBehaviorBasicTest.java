package org.jabogaf.core.behavior.look;

import org.jabogaf.api.behavior.look.CanLookReport;
import org.jabogaf.api.behavior.look.LookBlock;
import org.jabogaf.api.behavior.look.Lookable;
import org.jabogaf.api.behavior.move.*;
import org.jabogaf.api.board.field.ContainsGameObjects;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.resource.NotEnoughResourceException;
import org.jabogaf.api.resource.Resource;
import org.jabogaf.core.SimpleGridLayoutBoardWithSubject;
import org.jabogaf.core.behavior.move.MoveBehaviorBasic;
import org.jabogaf.core.behavior.move.MovePathBasic;
import org.jabogaf.core.object.GameObjectBasic;
import org.jabogaf.core.resource.LookPoint;
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
public class LookBehaviorBasicTest extends ArquillianGameContextTest {

    @Inject
    @SLF4J
    private Logger log;

    @Inject
    private LookBehaviorDummy lookBehavior;

    @Inject
    private SimpleGridLayoutBoardWithSubject game;

    @BeforeInGameContext
    public void before() {
        game.create(5, 5, 0, 0, 10);
        game.changeLookBehaviorOfSubject(lookBehavior);
    }

    @Test
    public void testCanLook() throws Exception {
        lookBehavior.setLookBlockSet((m, t) -> false);
        CanLookReport canLookReport = lookBehavior.canLook(game.getGameSubject(), game.getField(0, 1), game.getGameSubject());
        assertTrue(canLookReport.isPossible());
        assertEquals(0, canLookReport.cost().getAmount());
        assertEquals(10, canLookReport.maxPayment().getAmount());

        assertFalse(lookBehavior.canLook(null, game.getField(0, 1), game.getGameSubject()).isPossible());
        assertFalse(lookBehavior.canLook(game.getGameSubject(), game.getField(0, 1), null).isPossible());

        lookBehavior.setLookBlockSet((m, t) -> true);
        assertFalse(lookBehavior.canLook(game.getGameSubject(), game.getField(0, 1), game.getGameSubject()).isPossible());

        lookBehavior.setLookBlockSet((m, t) -> false);
        game.getFieldConnection(0, 0, 0, 1).addObjectOnConnection(new GameObjectBasic<ContainsGameObjects>("myGameObject") {
            @Override
            public Resource lookCost() {
                return new LookPoint(1);
            }
        });
        game.getGameSubject().setResource(new LookPoint(0));
        // FIXME
//        assertFalse(lookBehavior.canLook(game.getGameSubject(), game.getField(0, 1), game.getGameSubject()).isPossible());
    }

//    @Test
//    public void testCanMove() throws Exception {
//        moveBehavior.setMoveBlockSet((m, t) -> false);
//        CanMoveReport canMoveReport = moveBehavior.canMove(game.getGameSubject(), game.getField(0, 1), game.getGameSubject());
//        assertTrue(canMoveReport.isPossible());
//        assertEquals(1, canMoveReport.moveCost().getAmount());
//        assertEquals(10, canMoveReport.maxPayment().getAmount());
//
//        assertFalse(moveBehavior.canMove(null, game.getField(0, 1), game.getGameSubject()).isPossible());
//        assertFalse(moveBehavior.canMove(game.getGameSubject(), game.getField(0, 1), null).isPossible());
//
//        moveBehavior.setMoveBlockSet((m, t) -> true);
//        assertFalse(moveBehavior.canMove(game.getGameSubject(), game.getField(0, 1), game.getGameSubject()).isPossible());
//
//        game.getGameSubject().setResource(new MovePoint(0));
//        assertFalse(moveBehavior.canMove(game.getGameSubject(), game.getField(0, 1), game.getGameSubject()).isPossible());
//    }
//
//    @Test
//    public void testCanMovePath() throws Exception {
//        game.setPositionOfSubject(game.getField(2, 2));
//        moveBehavior.setMoveBlockSet((m, t) -> false);
//        assertTrue(moveBehavior.canMove(game.getGameSubject(), movePath, game.getGameSubject()));
//
//        assertFalse(moveBehavior.canMove(null, movePath, game.getGameSubject()));
//        assertFalse(moveBehavior.canMove(game.getGameSubject(), movePath, null));
//
//        moveBehavior.setMoveBlockSet((m, t) -> true);
//        assertFalse(moveBehavior.canMove(game.getGameSubject(), movePath, game.getGameSubject()));
//
//        game.getGameSubject().setResource(new MovePoint(2));
//        assertFalse(moveBehavior.canMove(game.getGameSubject(), movePath, game.getGameSubject()));
//    }
//
//    @Test
//    public void testMoveOk() throws Exception {
//        assertEquals(10, game.getGameSubject().amountOf(MovePoint.class));
//        moveBehavior.setMoveBlockSet((m, t) -> false);
//
//        assertEquals(game.getField(0, 1), moveBehavior.move(game.getGameSubject(), game.getSetterOfPositionOfSubject(), game.getField(0, 1), game.getGameSubject()));
//        assertEquals(game.getField(0, 1), game.getGameSubject().getPosition());
//        assertEquals(9, game.getGameSubject().amountOf(MovePoint.class));
//
//        game.getField(0, 0).setMovementCost(new MovePoint(3));
//        assertEquals(game.getField(0, 0), moveBehavior.move(game.getGameSubject(), game.getSetterOfPositionOfSubject(), game.getField(0, 0), game.getGameSubject()));
//        assertEquals(game.getField(0, 0), game.getGameSubject().getPosition());
//        assertEquals(6, game.getGameSubject().amountOf(MovePoint.class));
//    }
//
//    @Test
//    public void testMovePathOk() throws Exception {
//        game.getSetterOfPositionOfSubject().setPosition(game.getField(2, 2));
//        assertEquals(10, game.getGameSubject().amountOf(MovePoint.class));
//        moveBehavior.setMoveBlockSet((m, t) -> false);
//        moveBehavior.move(game.getGameSubject(), game.getSetterOfPositionOfSubject(), movePath, game.getGameSubject());
//        assertEquals(game.getField(3, 4), game.getGameSubject().getPosition());
//        assertEquals(7, game.getGameSubject().amountOf(MovePoint.class));
//    }
//
//    @Test
//    public void testMoveBlock() throws FieldsNotConnectedException, NotEnoughResourceException {
//        moveBehavior.setMoveBlockSet((m, t) -> false, new MoveBlock1(), new MoveBlock2());
//        assertEquals(2, game.getGameSubject().canMove(game.getField(0, 1)).moveBlocks().size());
//
//        try {
//            game.getGameSubject().move(game.getField(0, 1));
//            fail("exception " + MoveNotPossibleException.class.getSimpleName() + " should be thrown");
//        } catch (MoveNotPossibleException e) {
//            log.debug(e.getMessage());
//            assertEquals(2, e.getMoveBlocks().size());
//            for (MoveBlock moveBlock : e.getMoveBlocks()) {
//                assertTrue(MoveBlock1.class == moveBlock.getClass() || MoveBlock2.class == moveBlock.getClass());
//            }
//        }
//        assertEquals(game.getField(0, 0), game.getGameSubject().getPosition());
//    }
//
//    @Test(expected = FieldsNotConnectedException.class)
//    public void testMoveNotConnected() throws Exception {
//        game.getGameSubject().move(game.getField(1, 1));
//    }
//
//    @Test(expected = NotEnoughResourceException.class)
//    public void testMoveNoMovePoints() throws NotEnoughResourceException, FieldsNotConnectedException, MoveNotPossibleException {
//        moveBehavior.setMoveBlockSet((m, t) -> false);
//        game.getGameSubject().setResource(new MovePoint(0));
//        CanMoveReport canMoveReport = game.getGameSubject().canMove(game.getField(0, 1));
//        assertFalse(canMoveReport.canPay());
//        game.getGameSubject().move(game.getField(0, 1));
//    }
//
//    @Test(expected = NotEnoughResourceException.class)
//    public void testMovePathNoMovePoints() throws NotEnoughResourceException, FieldsNotConnectedException, MoveNotPossibleException {
//        game.getSetterOfPositionOfSubject().setPosition(game.getField(2, 2));
//        game.getGameSubject().setResource(new MovePoint(0));
//        moveBehavior.setMoveBlockSet((m, t) -> false);
//        assertFalse(game.getGameSubject().canMove(movePath));
//        game.getGameSubject().move(movePath);
//    }
//
//    @Test
//    public void testMoveUnableToEnd() {
//        moveBehavior.setMoveUnableToEndSet((m, t) -> false, new MoveUnableToEnd1(), new MoveUnableToEnd2());
//        assertEquals(2, moveBehavior.checkMoveUnableToEnd(game.getGameSubject(), game.getField(0, 1)).size());
//        assertFalse(game.getGameSubject().isMoveableTarget(game.getField(0, 1)));
//        try {
//            game.getGameSubject().move(game.getField(0, 1));
//            fail("exception " + MoveNotPossibleException.class.getSimpleName() + " should be thrown");
//        } catch (MoveNotPossibleException e) {
//            log.debug(e.getMessage());
//            assertEquals(2, e.getMoveUnableToEnd().size());
//            for (MoveUnableToEnd moveUnableToEnd : e.getMoveUnableToEnd()) {
//                assertTrue(MoveUnableToEnd1.class == moveUnableToEnd.getClass() || MoveUnableToEnd2.class == moveUnableToEnd.getClass());
//            }
//        }
//        assertEquals(game.getField(0, 0), game.getGameSubject().getPosition());
//    }

    @ApplicationScoped
    public static class LookBehaviorDummy extends LookBehaviorBasic {

        private Set<LookBlock> lookBlockSet = new HashSet<>();

        @Override
        protected Set<LookBlock> fillLookBlocks() {
            return new HashSet<>();
        }

        @Override
        public Set<LookBlock> getLookBlocks() {
            return lookBlockSet;
        }

        public void setLookBlockSet(LookBlock... lookBlockCollection) {
            this.lookBlockSet.clear();
            Collections.addAll(this.lookBlockSet, lookBlockCollection);
        }
    }

    private static class LookBlock1 implements LookBlock {

        @Override
        public boolean blocks(Lookable lookable, Field target) {
            return true;
        }
    }

    private static class LookBlock2 implements LookBlock {

        @Override
        public boolean blocks(Lookable moveable, Field target) {
            return true;
        }
    }
}