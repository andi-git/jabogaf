package at.ahammer.boardgame.core.behavior.move;

import at.ahammer.boardgame.api.behavior.move.*;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.field.FieldConnection;
import at.ahammer.boardgame.api.subject.SetterOfPosition;
import at.ahammer.boardgame.core.board.AbstractBoardTest;
import at.ahammer.boardgame.core.board.field.FieldBasic;
import at.ahammer.boardgame.core.board.field.FieldConnectionBasic;
import at.ahammer.boardgame.core.board.field.FieldNull;
import at.ahammer.boardgame.core.subject.GameSubjectNull;
import at.ahammer.boardgame.core.test.ArquillianGameContext;
import at.ahammer.boardgame.core.test.ArquillianGameContextTest;
import at.ahammer.boardgame.core.test.BeforeInGameContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import javax.inject.Inject;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(ArquillianGameContext.class)
public class MoveStrategyDefaultTest extends ArquillianGameContextTest {

    @Inject
    private MoveStrategy moveStrategy;

    @Inject
    private Logger log;

    private Field position;

    private Field field1, field2, field3;

    private FieldConnection fieldConnection12;

    private Moveable moveable;

    private Set<MoveBlock> moveBlocks = new HashSet<>();

    @BeforeInGameContext
    public void before() {
        field1 = new Field1();
        field2 = new Field2();
        field3 = new Field3();
        fieldConnection12 = new FieldConnectionBasic("fieldConnection12", field1, field2);
        moveable = new GameSubjectNull("myMoveable", field1);
        position = null;
    }

    @Test
    public void testMoveOk() throws Exception {
        moveBlocks.add((m, t) -> false);
        assertEquals(field2, moveStrategy.move(moveable, (field) -> position = field, field2, moveBlocks));
        assertEquals(field2, position);
    }

    @Test
    public void testMoveBlock() throws FieldsNotConnectedException {
        moveBlocks.add((m, t) -> false);
        moveBlocks.add(new MoveBlock1());
        moveBlocks.add(new MoveBlock2());
        try {
            moveStrategy.move(moveable, (field) -> position = field, field2, moveBlocks);
            fail("exception " + MoveNotPossibleException.class.getSimpleName() + " should be thrown");
        } catch (MoveNotPossibleException e) {
            log.info(e.getMessage());
            assertEquals(2, e.getMoveBlocks().size());
            for (MoveBlock moveBlock : e.getMoveBlocks()) {
                assertTrue(MoveBlock1.class == moveBlock.getClass() || MoveBlock2.class == moveBlock.getClass());
            }
        }
        assertNull(position);
    }

    @Test(expected = FieldsNotConnectedException.class)
    public void testMoveNotConnected() throws Exception {
        moveStrategy.move(moveable, (field) -> position = field, field3, moveBlocks);
    }

    private static class Field1 extends FieldBasic {

        public Field1() {
            super("field1");
        }

        @Override
        public boolean isConnected(Field target) {
            return "field2".equals(target.getId());
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
}