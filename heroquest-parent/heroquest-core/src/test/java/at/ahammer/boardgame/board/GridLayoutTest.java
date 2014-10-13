package at.ahammer.boardgame.board;

import at.ahammer.boardgame.cdi.GameContext;
import at.ahammer.boardgame.cdi.NewInstanceInGameContext;
import at.ahammer.boardgame.object.GameObject;
import at.ahammer.boardgame.object.field.FieldConnection;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

/**
 * Created by andreas on 8/29/14.
 */
public class GridLayoutTest extends TestWithDummyBoard {

    @Test
    public void testLook() {
        Set<FieldConnection> fieldConnectionsForLook = getLayout().getLookConnections(getField(1, 0), getField(2, 2));
        Assert.assertEquals(3, fieldConnectionsForLook.size());
        Assert.assertTrue(fieldConnectionsForLook.contains(getById("FieldConnection:1,0-1,1")));
        Assert.assertTrue(fieldConnectionsForLook.contains(getById("FieldConnection:1,1-2,1")));
        Assert.assertTrue(fieldConnectionsForLook.contains(getById("FieldConnection:2,1-2,2")));
    }

    @Test
    public void testGetAllGameObjectsOfField() {
        Set<GameObject> gameObjects = getLayout().getAllGameObjectsOf(getField(1, 4));
        Assert.assertEquals(1, gameObjects.size());
        Assert.assertEquals("Door:1,3-1,4", gameObjects.iterator().next().toString());
    }
}
