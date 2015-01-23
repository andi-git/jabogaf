package at.ahammer.boardgame.core.behavior.move;

import at.ahammer.boardgame.api.behavior.move.FieldsNotConnectedException;
import at.ahammer.boardgame.api.behavior.move.MoveBlock;
import at.ahammer.boardgame.api.behavior.move.MoveNotPossibleException;
import at.ahammer.boardgame.api.behavior.move.Moveable;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.field.FieldConnection;
import at.ahammer.boardgame.api.board.field.FieldConnectionObject;
import at.ahammer.boardgame.api.board.layout.Layout;
import at.ahammer.boardgame.api.resource.NotEnoughResourceException;
import at.ahammer.boardgame.api.resource.Resource;
import at.ahammer.boardgame.api.resource.ResourceHolder;
import at.ahammer.boardgame.api.subject.GameSubject;
import at.ahammer.boardgame.api.subject.SetterOfPosition;
import at.ahammer.boardgame.core.board.field.FieldBasic;
import at.ahammer.boardgame.core.board.field.FieldConnectionBasic;
import at.ahammer.boardgame.core.board.field.FieldConnectionObjectBasic;
import at.ahammer.boardgame.core.resource.MovePoint;
import at.ahammer.boardgame.core.subject.GameSubjectBasic;
import at.ahammer.boardgame.core.subject.GameSubjectNull;
import at.ahammer.boardgame.core.test.ArquillianGameContext;
import at.ahammer.boardgame.core.test.ArquillianGameContextTest;
import at.ahammer.boardgame.core.test.BeforeInGameContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(ArquillianGameContext.class)
public class MoveBehaviorBasicTest extends ArquillianGameContextTest {

    @Inject
    private Logger log;

    @Inject
    private MoveBehaviorDummy moveBehavior;

    private Field field1, field2, field3;

    private MyGameSubject gameSubject;

    @BeforeInGameContext
    public void before() {
        field1 = new Field1();
        field2 = new Field2();
        field3 = new Field3();
        gameSubject = new MyGameSubject("myMoveable", field1);
        gameSubject.earn(new MovePoint(10).asPayment());
    }

    @Test
    public void testCanMove() throws Exception {
        moveBehavior.setMoveBlocks((m, t) -> false);
        assertTrue(moveBehavior.canMove(gameSubject, field2, gameSubject));

        assertFalse(moveBehavior.canMove(null, field2, gameSubject));
        assertFalse(moveBehavior.canMove(gameSubject, null, gameSubject));
        assertFalse(moveBehavior.canMove(gameSubject, field2, null));

        moveBehavior.setMoveBlocks((m, t) -> true);
        assertFalse(moveBehavior.canMove(gameSubject, field2, gameSubject));

        gameSubject.pay(new MovePoint(gameSubject.amountOf(MovePoint.class)).asPayment());
        assertFalse(moveBehavior.canMove(gameSubject, field2, gameSubject));
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
    public void testMoveBlock() throws FieldsNotConnectedException, NotEnoughResourceException {
        moveBehavior.setMoveBlocks((m, t) -> false, new MoveBlock1(), new MoveBlock2());
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
        gameSubject.pay(new MovePoint(gameSubject.amountOf(MovePoint.class)).asPayment());
        assertEquals(field2, moveBehavior.move(gameSubject, gameSubject.getSetterOfPosition(), field2, gameSubject));
    }

    @ApplicationScoped
    public static class MoveBehaviorDummy extends MoveBehaviorBasic {

        private Set<MoveBlock> moveBlocks = new HashSet<>();

        @Override
        public Set<Field> getMovableFields(Moveable moveable, ResourceHolder resourceHolder) {
            return null;
        }

        @Override
        public boolean canBeUsedOn(Layout layout) {
            return false;
        }

        @Override
        public Set<MoveBlock> getMoveBlocks() {
            return moveBlocks;
        }

        public void setMoveBlocks(MoveBlock... moveBlocks) {
            this.moveBlocks.clear();
            for (MoveBlock moveBlock : moveBlocks) {
                this.moveBlocks.add(moveBlock);
            }
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

        public MyGameSubject(String id, Field position) {
            super(id, position);
        }

        @Override
        public SetterOfPosition getSetterOfPosition() {
            return super.getSetterOfPosition();
        }
    }
}