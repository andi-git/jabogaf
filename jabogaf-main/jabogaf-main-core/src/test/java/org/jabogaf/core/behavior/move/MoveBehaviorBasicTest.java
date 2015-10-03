package org.jabogaf.core.behavior.move;

import org.jabogaf.api.behavior.move.*;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.resource.NotEnoughResourceException;
import org.jabogaf.api.resource.Resource;
import org.jabogaf.api.subject.SetterOfPosition;
import org.jabogaf.core.board.field.FieldBasic;
import org.jabogaf.core.resource.MovePoint;
import org.jabogaf.core.subject.GameSubjectBasic;
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
        moveBehavior.setMoveBlockSet((m, t) -> false);
        CanMoveReport canMoveReport = moveBehavior.canMove(gameSubject, field2, gameSubject);
        assertTrue(canMoveReport.isPossible());
        assertEquals(1, canMoveReport.moveCost().getAmount());
        assertEquals(10, canMoveReport.maxPayment().getAmount());

        assertFalse(moveBehavior.canMove(null, field2, gameSubject).isPossible());
        assertFalse(moveBehavior.canMove(gameSubject, field2, null).isPossible());

        moveBehavior.setMoveBlockSet((m, t) -> true);
        assertFalse(moveBehavior.canMove(gameSubject, field2, gameSubject).isPossible());

        gameSubject.setResource(new MovePoint(0));
        assertFalse(moveBehavior.canMove(gameSubject, field2, gameSubject).isPossible());
    }

    @Test
    public void testCanMovePath() throws Exception {
        gameSubject.getSetterOfPosition().setPosition(field5);
        moveBehavior.setMoveBlockSet((m, t) -> false);
        assertTrue(moveBehavior.canMove(gameSubject, movePath, gameSubject));

        assertFalse(moveBehavior.canMove(null, movePath, gameSubject));
        assertFalse(moveBehavior.canMove(gameSubject, movePath, null));

        moveBehavior.setMoveBlockSet((m, t) -> true);
        assertFalse(moveBehavior.canMove(gameSubject, movePath, gameSubject));

        gameSubject.setResource(new MovePoint(2));
        assertFalse(moveBehavior.canMove(gameSubject, movePath, gameSubject));
    }

    @Test
    public void testMoveOk() throws Exception {
        assertEquals(10, gameSubject.amountOf(MovePoint.class));
        moveBehavior.setMoveBlockSet((m, t) -> false);
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
        moveBehavior.setMoveBlockSet((m, t) -> false);
        moveBehavior.move(gameSubject, gameSubject.getSetterOfPosition(), movePath, gameSubject);
        assertEquals(field8, gameSubject.getPosition());
        assertEquals(7, gameSubject.amountOf(MovePoint.class));
    }

    @Test
    public void testMoveBlock() throws FieldsNotConnectedException, NotEnoughResourceException {
        moveBehavior.setMoveBlockSet((m, t) -> false, new MoveBlock1(), new MoveBlock2());
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
        moveBehavior.setMoveBlockSet((m, t) -> false);
        gameSubject.setResource(new MovePoint(0));
        CanMoveReport canMoveReport = moveBehavior.canMove(gameSubject, field2, gameSubject);
        assertFalse(canMoveReport.canPay());
        moveBehavior.move(gameSubject, gameSubject.getSetterOfPosition(), field2, gameSubject);
    }

    @Test(expected = NotEnoughResourceException.class)
    public void testMovePathNoMovePoints() throws NotEnoughResourceException, FieldsNotConnectedException, MoveNotPossibleException {
        gameSubject.getSetterOfPosition().setPosition(field5);
        gameSubject.setResource(new MovePoint(0));
        moveBehavior.setMoveBlockSet((m, t) -> false);
        moveBehavior.move(gameSubject, gameSubject.getSetterOfPosition(), movePath, gameSubject);
    }

    @Test
    public void testMoveUnableToEnd() {
        moveBehavior.setMoveUnableToEndSet((m, t) -> false, new MoveUnableToEnd1(), new MoveUnableToEnd2());
        assertEquals(2, moveBehavior.checkMoveUnableToEnd(gameSubject, field2).size());
        assertFalse(gameSubject.isMoveableTarget(field2));
        try {
            moveBehavior.move(gameSubject, gameSubject.getSetterOfPosition(), field2, gameSubject);
            fail("exception " + MoveNotPossibleException.class.getSimpleName() + " should be thrown");
        } catch (MoveNotPossibleException e) {
            log.info(e.getMessage());
            assertEquals(2, e.getMoveUnableToEnd().size());
            for (MoveUnableToEnd moveUnableToEnd : e.getMoveUnableToEnd()) {
                assertTrue(MoveUnableToEnd1.class == moveUnableToEnd.getClass() || MoveUnableToEnd2.class == moveUnableToEnd.getClass());
            }
        }
        assertEquals(field1, gameSubject.getPosition());
    }

    @ApplicationScoped
    public static class MoveBehaviorDummy extends MoveBehaviorBasic {

        private Set<MoveBlock> moveBlockSet = new HashSet<>();

        private Set<MoveUnableToEnd> moveUnableToEndSet = new HashSet<>();

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