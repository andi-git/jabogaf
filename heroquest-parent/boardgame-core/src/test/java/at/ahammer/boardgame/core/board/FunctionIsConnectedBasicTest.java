package at.ahammer.boardgame.core.board;

import at.ahammer.boardgame.api.board.FunctionIsConnected;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.field.FieldConnection;
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
public class FunctionIsConnectedBasicTest extends AbstractBoardTest {

    @Inject
    private FunctionIsConnected functionIsConnected;

    @Test
    public void testFunctionIsConnectedBasicNullOrEmpty() {
        Assert.assertFalse(functionIsConnected.isConnected(null, field1, field2));
        Assert.assertFalse(functionIsConnected.isConnected(fieldConnections, null, null));
    }

    @Test
    public void testFunctionIsConnectedBasic() {
        Assert.assertNotNull(functionIsConnected);
        Assert.assertTrue(functionIsConnected.isConnected(fieldConnections, field1, field2));
        Assert.assertTrue(functionIsConnected.isConnected(fieldConnections, field3, field2));
        Assert.assertFalse(functionIsConnected.isConnected(fieldConnections, field1, field3));
    }
}
