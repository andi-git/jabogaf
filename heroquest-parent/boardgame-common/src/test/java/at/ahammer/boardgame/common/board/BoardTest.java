package at.ahammer.boardgame.common.board;

import org.junit.Assert;
import org.junit.Test;

public class BoardTest extends DummyBoardTest {

    @Test
    public void testBoard() {
        Assert.assertTrue(getBoard().getLayout() instanceof GridLayout);
        Assert.assertEquals(25, getBoard().getLayout().getFields().size());
    }
}
