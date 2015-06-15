package at.ahammer.boardgame.core.behavior.move;

import at.ahammer.boardgame.api.behavior.move.*;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.resource.NotEnoughResourceException;
import at.ahammer.boardgame.api.resource.Resource;
import at.ahammer.boardgame.api.subject.SetterOfPosition;
import at.ahammer.boardgame.core.board.field.FieldBasic;
import at.ahammer.boardgame.core.resource.MovePoint;
import at.ahammer.boardgame.core.subject.GameSubjectBasic;
import at.ahammer.boardgame.core.test.ArquillianGameContext;
import at.ahammer.boardgame.core.test.ArquillianGameContextTest;
import at.ahammer.boardgame.core.test.BeforeInGameContext;
import at.ahammer.boardgame.util.log.SLF4J;
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

    private Field field1, field2, field3, field5, field8;

    private MyGameSubject gameSubject;

    private MovePath movePath;

    @BeforeInGameContext
    public void before() {
        field1 = new Field1();
        field2 = new Field2();
        field3 = new Field3();
        gameSubject = new MyGameSubject("myMoveable", field1, moveBehavior);
        gameSubject.earn(new MovePoint(10).asPayment());
        field5 = new Field5();
        field8 = new Field8();
        movePath = new MovePathBasic(field5, new Field6(), new Field7(), field8);
    }

    @Test
    public void testCanMove() throws Exception {
        moveBehavior.setMoveBlocks((m, t) -> false);
        CanMoveReport canMoveReport = moveBehavior.canMove(gameSubject, field2, gameSubject);
        assertTrue(canMoveReport.isPossible());
        assertEquals(1, canMoveReport.moveCost().getAmount());
        assertEquals(10, canMoveReport.maxPayment().getAmount());

        assertFalse(moveBehavior.canMove(null, field2, gameSubject).isPossible());
        assertFalse(moveBehavior.canMove(gameSubject, field2, null).isPossible());

        moveBehavior.setMoveBlocks((m, t) -> true);
        assertFalse(moveBehavior.canMove(gameSubject, field2, gameSubject).isPossible());

        gameSubject.setResource(new MovePoint(0));
        assertFalse(moveBehavior.canMove(gameSubject, field2, gameSubject).isPossible());
    }

    @Test
    public void testCanMovePath() throws Exception {
        gameSubject.getSetterOfPosition().setPosition(field5);
        moveBehavior.setMoveBlocks((m, t) -> false);
        assertTrue(moveBehavior.canMove(gameSubject, movePath, gameSubject));

        assertFalse(moveBehavior.canMove(null, movePath, gameSubject));
        assertFalse(moveBehavior.canMove(gameSubject, movePath, null));

        moveBehavior.setMoveBlocks((m, t) -> true);
        assertFalse(moveBehavior.canMove(gameSubject, movePath, gameSubject));

        gameSubject.setResource(new MovePoint(2));
        assertFalse(moveBehavior.canMove(gameSubject, movePath, gameSubject));
    }

    @Test
    public void testMoveOk() throws Exception {
        assertEquals(10, gameSubject.amountOf(MovePoint.class));
        moveBehavior.setMoveBlocks((m, t) -> false);
        assertEquals(field2, moveBehavior.move(gameSubject, gameSubject.getSetterOfPosition(), field2, gameSubject));
        assertEquals(field2, gameSubject.getPosition());
        assertEquals(9, gameSubject.amountOf(MovePoint.class));
        assertEquals(field1, moveBehavior.move(gameSubject, gameSubject.getSetterOfPosition(), field1, gameSubject));
        assertEquals(field1, gameSubject.getPosition());
        assertEquals(6, gameSubject.amountOf(MovePoint.class));
    }

    @Test
    public void testMovePathOk() throws Exception {
        gameSubject.getSetterOfPosition().setPosition(field5);
        assertEquals(10, gameSubject.amountOf(MovePoint.class));
        moveBehavior.setMoveBlocks((m, t) -> false);
        moveBehavior.move(gameSubject, gameSubject.getSetterOfPosition(), movePath, gameSubject);
        assertEquals(field8, gameSubject.getPosition());
        assertEquals(7, gameSubject.amountOf(MovePoint.class));
    }

    @Test
    public void testMoveBlock() throws FieldsNotConnectedException, NotEnoughResourceException {
        moveBehavior.setMoveBlocks((m, t) -> false, new MoveBlock1(), new MoveBlock2());
        assertEquals(2, moveBehavior.canMove(gameSubject, field2, gameSubject).moveBlocks().size());
        try {
            moveBehavior.move(gameSubject, gameSubject.getSetterOfPosition(), field2, gameSubject);
            fail("exception " + MoveNotPossibleException.class.getSimpleName() + " should be thrown");
        } catch (MoveNotPossibleException e) {
            log.info(e.getMessage());
            assertEquals(2, e.getMoveBlocks().size());
            for (MoveBlock moveBlock : e.getMoveBlocks()) {
                assertTrue(MoveBlock1.class == moveBlock.getClass() || MoveBlock2.class == moveBlock.getClass());
            }
        }
        assertEquals(field1, gameSubject.getPosition());
    }

    @Test(expected = FieldsNotConnectedException.class)
    public void testMoveNotConnected() throws Exception {
        moveBehavior.move(gameSubject, gameSubject.getSetterOfPosition(), field3, gameSubject);
    }

    @Test(expected = NotEnoughResourceException.class)
    public void testMoveNoMovePoints() throws NotEnoughResourceException, FieldsNotConnectedException, MoveNotPossibleException {
        moveBehavior.setMoveBlocks((m, t) -> false);
        gameSubject.setResource(new MovePoint(0));
        CanMoveReport canMoveReport = moveBehavior.canMove(gameSubject, field2, gameSubject);
        assertFalse(canMoveReport.canPay());
        moveBehavior.move(gameSubject, gameSubject.getSetterOfPosition(), field2, gameSubject);
    }

    @Test(expected = NotEnoughResourceException.class)
    public void testMovePathNoMovePoints() throws NotEnoughResourceException, FieldsNotConnectedException, MoveNotPossibleException {
        gameSubject.getSetterOfPosition().setPosition(field5);
        gameSubject.setResource(new MovePoint(0));
        moveBehavior.setMoveBlocks((m, t) -> false);
        moveBehavior.move(gameSubject, gameSubject.getSetterOfPosition(), movePath, gameSubject);
    }

    @ApplicationScoped
    public static class MoveBehaviorDummy extends MoveBehaviorBasic {

        private Set<MoveBlock> moveBlocks = new HashSet<>();

        @Override
        public Set<MoveBlock> getMoveBlocks() {
            return moveBlocks;
        }

        public void setMoveBlocks(MoveBlock... moveBlocks) {
            this.moveBlocks.clear();
            Collections.addAll(this.moveBlocks, moveBlocks);
        }
    }

    private static class Field1 extends FieldBasic {

        public Field1() {
            super("field1");
        }

        @Override
        public boolean isConnected(Field target) {
            return "field2".equals(target.getId());
        }

        @Override
        public Resource movementCost() {
            return new MovePoint(3);
        }
    }

    private static class Field2 extends FieldBasic {

        public Field2() {
            super("field2");
        }

        @Override
        public boolean isConnected(Field target) {
            return "field1".equals(target.getId());
        }
    }

    private static class Field3 extends FieldBasic {

        public Field3() {
            super("field3");
        }

        @Override
        public boolean isConnected(Field target) {
            return false;
        }
    }

    private static class Field5 extends FieldBasic {

        public Field5() {
            super("field5");
        }

        @Override
        public boolean isConnected(Field target) {
            return "field6".equals(target.getId());
        }
    }

    private static class Field6 extends FieldBasic {

        public Field6() {
            super("field6");
        }

        @Override
        public boolean isConnected(Field target) {
            return "field5".equals(target.getId()) || "field7".equals(target.getId());
        }
    }

    private static class Field7 extends FieldBasic {

        public Field7() {
            super("field7");
        }

        @Override
        public boolean isConnected(Field target) {
            return "field6".equals(target.getId()) || "field8".equals(target.getId());
        }
    }

    private static class Field8 extends FieldBasic {

        public Field8() {
            super("field8");
        }

        @Override
        public boolean isConnected(Field target) {
            return "field7".equals(target.getId());
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

    private class MyGameSubject extends GameSubjectBasic {

        public MyGameSubject(String id, Field position, MoveBehavior moveBehavior) {
            super(id, position, moveBehavior, null);
        }

        @Override
        public SetterOfPosition getSetterOfPosition() {
            return super.getSetterOfPosition();
        }
    }
}