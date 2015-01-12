package at.ahammer.boardgame.common.board;

import at.ahammer.boardgame.common.TestWithExampleGridLayoutBoard;
import at.ahammer.boardgame.common.board.layout.GridLayout;
import at.ahammer.boardgame.core.test.ArquillianGameContext;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(ArquillianGameContext.class)
public class GridLayoutTest extends TestWithExampleGridLayoutBoard {

    @Test
    public void testFieldsAsStream() {
        assertEquals(25, getLayout().getFieldsAsStream().count());
        assertEquals("Field:0,0", getLayout().getFieldsAsStream().findFirst().get().getId());
    }
}
