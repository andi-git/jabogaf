package at.ahammer.boardgame.board;

import at.ahammer.boardgame.cdi.GameContext;
import at.ahammer.boardgame.object.field.Field;
import at.ahammer.boardgame.test.util.ArquillianGameContext;
import at.ahammer.boardgame.test.util.ArquillianGameContextTest;
import at.ahammer.boardgame.test.util.RunAllMethodsInGameContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by andreas on 8/23/14.
 */
@RunWith(ArquillianGameContext.class)
public class BoardTest extends ArquillianGameContextTest implements RunAllMethodsInGameContext {

    @Test
    public void testBoard() {
        Board board = DummyBoard.getNewInstance();
        Field field11 = GameContext.current().getNewInstanceInGameContext(Field.class, "field:1,1");

        Assert.assertTrue(field11.isConnected(GameContext.current().getNewInstanceInGameContext(Field.class, "field:0,1")));
        Assert.assertTrue(field11.isConnected(GameContext.current().getNewInstanceInGameContext(Field.class, "field:1,0")));
        Assert.assertTrue(field11.isConnected(GameContext.current().getNewInstanceInGameContext(Field.class, "field:1,2")));
        Assert.assertTrue(field11.isConnected(GameContext.current().getNewInstanceInGameContext(Field.class, "field:2,1")));

        Assert.assertFalse(field11.isConnected(GameContext.current().getNewInstanceInGameContext(Field.class, "field:1,1")));
        Assert.assertFalse(field11.isConnected(GameContext.current().getNewInstanceInGameContext(Field.class, "field:0,0")));
        Assert.assertFalse(field11.isConnected(GameContext.current().getNewInstanceInGameContext(Field.class, "field:0,2")));
        Assert.assertFalse(field11.isConnected(GameContext.current().getNewInstanceInGameContext(Field.class, "field:2,0")));
        Assert.assertFalse(field11.isConnected(GameContext.current().getNewInstanceInGameContext(Field.class, "field:2,2")));
        Assert.assertFalse(field11.isConnected(GameContext.current().getNewInstanceInGameContext(Field.class, "field:3,3")));

    }
}
