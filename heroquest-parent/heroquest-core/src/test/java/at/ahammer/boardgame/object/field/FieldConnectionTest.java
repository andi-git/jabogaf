package at.ahammer.boardgame.object.field;

import at.ahammer.boardgame.board.TestWithDummyBoard;
import at.ahammer.boardgame.cdi.GameContext;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by andreas on 8/23/14.
 */
public class FieldConnectionTest extends TestWithDummyBoard {

    @Test
    public void testFieldConnection() {
        Field field11 = GameContext.current().getNewInstanceInGameContext(Field.class, "Field:1,1");

        Assert.assertTrue(field11.isConnected(GameContext.current().getNewInstanceInGameContext(Field.class, "Field:0,1")));
        Assert.assertTrue(field11.isConnected(GameContext.current().getNewInstanceInGameContext(Field.class, "Field:1,0")));
        Assert.assertTrue(field11.isConnected(GameContext.current().getNewInstanceInGameContext(Field.class, "Field:1,2")));
        Assert.assertTrue(field11.isConnected(GameContext.current().getNewInstanceInGameContext(Field.class, "Field:2,1")));

        Assert.assertFalse(field11.isConnected(GameContext.current().getNewInstanceInGameContext(Field.class, "Field:1,1")));
        Assert.assertFalse(field11.isConnected(GameContext.current().getNewInstanceInGameContext(Field.class, "Field:0,0")));
        Assert.assertFalse(field11.isConnected(GameContext.current().getNewInstanceInGameContext(Field.class, "Field:0,2")));
        Assert.assertFalse(field11.isConnected(GameContext.current().getNewInstanceInGameContext(Field.class, "Field:2,0")));
        Assert.assertFalse(field11.isConnected(GameContext.current().getNewInstanceInGameContext(Field.class, "Field:2,2")));
        Assert.assertFalse(field11.isConnected(GameContext.current().getNewInstanceInGameContext(Field.class, "Field:3,3")));
    }
}
