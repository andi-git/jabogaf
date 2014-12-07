package at.ahammer.boardgame.common.board;

import at.ahammer.boardgame.api.board.Board;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.cdi.GameContextBean;
import at.ahammer.boardgame.api.cdi.GameContextManager;
import at.ahammer.boardgame.api.controller.PlayerController;
import at.ahammer.boardgame.api.subject.GameSubject;
import at.ahammer.boardgame.core.subject.GameSubjectNull;
import at.ahammer.boardgame.core.test.ArquillianGameContext;
import at.ahammer.boardgame.core.test.ArquillianGameContextTest;
import at.ahammer.boardgame.core.test.BeforeInGameContext;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(ArquillianGameContext.class)
public class DummyBoardTest extends ArquillianGameContextTest {

    private Board board;

    @Inject
    private PlayerController playerController;

    @Inject
    private GameContextManager gameContextManager;

    private GameSubject currentPlayer;

    @BeforeInGameContext
    public void before() {
        board = new Board("dummyBoard", new GridLayout("dummyGridLayout", new GridLayoutCreationDummy()));
        setCurrentPlayer(new GameSubjectNull());
    }

    public Board getBoard() {
        return board;
    }

    protected GridLayout getLayout() {
        return (GridLayout) board.getLayout();
    }

    protected Field getField(int x, int y) {
        return getLayout().getField(x, y);
    }

    protected <T> T getById(Class<T> type, String id) {
        return gameContextManager.getGameContextBean(type, id);
    }

    protected GameContextBean getById(String id) {
        return getById(GameContextBean.class, id);
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    @Override
    public GameContextManager getGameContextManager() {
        return gameContextManager;
    }

    public GameSubject getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(GameSubject currentPlayer) {
        this.currentPlayer = currentPlayer;
        playerController.setCurrentPlayer(currentPlayer);
    }

    @Test
    public void dummy() {
        // nothing
    }
}
