package at.ahammer.boardgame.core.board;

import at.ahammer.boardgame.api.behavior.look.LookBehavior;
import at.ahammer.boardgame.api.behavior.move.MoveBehavior;
import at.ahammer.boardgame.api.board.Board;
import at.ahammer.boardgame.api.board.Layout;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.field.FieldConnection;
import at.ahammer.boardgame.api.board.field.FieldConnectionObject;
import at.ahammer.boardgame.core.test.ArquillianGameContextTest;
import at.ahammer.boardgame.core.test.BeforeInGameContext;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractBoardTest extends ArquillianGameContextTest{

    protected Field field1, field2, field3;

    protected FieldConnection fieldConnection12, fieldConnection23;

    protected FieldConnectionObject object1, object2;

    protected Set<Field> fields = new HashSet<>();

    protected Set<FieldConnection> fieldConnections = new HashSet<>();

    protected Set<FieldConnectionObject> fieldConnectionObjects = new HashSet<>();

    protected Board board;

    protected Layout layout;

    @BeforeInGameContext
    public void before() {
        field1 = new Field("field1");
        field2 = new Field("field2");
        field3 = new Field("field3");
        fields.add(field1);
        fields.add(field2);
        fields.add(field3);
        fieldConnection12 = new FieldConnection("fieldConnection12", field1, field2);
        fieldConnections.add(fieldConnection12);
        fieldConnection23 = new FieldConnection("fieldConnection23", field3, field2);
        fieldConnections.add(fieldConnection23);
        object1 = new FieldConnectionObject("object1") {
            @Override
            public boolean canMove(MoveBehavior moveBehavior) {
                return false;
            }

            @Override
            public boolean canLook(LookBehavior lookBehavior) {
                return false;
            }
        };
        object2 = new FieldConnectionObject("object2") {
            @Override
            public boolean canMove(MoveBehavior moveBehavior) {
                return false;
            }

            @Override
            public boolean canLook(LookBehavior lookBehavior) {
                return false;
            }
        };
        fieldConnection12.addObjectOnConnection(object1, object2);
        fieldConnectionObjects.add(object1);
        fieldConnectionObjects.add(object2);
        layout = new Layout("layout", fields, fieldConnections, new HashSet<>()) {
            @Override
            public Set<FieldConnection> getLookConnections(Field position, Field target) {
                return null;
            }
        };
        board = new Board("board", layout);
    }

}