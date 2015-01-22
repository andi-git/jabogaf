package at.ahammer.boardgame.core.board;

import at.ahammer.boardgame.api.board.Board;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.field.FieldConnection;
import at.ahammer.boardgame.api.board.field.FieldConnectionObject;
import at.ahammer.boardgame.api.board.layout.Layout;
import at.ahammer.boardgame.core.board.field.*;
import at.ahammer.boardgame.core.board.layout.LayoutBasic;
import at.ahammer.boardgame.core.test.ArquillianGameContextTest;
import at.ahammer.boardgame.core.test.BeforeInGameContext;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public abstract class AbstractBoardTest extends ArquillianGameContextTest {

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
        field1 = new FieldBasic("field1");
        field2 = new FieldBasic("field2");
        field3 = new FieldBasic("field3");
        fields.add(field1);
        fields.add(field2);
        fields.add(field3);
        fieldConnection12 = new FieldConnectionBasic("fieldConnection12", field1, field2);
        fieldConnections.add(fieldConnection12);
        fieldConnection23 = new FieldConnectionBasic("fieldConnection23", field3, field2);
        fieldConnections.add(fieldConnection23);
        object1 = new FieldConnectionObjectBasic("object1") { };
        object2 = new FieldConnectionObjectBasic("object2") { };
        fieldConnection12.addObjectOnConnection(object1, object2);
        fieldConnectionObjects.add(object1);
        fieldConnectionObjects.add(object2);
        layout = new LayoutBasic("layout", fields, fieldConnections, new HashSet<>()) {
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
    }
}
