package at.ahammer.boardgame.core.board;

import at.ahammer.boardgame.api.board.BoardManager;
import at.ahammer.boardgame.core.test.ArquillianGameContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(ArquillianGameContext.class)
public class BoardManagerTest extends AbstractBoardTest {

    @Inject
    private BoardManager boardManager;

    @Test
    public void testBoardManager() {
        Assert.assertEquals("board", boardManager.getBoard().getId());
        Assert.assertEquals(3, boardManager.getFields().size());
        Assert.assertEquals(2, boardManager.getAllFieldConnectionObjects(field1, field2).size());
    }
}
