package at.ahammer.boardgame.board;

import at.ahammer.boardgame.test.util.ArquillianGameContext;
import at.ahammer.boardgame.test.util.ArquillianGameContextTest;
import at.ahammer.boardgame.test.util.BeforeInGameContext;
import at.ahammer.boardgame.test.util.RunAllMethodsInGameContext;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by andreas on 8/24/14.
 */
@RunWith(ArquillianGameContext.class)
public class TestWithDummyBoard extends ArquillianGameContextTest implements RunAllMethodsInGameContext {

    protected Board board;

    @BeforeInGameContext
    public void setUp() {
        board = new Board("dummyBoard", new GridLayout("dummyGridLayout", new GridLayoutCreationDummy()));
    }

    private Board getBoard() {
        return board;
    }

    protected GridLayout getLayout() {
        return (GridLayout) board.getLayout();
    }

    @Test
    public void dummy() {
        // nothing
    }
}
