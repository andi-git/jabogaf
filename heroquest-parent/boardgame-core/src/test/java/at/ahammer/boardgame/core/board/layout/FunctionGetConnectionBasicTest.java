package at.ahammer.boardgame.core.board.layout;

import at.ahammer.boardgame.api.board.layout.FunctionGetConnection;
import at.ahammer.boardgame.core.board.AbstractBoardTest;
import at.ahammer.boardgame.core.board.field.FieldConnectionNull;
import at.ahammer.boardgame.core.test.ArquillianGameContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

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
