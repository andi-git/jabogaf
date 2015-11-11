package org.jabogaf.core.board;

import org.jabogaf.api.board.Board;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.api.board.layout.Layout;
import org.jabogaf.api.object.GameObject;
import org.jabogaf.common.board.layout.grid.GridLayoutCreationStrategy;
import org.jabogaf.core.behavior.look.LookPathBasic;
import org.jabogaf.core.board.field.FieldBasic;
import org.jabogaf.core.board.field.FieldConnectionBasic;
import org.jabogaf.core.board.layout.LayoutBasic;
import org.jabogaf.core.object.GameObjectBasic;
import org.jabogaf.test.gamecontext.ArquillianGameContextTest;
import org.jabogaf.test.gamecontext.BeforeInGameContext;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractBoardTest extends ArquillianGameContextTest {

    protected Field field1, field2, field3;

    protected FieldConnection fieldConnection12, fieldConnection23;

    protected GameObject object1, object2;

    protected Set<Field> fields = new HashSet<>();

    protected Set<FieldConnection> fieldConnections = new HashSet<>();

    protected Set<GameObject> fieldConnectionObjects = new HashSet<>();

    protected Board board;

    protected Layout layout;

    @BeforeInGameContext
    public void before() {
        GridLayoutCreationStrategy gridLayoutCreationStrategy = new GridLayoutCreationStrategy(3, 1,
                (x, y) -> new FieldBasic(Field.class.getSimpleName() + ":" + x + "," + y),
                (field1, field2) -> new FieldConnectionBasic(FieldConnection.class.getSimpleName() + ":" + field1.getId() + "-" + field2.getId(), field1, field2),
                LookPathBasic::new
        );
        board = new BoardBasic("board", new LayoutBasic("layout", gridLayoutCreationStrategy));
        field1 = gridLayoutCreationStrategy.getFieldsArray()[0][0];
        field2 = gridLayoutCreationStrategy.getFieldsArray()[0][1];
        field3 = gridLayoutCreationStrategy.getFieldsArray()[0][2];
        fieldConnection12 = field1.getConnectionTo(field2);
        fieldConnection23 = field2.getConnectionTo(field3);
        fieldConnections.add(fieldConnection12);
        fieldConnections.add(fieldConnection23);
        object1 = new GameObjectBasic("object1");
        object2 = new GameObjectBasic("object2");
        fieldConnection12.addObjectOnConnection(object1);
        fieldConnection12.addObjectOnConnection(object2);
    }
}
