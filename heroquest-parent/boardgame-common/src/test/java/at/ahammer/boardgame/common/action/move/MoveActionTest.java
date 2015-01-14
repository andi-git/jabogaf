package at.ahammer.boardgame.common.action.move;

import at.ahammer.boardgame.api.action.ActionNotPossibleException;
import at.ahammer.boardgame.common.TestWithExampleGridLayoutBoard;
import at.ahammer.boardgame.common.object.field.Door;
import at.ahammer.boardgame.core.subject.GameSubjectNull;
import at.ahammer.boardgame.core.test.ArquillianGameContext;
import at.ahammer.boardgame.core.test.BeforeInGameContext;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import javax.inject.Inject;

@RunWith(ArquillianGameContext.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MoveActionTest extends TestWithExampleGridLayoutBoard {

    @Inject
    private MoveAction moveAction;

    @Inject
    private ObserveBeforeMoveActionEvent observeBeforeMoveActionEvent;

    @Inject
    private ObserveAfterMoveActionEvent observeAfterMoveActionEvent;

    @Override
    @BeforeInGameContext
    public void before() {
        super.before();
        setCurrentPlayer(new MyGameSubject("dummyGameSubject", getField(4, 0)));
    }

    @Test
    public void testPerformAction() throws Exception {
        Assert.assertFalse(observeBeforeMoveActionEvent.wasObserved());
        Assert.assertFalse(observeAfterMoveActionEvent.wasObserved());
        Door door = getById(Door.class, "Door:3,3-4,3");
        Assert.assertFalse(door.isVisible());
        moveAction.perform(new MoveActionParameter(getCurrentPlayer(), getField(4, 1)));
        Assert.assertTrue(observeBeforeMoveActionEvent.wasObserved());
        Assert.assertTrue(observeAfterMoveActionEvent.wasObserved());
    }

    @Test(expected = ActionNotPossibleException.class)
    public void testPerformMoveNotPossible() throws Exception {
        setCurrentPlayer(new GameSubjectNull("anotherGameSubjectNull", null));
        moveAction.perform(new MoveActionParameter(getCurrentPlayer(), getField(4, 1)));
    }

    @Test(expected = ActionNotPossibleException.class)
    public void testPerformNotActivePlayer() throws Exception {
        getPlayerController().setCurrentPlayer(new GameSubjectNull("nonActivePlayer", null));
        moveAction.perform(new MoveActionParameter(getCurrentPlayer(), getField(4, 1)));
    }
}