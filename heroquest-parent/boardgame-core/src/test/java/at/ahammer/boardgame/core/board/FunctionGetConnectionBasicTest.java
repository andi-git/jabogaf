package at.ahammer.boardgame.core.board;

import at.ahammer.boardgame.api.board.FunctionGetConnection;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.field.FieldConnection;
import at.ahammer.boardgame.api.board.field.FieldConnectionNull;
import at.ahammer.boardgame.core.test.ArquillianGameContext;
import at.ahammer.boardgame.core.test.ArquillianGameContextTest;
import at.ahammer.boardgame.core.test.BeforeInGameContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

@RunWith(ArquillianGameContext.class)
public class FunctionGetConnectionBasicTest extends AbstractBoardTest {

    @Inject
    private FunctionGetConnection functionGetConnection;

    @Test
    public void testFunctionGetConnectionBasicNullOrEmpty() {
        Assert.assertNotNull(functionGetConnection);
        Assert.assertEquals(FieldConnectionNull.class, functionGetConnection.getConnection(null, field1, field2).getClass());
        Assert.assertEquals(FieldConnectionNull.class, functionGetConnection.getConnection(fieldConnections, null, null).getClass());
    }

    @Test
    public void testFunctionGetConnectionBasic() {
        Assert.assertEquals(fieldConnection12, functionGetConnection.getConnection(fieldConnections, field1, field2));
        Assert.assertEquals(fieldConnection23, functionGetConnection.getConnection(fieldConnections, field3, field2));
        Assert.assertEquals(FieldConnectionNull.class, functionGetConnection.getConnection(fieldConnections, field1, field3).getClass());
    }
}
