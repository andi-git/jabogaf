package org.jabogaf.core.behavior.move;

import org.jabogaf.api.behavior.move.MovePath;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.event.GameStateInvalidEvent;
import org.jabogaf.api.resource.Resource;
import org.jabogaf.core.SimpleGridLayoutBoardWithSubject;
import org.jabogaf.core.object.GameObjectBasic;
import org.jabogaf.core.resource.MovePoint;
import org.jabogaf.test.gamecontext.ArquillianGameContext;
import org.jabogaf.test.gamecontext.ArquillianGameContextTest;
import org.jabogaf.test.gamecontext.BeforeInGameContext;
import org.jabogaf.util.log.LogWrapper;
import org.jabogaf.util.log.SLF4J;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(ArquillianGameContext.class)
public class MoveableFieldsTest extends ArquillianGameContextTest {

    @Inject
    private MoveableFields moveableFields;

    @Inject
    private SimpleGridLayoutBoardWithSubject game;

    @Inject
    private MoveableFields.MovePointHolder movePointHolder;

    @Inject
    private Event<GameStateInvalidEvent> gameStateInvalidEventEvent;

    @Inject
    @SLF4J
    private LogWrapper log;

    @BeforeInGameContext
    public void before() {
        movePointHolder.get(0); // init before tests because of performance-measures
    }

    @Test
    public void testGetMovableFields2x2() throws Exception {
        List<MovePath> movePaths;
        game.create(2, 2, 0, 0, 10);

        movePaths = moveableFields.get(new MoveableFields.Parameter(game.getGameSubject()));
        printMovePath(movePaths);
        assertEquals(3, movePaths.size());
        assertContainsNumberOfCost(movePaths, 2, 1);
        assertContainsNumberOfCost(movePaths, 1, 2);
        assertContainsNumberOfCost(movePaths, 0, 0);
        assertContainsNumberOfCost(movePaths, 0, 3);

        game.getGameSubject().setResource(new MovePoint(1));
        movePaths = moveableFields.get(new MoveableFields.Parameter(game.getGameSubject()));
        printMovePath(movePaths);
        assertEquals(2, movePaths.size());
        assertContainsNumberOfCost(movePaths, 2, 1);

        game.getGameSubject().setResource(new MovePoint(2));
        gameStateChange(() -> game.getFieldConnection(0, 0, 1, 0).addObjectOnConnection(new GameObjectBasic<Field>("fco1") {
            @Override
            public Resource movementCost() {
                return new MovePoint(3);
            }
        }));
        movePaths = moveableFields.get(new MoveableFields.Parameter(game.getGameSubject()));
        printMovePath(movePaths);
        assertEquals(2, movePaths.size());
        assertContainsNumberOfCost(movePaths, 1, 1);
        assertContainsNumberOfCost(movePaths, 1, 2);
    }

    @Test
    public void testGetMovableFields3x3() throws Exception {
        List<MovePath> movePaths;
        game.create(3, 3, 1, 1, 10);

        movePaths = moveableFields.get(new MoveableFields.Parameter(game.getGameSubject()));
        printMovePath(movePaths);
        assertEquals(8, movePaths.size());
        assertContainsNumberOfCost(movePaths, 4, 1);
        assertContainsNumberOfCost(movePaths, 4, 2);
        assertContainsNumberOfCost(movePaths, 0, 0);
        assertContainsNumberOfCost(movePaths, 0, 3);

        gameStateChange(() -> {
            game.getFieldConnection(0, 1, 1, 1).addObjectOnConnection(new GameObjectBasic<Field>("fco1") {
                @Override
                public Resource movementCost() {
                    return new MovePoint(9);
                }
            });
            game.getFieldConnection(0, 0, 0, 1).addObjectOnConnection(new GameObjectBasic<Field>("fco2") {
                @Override
                public Resource movementCost() {
                    return new MovePoint(20);
                }
            });
            game.getFieldConnection(0, 1, 0, 2).addObjectOnConnection(new GameObjectBasic<Field>("fco3") {
                @Override
                public Resource movementCost() {
                    return new MovePoint(20);
                }
            });
            game.getFieldConnection(1, 1, 2, 1).addObjectOnConnection(new GameObjectBasic<Field>("fco4") {
                @Override
                public Resource movementCost() {
                    return new MovePoint(20);
                }
            });
        });
        movePaths = moveableFields.get(new MoveableFields.Parameter(game.getGameSubject()));
        printMovePath(movePaths);
        assertEquals(8, movePaths.size());
        assertContainsNumberOfCost(movePaths, 2, 1);
        assertContainsNumberOfCost(movePaths, 4, 2);
        assertContainsNumberOfCost(movePaths, 0, 0);
        assertContainsNumberOfCost(movePaths, 1, 3);
        assertContainsNumberOfCost(movePaths, 1, 10);
    }

    @Test
    public void testShortestPath() {
        game.create(9, 9, 0, 0, 7);
        Instant start = Instant.now();
        moveableFields.get(new MoveableFields.Parameter(game.getGameSubject()));
        System.out.println(Duration.between(start, Instant.now()));
        assertEquals(1, game.getGameSubject().getShortestPath(game.getField(1, 0)).cost().getAmount());
        assertEquals(2, game.getGameSubject().getShortestPath(game.getField(1, 1)).cost().getAmount());
        assertEquals(3, game.getGameSubject().getShortestPath(game.getField(2, 1)).cost().getAmount());
        assertEquals(4, game.getGameSubject().getShortestPath(game.getField(2, 2)).cost().getAmount());
    }

    private void assertContainsNumberOfCost(List<MovePath> movePaths, int number, int cost) {
        assertEquals(number, movePaths.stream().filter(mp -> mp.cost().getAmount() == cost).count());
    }

    private void printMovePath(List<MovePath> movePaths) {
        log.debug("------------------------------------------------------");
        movePaths.stream().forEach((mp) -> log.debug("{}", mp::toString));
        log.debug("------------------------------------------------------");
    }

    private void gameStateChange(Runnable actionThatTriggersGameStateChange) {
        actionThatTriggersGameStateChange.run();
        gameStateInvalidEventEvent.fire(new GameStateInvalidEvent());
    }
}
