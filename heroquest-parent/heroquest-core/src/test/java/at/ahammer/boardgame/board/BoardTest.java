package at.ahammer.boardgame.board;

import at.ahammer.boardgame.cdi.GameContext;
import at.ahammer.boardgame.object.field.Field;
import at.ahammer.boardgame.object.field.FieldGroup;
import at.ahammer.boardgame.test.util.ArquillianGameContext;
import at.ahammer.boardgame.test.util.ArquillianGameContextTest;
import at.ahammer.boardgame.test.util.RunAllMethodsInGameContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by andreas on 8/23/14.
 */
public class BoardTest extends TestWithDummyBoard {

    @Test
    public void testBoard() {
        Assert.assertTrue(board.getLayout() instanceof GridLayout);
        Assert.assertEquals(25, board.getLayout().getFields().size());
    }
}
