package at.ahammer.boardgame.common.action.move;

import at.ahammer.boardgame.api.action.ActionNotPossibleException;
import at.ahammer.boardgame.common.TestWithExampleGridLayoutBoard;
import at.ahammer.boardgame.common.object.field.Door;
import at.ahammer.boardgame.core.resource.MovePoint;
import at.ahammer.boardgame.core.subject.GameSubjectNull;
import at.ahammer.boardgame.core.test.ArquillianGameContext;
import at.ahammer.boardgame.core.test.BeforeInGameContext;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
        setCurrentPlayer(getPlayer3());
    }

    @Test
    public void testPerformAction() throws Exception {
        assertFalse(observeBeforeMoveActionEvent.wasObserved());
        assertFalse(observeAfterMoveActionEvent.wasObserved());
        assertEquals(getField(4, 0), getPlayer3().getPosition());
        assertEquals(10, getPlayer3().amountOf(MovePoint.class));
        moveAction.perform(new MoveActionParameter(getCurrentPlayer(), getField(4, 1)));
        assertTrue(observeBeforeMoveActionEvent.wasObserved());
        assertTrue(observeAfterMoveActionEvent.wasObserved());
        assertEquals(getField(4, 1), getPlayer3().getPosition());
        assertEquals(9, getPlayer3().amountOf(MovePoint.class));
    }

    @Test(expected = ActionNotPossibleException.class)
    public void testPerformMoveNotPossible() throws Exception {
        setCurrentPlayer(new GameSubjectNull("anotherGameSubjectNull"));
        moveAction.perform(new MoveActionParameter(getCurrentPlayer(), getField(4, 1)));
    }

    @Test(expected = ActionNotPossibleException.class)
    public void testPerformNotActivePlayer() throws Exception {
        getPlayerController().setCurrentPlayer(new GameSubjectNull("nonActivePlayer"));
        moveAction.perform(new MoveActionParameter(getCurrentPlayer(), getField(4, 1)));
    }
}