package at.ahammer.boardgame.common.board;

import at.ahammer.boardgame.common.TestWithExampleGridLayoutBoard;
import at.ahammer.boardgame.common.board.layout.GridLayout;
import at.ahammer.boardgame.core.test.ArquillianGameContext;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(ArquillianGameContext.class)
public class BoardTest extends TestWithExampleGridLayoutBoard {

    @Test
    public void testBoard() {
        assertTrue(getLayout() instanceof GridLayout);
        assertEquals(24, getLayout().getFields().size());
    }
}
