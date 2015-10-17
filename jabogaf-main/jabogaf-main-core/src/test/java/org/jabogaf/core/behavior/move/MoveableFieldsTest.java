package org.jabogaf.core.behavior.move;

import org.jabogaf.api.behavior.move.MovePath;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.api.board.layout.Layout;
import org.jabogaf.api.controller.PlayerController;
import org.jabogaf.api.event.GameStateInvalidEvent;
import org.jabogaf.api.gamecontext.FireEvent;
import org.jabogaf.api.gamecontext.GameContextManager;
import org.jabogaf.api.resource.Resource;
import org.jabogaf.api.subject.GameSubject;
import org.jabogaf.core.board.BoardBasic;
import org.jabogaf.core.board.field.FieldBasic;
import org.jabogaf.core.board.field.FieldConnectionBasic;
import org.jabogaf.core.board.layout.LayoutBasic;
import org.jabogaf.core.object.GameObjectBasic;
import org.jabogaf.core.resource.MovePoint;
import org.jabogaf.core.subject.GameSubjectBasic;
import org.jabogaf.test.gamecontext.ArquillianGameContext;
import org.jabogaf.test.gamecontext.ArquillianGameContextTest;
import org.jabogaf.test.gamecontext.BeforeInGameContext;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@RunWith(ArquillianGameContext.class)
public class MoveableFieldsTest extends ArquillianGameContextTest {

    @Inject
    private MoveableFields moveableFields;

    @Inject
    private GameContextManager gameContextManager;

    @Inject
    private PlayerController playerController;

    private Field[][] fieldsArray;

    private Map<String, Field> fields = new HashMap<>();

    private Map<String, FieldConnection> fieldConnections = new HashMap<>();

    private GameSubject gameSubject;

    @Inject
    private MoveableFields.MovePointHolder movePointHolder;

    @Inject
    private Event<GameStateInvalidEvent> gameStateInvalidEventEvent;

    @BeforeInGameContext
    public void before() {
        movePointHolder.get(0); // init before tests because of performance-measures
    }

    protected void createLayout(int sizeX, int sizeY, int positionX, int positionY, int movementPoints) {
        createLayout(sizeX, sizeY);
        Layout layout = new LayoutBasic("layout", getFieldsAsSet(), getFieldConnectionsAsSet(), new HashSet<>()) {
            @Override
            public Stream<Field> getFieldsAsStream() {
                return null;
            }

            @Override
            public Set<FieldConnection> getLookConnections(Field position, Field target) {
                return null;
            }
        };
        new BoardBasic("board", layout);

        gameSubject = new GameSubjectBasic("gameSubject", getField(positionX, positionY));
        gameSubject.earn(new MovePoint(movementPoints, FireEvent.None).asPayment());
        playerController.setCurrentPlayer(gameSubject);

    }

    protected Set<Field> getFieldsAsSet() {
        return fields.keySet().stream().map(fields::get).collect(Collectors.toSet());
    }

    protected Set<FieldConnection> getFieldConnectionsAsSet() {
        return fieldConnections.keySet().stream().map(fieldConnections::get).collect(Collectors.toSet());
    }

    protected void createLayout(int x, int y) {
        fieldsArray = new Field[y][x];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                String id = "f" + i + "" + j;
                fieldsArray[i][j] = new FieldBasic(id);
                fields.put(id, fieldsArray[i][j]);
            }
        }
        createFieldConnections();
    }

    protected void createFieldConnections() {
        int y = fieldsArray.length;
        int x = fieldsArray[0].length;
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if ((i + 1) < y) {
                    String id = "fc" + i + "" + j + "-" + (i + 1) + "" + j;
                    fieldConnections.put(id, new FieldConnectionBasic(id, fieldsArray[i][j], fieldsArray[i + 1][j]));
                }
                if ((j + 1) < x) {
                    String id = "fc" + i + "" + j + "-" + i + "" + (j + 1);
                    fieldConnections.put(id, new FieldConnectionBasic(id, fieldsArray[i][j], fieldsArray[i][j + 1]));
                }
            }
        }
    }

    protected Field getField(int x, int y) {
        return gameContextManager.getGameContextBean(Field.class, "f" + x + "" + y);
    }

    protected FieldConnection getFieldConnection(int x1, int y1, int x2, int y2) {
        return gameContextManager.getGameContextBean(FieldConnection.class, "fc" + x1 + "" + y1 + "-" + x2 + "" + y2);
    }

    @Test
    public void testGetMovableFields2x2() throws Exception {
        List<MovePath> movePaths;
        createLayout(2, 2, 0, 0, 10);

        movePaths = moveableFields.get(new MoveableFields.Parameter(gameSubject));
        printMovePath(movePaths);
        assertEquals(3, movePaths.size());
        assertContainsNumberOfCost(movePaths, 2, 1);
        assertContainsNumberOfCost(movePaths, 1, 2);
        assertContainsNumberOfCost(movePaths, 0, 0);
        assertContainsNumberOfCost(movePaths, 0, 3);

        gameSubject.setResource(new MovePoint(1));
        movePaths = moveableFields.get(new MoveableFields.Parameter(gameSubject));
        printMovePath(movePaths);
        assertEquals(2, movePaths.size());
        assertContainsNumberOfCost(movePaths, 2, 1);

        gameSubject.setResource(new MovePoint(2));
        gameStateChange(() -> getFieldConnection(0, 0, 1, 0).addObjectOnConnection(new GameObjectBasic("fco1") {
            @Override
            public Resource movementCost() {
                return new MovePoint(3);
            }
        }));
        movePaths = moveableFields.get(new MoveableFields.Parameter(gameSubject));
        printMovePath(movePaths);
        assertEquals(2, movePaths.size());
        assertContainsNumberOfCost(movePaths, 1, 1);
        assertContainsNumberOfCost(movePaths, 1, 2);
    }

    @Test
    public void testGetMovableFields3x3() throws Exception {
        List<MovePath> movePaths;
        createLayout(3, 3, 1, 1, 10);

        movePaths = moveableFields.get(new MoveableFields.Parameter(gameSubject));
        printMovePath(movePaths);
        assertEquals(8, movePaths.size());
        assertContainsNumberOfCost(movePaths, 4, 1);
        assertContainsNumberOfCost(movePaths, 4, 2);
        assertContainsNumberOfCost(movePaths, 0, 0);
        assertContainsNumberOfCost(movePaths, 0, 3);

        gameStateChange(() -> {
            getFieldConnection(0, 1, 1, 1).addObjectOnConnection(new GameObjectBasic("fco1") {
                @Override
                public Resource movementCost() {
                    return new MovePoint(9);
                }
            });
            getFieldConnection(0, 0, 0, 1).addObjectOnConnection(new GameObjectBasic("fco2") {
                @Override
                public Resource movementCost() {
                    return new MovePoint(20);
                }
            });
            getFieldConnection(0, 1, 0, 2).addObjectOnConnection(new GameObjectBasic("fco3") {
                @Override
                public Resource movementCost() {
                    return new MovePoint(20);
                }
            });
            getFieldConnection(1, 1, 2, 1).addObjectOnConnection(new GameObjectBasic("fco4") {
                @Override
                public Resource movementCost() {
                    return new MovePoint(20);
                }
            });
        });
        movePaths = moveableFields.get(new MoveableFields.Parameter(gameSubject));
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
        createLayout(9, 9, 0, 0, 7);
        Instant start = Instant.now();
        moveableFields.get(new MoveableFields.Parameter(gameSubject));
        System.out.println(Duration.between(start, Instant.now()));
        assertEquals(1, gameSubject.getShortestPath(getField(1, 0)).cost().getAmount());
        assertEquals(2, gameSubject.getShortestPath(getField(1, 1)).cost().getAmount());
        assertEquals(3, gameSubject.getShortestPath(getField(2, 1)).cost().getAmount());
        assertEquals(4, gameSubject.getShortestPath(getField(2, 2)).cost().getAmount());
    }

    private void assertContainsNumberOfCost(List<MovePath> movePaths, int number, int cost) {
        assertEquals(number, movePaths.stream().filter(mp -> mp.cost().getAmount() == cost).count());
    }

    private void printMovePath(List<MovePath> movePaths) {
        System.out.println("------------------------------------------------------");
        movePaths.stream().forEach(System.out::println);
        System.out.println("------------------------------------------------------");
    }

    private <T> void gameStateChange(Runnable actionThatTriggersGameStateChange) {
        actionThatTriggersGameStateChange.run();
        gameStateInvalidEventEvent.fire(new GameStateInvalidEvent());
    }
}
