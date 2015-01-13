package at.ahammer.boardgame.common.board.layout.log;

import at.ahammer.boardgame.api.subject.GameSubject;
import at.ahammer.boardgame.common.TestWithExampleGridLayoutBoard;
import at.ahammer.boardgame.common.board.layout.GridLayout;
import at.ahammer.boardgame.core.subject.GameSubjectNull;
import at.ahammer.boardgame.core.test.ArquillianGameContext;
import at.ahammer.boardgame.core.test.BeforeInGameContext;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(ArquillianGameContext.class)
public class GridLayoutLoggerTest extends TestWithExampleGridLayoutBoard {

    @Inject
    private GridLayoutLogger gridLayoutLogger;

    private GameSubject player1;

    private GameSubject player2;

    @BeforeInGameContext
    public void before() {
        super.before();
        player1 = new GameSubjectNull("player1", getField(0, 0));
        player2 = new GameSubjectNull("player2", getField(0, 1));
    }

    @Test
    public void testGenericType() throws Exception {
        assertEquals(GridLayout.class, gridLayoutLogger.genericType());
    }

    @Test
    public void testToString() throws Exception {
        String string = gridLayoutLogger.toString(getLayout());
        System.out.println(string);
        String[] lines = string.split("\n");
        assertEquals(51, lines.length);
        assertEquals(100, lines[1].length());
        assertFirstOrLastLine(lines, 1, 10, 11, 20, 21, 30, 31, 40, 41, 50);
        assertEmptyLine(lines, 4, 5, 6, 7, 8, 9, 13, 49);
        assertEquals("|Field:0,0         ||Field:0,1         ||Field:0,2         ||Field:0,3         ||Field:0,4         |", lines[2]);
        assertEquals("|player1           ||player2           ||                  ||                  ||                  |", lines[3]);
        assertEquals("|Field:1,0         ||Field:1,1         ||Field:1,2         ||Field:1,3         ||Field:1,4         |", lines[12]);
        assertEquals("|Field:2,0         ||Field:2,1         ||Field:2,2         ||Field:2,3         ||Field:2,4         |", lines[22]);
        assertEquals("|Field:3,0         ||Field:3,1         ||Field:3,2         ||Field:3,3         ||Field:3,4         |", lines[32]);
        assertEquals("|Field:4,0         ||Field:4,1         ||Field:4,2         ||Field:4,3         ||Field:4,4         |", lines[42]);
    }

    private void assertFirstOrLastLine(String[] lines, int... positions) {
        for (int position : positions) {
            assertEquals("+------------------++------------------++------------------++------------------++------------------+", lines[position]);
        }
    }

    private void assertEmptyLine(String[] lines, int... positions) {
        for (int position : positions) {
            assertEquals("|                  ||                  ||                  ||                  ||                  |", lines[position]);
        }
    }
}