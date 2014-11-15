package at.ahammer.boardgame.common.board;

import org.junit.Assert;
import org.junit.Test;

public class BoardTest extends DummyBoardTest {

    @Test
    public void testBoard() {
        Assert.assertTrue(board.getLayout() instanceof GridLayout);
        Assert.assertEquals(25, board.getLayout().getFields().size());
    }
}
