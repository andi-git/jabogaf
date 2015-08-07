package org.jabogaf.common;

import org.jabogaf.api.board.Board;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.cdi.GameContextBean;
import org.jabogaf.api.cdi.GameContextManager;
import org.jabogaf.api.controller.PlayerController;
import org.jabogaf.api.subject.GameSubject;
import org.jabogaf.common.behavior.move.GameSubjectForMovement;
import org.jabogaf.common.board.layout.grid.GridLayout;
import org.jabogaf.common.board.layout.grid.GridLayoutCreationExample;
import org.jabogaf.core.board.BoardBasic;
import org.jabogaf.core.resource.MovePoint;
import org.jabogaf.core.subject.GameSubjectNull;
import org.jabogaf.test.cdi.ArquillianGameContextTest;
import org.jabogaf.test.cdi.BeforeInGameContext;

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
        player1.earn(new MovePoint(10).asPayment());
        player2.earn(new MovePoint(10).asPayment());
        player3.earn(new MovePoint(10).asPayment());
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
