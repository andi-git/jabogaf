package at.ahammer.boardgame.core.behavior.move;

import at.ahammer.boardgame.api.behavior.move.MovePath;
import at.ahammer.boardgame.api.board.Board;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.field.FieldConnection;
import at.ahammer.boardgame.api.board.layout.Layout;
import at.ahammer.boardgame.api.cdi.GameContextManager;
import at.ahammer.boardgame.api.controller.PlayerController;
import at.ahammer.boardgame.api.resource.Resource;
import at.ahammer.boardgame.api.subject.GameSubject;
import at.ahammer.boardgame.core.board.BoardBasic;
import at.ahammer.boardgame.core.board.field.FieldBasic;
import at.ahammer.boardgame.core.board.field.FieldConnectionBasic;
import at.ahammer.boardgame.core.board.field.FieldConnectionObjectBasic;
import at.ahammer.boardgame.core.board.layout.LayoutBasic;
import at.ahammer.boardgame.core.resource.MovePoint;
import at.ahammer.boardgame.core.subject.GameSubjectBasic;
import at.ahammer.boardgame.core.test.ArquillianGameContext;
import at.ahammer.boardgame.core.test.ArquillianGameContextTest;
import at.ahammer.boardgame.core.test.BeforeInGameContext;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@RunWith(ArquillianGameContext.class)
public class MoveableFieldCollectorBasicTest extends ArquillianGameContextTest {

    @Inject
    private MoveableFieldsCollector moveableFieldsCollector;

    @Inject
    private GameContextManager gameContextManager;

    @Inject
    private PlayerController playerController;

    private Board board;

    private Layout layout;

    private Field[][] fieldsArray;

    private Map<String, Field> fields = new HashMap<>();

    private Map<String, FieldConnection> fieldConnections = new HashMap<>();

    private GameSubject gameSubject;

    @BeforeInGameContext
    public void before() {
    }

    protected void createLayout(int sizeX, int sizeY, int positionX, int positionY, int movementPoints) {
        createLayout(sizeX, sizeY);
        layout = new LayoutBasic("layout", getFieldsAsSet(), getFieldConnectionsAsSet(), new HashSet<>()) {
            @Override
            public Stream getFieldsAsStream() {
                return null;
            }

            @Override
            public Set<FieldConnection> getLookConnections(Field position, Field target) {
                return null;
            }
        };
        board = new BoardBasic("board", layout);

        gameSubject = new GameSubjectBasic("gameSubject", getField(positionX, positionY));
        gameSubject.earn(new MovePoint(movementPoints).asPayment());
        playerController.setCurrentPlayer(gameSubject);

    }

    protected Set<Field> getFieldsAsSet() {
        return fields.keySet().stream().map(key -> fields.get(key)).collect(Collectors.toSet());
    }

    protected Set<FieldConnection> getFieldConnectionsAsSet() {
        return fieldConnections.keySet().stream().map(key -> fieldConnections.get(key)).collect(Collectors.toSet());
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
        Map<Field, MovePath> movePaths;
        createLayout(2, 2, 0, 0, 10);

        movePaths = moveableFieldsCollector.getMovableFields(gameSubject, gameSubject);
        printMovePath(movePaths);
        assertEquals(3, movePaths.size());
        assertContainsNumberOfCost(movePaths, 2, 1);
        assertContainsNumberOfCost(movePaths, 1, 2);
        assertContainsNumberOfCost(movePaths, 0, 0);
        assertContainsNumberOfCost(movePaths, 0, 3);

        gameSubject.setResource(new MovePoint(1));
        movePaths = moveableFieldsCollector.getMovableFields(gameSubject, gameSubject);
        printMovePath(movePaths);
        assertEquals(2, movePaths.size());
        assertContainsNumberOfCost(movePaths, 2, 1);

        gameSubject.setResource(new MovePoint(2));
        getFieldConnection(0, 0, 1, 0).addObjectOnConnection(new FieldConnectionObjectBasic("fco1") {
            @Override
            public Resource movementCost() {
                return new MovePoint(3);
            }
        });
        movePaths = moveableFieldsCollector.getMovableFields(gameSubject, gameSubject);
        printMovePath(movePaths);
        assertEquals(2, movePaths.size());
        assertContainsNumberOfCost(movePaths, 1, 1);
        assertContainsNumberOfCost(movePaths, 1, 2);
    }

    @Test
    public void testGetMovableFields3x3() throws Exception {
        Map<Field, MovePath> movePaths;
        createLayout(3, 3, 1, 1, 10);

        movePaths = moveableFieldsCollector.getMovableFields(gameSubject, gameSubject);
        printMovePath(movePaths);
        assertEquals(8, movePaths.size());
        assertContainsNumberOfCost(movePaths, 4, 1);
        assertContainsNumberOfCost(movePaths, 4, 2);
        assertContainsNumberOfCost(movePaths, 0, 0);
        assertContainsNumberOfCost(movePaths, 0, 3);

        getFieldConnection(0, 1, 1, 1).addObjectOnConnection(new FieldConnectionObjectBasic("fco1") {
            @Override
            public Resource movementCost() {
                return new MovePoint(9);
            }
        });
        getFieldConnection(0, 0, 0, 1).addObjectOnConnection(new FieldConnectionObjectBasic("fco2") {
            @Override
            public Resource movementCost() {
                return new MovePoint(20);
            }
        });
        getFieldConnection(0, 1, 0, 2).addObjectOnConnection(new FieldConnectionObjectBasic("fco3") {
            @Override
            public Resource movementCost() {
                return new MovePoint(20);
            }
        });
        getFieldConnection(1, 1, 2, 1).addObjectOnConnection(new FieldConnectionObjectBasic("fco4") {
            @Override
            public Resource movementCost() {
                return new MovePoint(20);
            }
        });
        movePaths = moveableFieldsCollector.getMovableFields(gameSubject, gameSubject);
        printMovePath(movePaths);
        assertEquals(8, movePaths.size());
        assertContainsNumberOfCost(movePaths, 2, 1);
        assertContainsNumberOfCost(movePaths, 4, 2);
        assertContainsNumberOfCost(movePaths, 0, 0);
        assertContainsNumberOfCost(movePaths, 1, 3);
        assertContainsNumberOfCost(movePaths, 1, 10);
    }

    private void assertContainsNumberOfCost(Map<Field, MovePath> movePaths, int number, int cost) {
        assertEquals(number, movePaths.keySet().stream().filter(f -> movePaths.get(f).cost().getAmount() == cost).count());
    }

    private void printMovePath(Map<Field, MovePath> movePaths) {
        System.out.println("------------------------------------------------------");
        movePaths.keySet().forEach(key -> System.out.println(key + "-> " + movePaths.get(key)));
        System.out.println("------------------------------------------------------");
    }
}
