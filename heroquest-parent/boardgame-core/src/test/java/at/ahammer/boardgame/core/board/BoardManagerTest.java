package at.ahammer.boardgame.core.board;

import at.ahammer.boardgame.api.board.Board;
import at.ahammer.boardgame.api.board.BoardManager;
import at.ahammer.boardgame.api.board.Layout;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.field.FieldConnection;
import at.ahammer.boardgame.core.test.ArquillianGameContext;
import at.ahammer.boardgame.core.test.ArquillianGameContextTest;
import at.ahammer.boardgame.core.test.BeforeInGameContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@RunWith(ArquillianGameContext.class)
public class BoardManagerTest extends AbstractBoardTest {

    @Inject
    private BoardManager boardManager;

    @Test
    public void testBoardManager() {
        Assert.assertEquals("board", boardManager.getBoard().getId());
        Assert.assertEquals(3, boardManager.getFields().size());
        Assert.assertEquals(2, boardManager.getAllFieldConectionObjects(field1, field2).size());
    }
}
