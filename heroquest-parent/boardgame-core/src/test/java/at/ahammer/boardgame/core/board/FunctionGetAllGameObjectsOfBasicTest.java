package at.ahammer.boardgame.core.board;

import at.ahammer.boardgame.api.behavior.look.LookBehavior;
import at.ahammer.boardgame.api.behavior.move.MoveBehavior;
import at.ahammer.boardgame.api.board.FunctionGetAllGameObjectsOf;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.field.FieldConnection;
import at.ahammer.boardgame.api.board.field.FieldConnectionObject;
import at.ahammer.boardgame.api.object.GameObject;
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
public class FunctionGetAllGameObjectsOfBasicTest extends AbstractBoardTest {

    @Inject
    private FunctionGetAllGameObjectsOf functionGetAllGameObjectsOf;

    @Test
    public void testFunctionGetAllGameObjectsOfBasicNullOrEmpty() {
        Assert.assertNotNull(functionGetAllGameObjectsOf);
        Assert.assertTrue(functionGetAllGameObjectsOf.getAllGameObjectsOf(null, field1, field2).isEmpty());
        Assert.assertTrue(functionGetAllGameObjectsOf.getAllGameObjectsOf(fieldConnections, null, null).isEmpty());
    }

    @Test
    public void testFunctionGetAllGameObjectsOfBasic() {
        Assert.assertEquals(2, functionGetAllGameObjectsOf.getAllGameObjectsOf(fieldConnections, field1, field2).size());
        Assert.assertTrue(functionGetAllGameObjectsOf.getAllGameObjectsOf(fieldConnections, field1, field3).isEmpty());
        Assert.assertTrue(functionGetAllGameObjectsOf.getAllGameObjectsOf(fieldConnections, field2, field3).isEmpty());
    }
}
