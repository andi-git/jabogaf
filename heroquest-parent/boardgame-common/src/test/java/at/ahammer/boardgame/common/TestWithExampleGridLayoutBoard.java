package at.ahammer.boardgame.common;

import at.ahammer.boardgame.api.board.Board;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.cdi.GameContextBean;
import at.ahammer.boardgame.api.cdi.GameContextManager;
import at.ahammer.boardgame.api.controller.PlayerController;
import at.ahammer.boardgame.api.subject.GameSubject;
import at.ahammer.boardgame.common.behavior.move.GameSubjectForMovement;
import at.ahammer.boardgame.common.board.layout.grid.GridLayout;
import at.ahammer.boardgame.common.board.layout.grid.GridLayoutCreationExample;
import at.ahammer.boardgame.core.board.BoardBasic;
import at.ahammer.boardgame.core.subject.GameSubjectNull;
import at.ahammer.boardgame.core.test.ArquillianGameContextTest;
import at.ahammer.boardgame.core.test.BeforeInGameContext;

import javax.inject.Inject;

@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public abstract class TestWithExampleGridLayoutBoard extends ArquillianGameContextTest {

    private Board board;

    @Inject
    private PlayerController playerController;

    @Inject
    private GameContextManager gameContextManager;

    private GameSubject currentPlayer;

    private GameSubjectForMovement player1;

    private GameSubjectForMovement player2;

    private GameSubjectForMovement player3;

    @BeforeInGameContext
    public void before() {
        board = new BoardBasic("dummyBoard", new GridLayout("dummyGridLayout", new GridLayoutCreationExample()));
        setCurrentPlayer(new GameSubjectNull());
        player1 = gameContextManager.resolve(new GameSubjectForMovement("player1", getField(1, 1)));
        player2 = gameContextManager.resolve(new GameSubjectForMovement("player2", getField(2, 2)));
        player3 = gameContextManager.resolve(new GameSubjectForMovement("player3", getField(4, 0)));
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

    protected GameSubjectForMovement getPlayer1() {
        return player1;
    }

    protected GameSubjectForMovement getPlayer2() {
        return player2;
    }

    protected GameSubjectForMovement getPlayer3() {
        return player3;
    }
}
