package at.ahammer.boardgame.common.board;

import at.ahammer.boardgame.api.board.Board;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.cdi.GameContextBean;
import at.ahammer.boardgame.api.cdi.GameContextManager;
import at.ahammer.boardgame.api.controller.PlayerController;
import at.ahammer.boardgame.core.test.ArquillianGameContext;
import at.ahammer.boardgame.core.test.ArquillianGameContextTest;
import at.ahammer.boardgame.core.test.BeforeInGameContext;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(ArquillianGameContext.class)
public class DummyBoardTest extends ArquillianGameContextTest {

    protected Board board;

    @Inject
    protected PlayerController playerController;

    @Inject
    protected GameContextManager gameContextManager;

    @BeforeInGameContext
    public void setUp() {
        board = new Board("dummyBoard", new GridLayout("dummyGridLayout", new GridLayoutCreationDummy()));
//        playerController.setCurrentPlayer(barbarian);
    }

    private Board getBoard() {
        return board;
    }

    protected GridLayout getLayout() {
        return (GridLayout) board.getLayout();
    }

    protected Field getField(int x, int y) { return getLayout().getField(x, y); }

    protected <T> T getById(Class<T> type, String id) {
        return gameContextManager.getGameContextBean(type, id);
    }

    protected GameContextBean getById(String id) {
        return getById(GameContextBean.class, id);
    }

    @Test
    public void dummy() {
        // nothing
    }
}
