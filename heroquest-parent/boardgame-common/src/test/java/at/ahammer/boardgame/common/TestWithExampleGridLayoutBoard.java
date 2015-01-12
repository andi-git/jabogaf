package at.ahammer.boardgame.common;

import at.ahammer.boardgame.api.behavior.look.LookBehavior;
import at.ahammer.boardgame.api.behavior.move.MoveBehavior;
import at.ahammer.boardgame.api.board.Board;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.cdi.GameContextBean;
import at.ahammer.boardgame.api.controller.PlayerController;
import at.ahammer.boardgame.api.subject.GameSubject;
import at.ahammer.boardgame.common.board.layout.GridLayout;
import at.ahammer.boardgame.common.board.layout.GridLayoutCreationExample;
import at.ahammer.boardgame.core.subject.GameSubjectNull;
import at.ahammer.boardgame.core.test.ArquillianGameContextTest;
import at.ahammer.boardgame.core.test.BeforeInGameContext;

import javax.inject.Inject;

@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public abstract class TestWithExampleGridLayoutBoard extends ArquillianGameContextTest {

    private Board board;

    @Inject
    private PlayerController playerController;

    private GameSubject currentPlayer;

    @BeforeInGameContext
    public void before() {
        board = new Board("dummyBoard", new GridLayout("dummyGridLayout", new GridLayoutCreationExample()));
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
        return getGameContextManager().getGameContextBean(type, id);
    }

    protected GameContextBean getById(String id) {
        return getById(GameContextBean.class, id);
    }

    protected PlayerController getPlayerController() {
        return playerController;
    }

    protected GameSubject getCurrentPlayer() {
        return currentPlayer;
    }

    protected void setCurrentPlayer(GameSubject currentPlayer) {
        this.currentPlayer = currentPlayer;
        playerController.setCurrentPlayer(currentPlayer);
    }
}
