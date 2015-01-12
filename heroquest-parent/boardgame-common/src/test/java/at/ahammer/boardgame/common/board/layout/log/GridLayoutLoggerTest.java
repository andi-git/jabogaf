package at.ahammer.boardgame.common.board.layout.log;

import at.ahammer.boardgame.api.subject.GameSubject;
import at.ahammer.boardgame.common.TestWithExampleGridLayoutBoard;
import at.ahammer.boardgame.common.board.layout.GridLayout;
import at.ahammer.boardgame.core.subject.GameSubjectNull;
import at.ahammer.boardgame.core.test.ArquillianGameContext;
import at.ahammer.boardgame.core.test.BeforeInGameContext;
import org.junit.Assert;
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
        System.out.println(gridLayoutLogger.toString(getLayout()));
    }
}