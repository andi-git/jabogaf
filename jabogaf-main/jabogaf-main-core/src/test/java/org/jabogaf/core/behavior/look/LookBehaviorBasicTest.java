package org.jabogaf.core.behavior.look;

import org.jabogaf.api.behavior.look.CanLookReport;
import org.jabogaf.api.behavior.look.LookBlock;
import org.jabogaf.api.behavior.look.Lookable;
import org.jabogaf.api.behavior.move.FieldsNotConnectedException;
import org.jabogaf.api.board.BoardManager;
import org.jabogaf.api.board.field.ContainsGameObjects;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.api.gamecontext.GameContextManager;
import org.jabogaf.api.resource.NotEnoughResourceException;
import org.jabogaf.api.resource.Resource;
import org.jabogaf.core.SimpleGridLayoutBoardWithSubject;
import org.jabogaf.core.object.GameObjectBasic;
import org.jabogaf.core.resource.LookPoint;
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
import java.util.Random;
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

    @Inject
    private BoardManager boardManager;

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
                return LookPoint.ONE;
            }
        });

        game.getGameSubject().setResource(LookPoint.NULL);
        game.getFieldConnection(0, 0, 0, 1).addObjectOnConnection(new GameObjectBasic<ContainsGameObjects>("go1") {
            @Override
            public Resource lookCost() {
                return LookPoint.ONE;
            }
        });
        assertFalse(lookBehavior.canLook(game.getGameSubject(), game.getField(0, 1), game.getGameSubject()).isPossible());
    }

    @Test
    public void testLookBlock() throws FieldsNotConnectedException, NotEnoughResourceException {
        lookBehavior.setLookBlockSet((m, t) -> false, new LookBlock1(), new LookBlock2());
        assertEquals(2, game.getGameSubject().canLook(game.getField(0, 1)).lookBlocks().size());
        lookBehavior.setLookBlockSet();
    }

    @Test
    public void testLookableFields() {
        assertEquals(24, game.getGameSubject().getLookableFields().size());

        game.getGameSubject().setResource(LookPoint.ONE);
        addObjectOnFieldConnection(0, 1, 0, 2, 2);
        addObjectOnFieldConnection(1, 1, 1, 2, 2);
        assertEquals(12, game.getGameSubject().getLookableFields().size());

        game.setPositionOfSubject(game.getField(2, 2));
        addObjectOnFieldConnection(1, 2, 2, 2, 2);
        addObjectOnFieldConnection(2, 1, 2, 2, 2);
        addObjectOnFieldConnection(3, 2, 2, 2, 2);
        addObjectOnFieldConnection(2, 3, 2, 2, 2);
        game.getGameSubject().getLookableFields().stream().forEach(System.out::println);
        assertEquals(0, game.getGameSubject().getLookableFields().size());
    }

    private void addObjectOnFieldConnection(int x1, int y1, int x2, int y2, int lookPoints) {
        FieldConnection fieldConnection = game.getFieldConnection(x1, y1, x2, y2);
        fieldConnection.addObjectOnConnection(new GameObjectBasic<ContainsGameObjects>("myGameObject" + new Random().nextInt()) {
            @Override
            public Resource lookCost() {
                return new LookPoint(lookPoints);
            }
        });
    }

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