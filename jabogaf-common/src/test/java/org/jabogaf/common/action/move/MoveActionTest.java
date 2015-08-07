package org.jabogaf.common.action.move;

import org.jabogaf.api.action.ActionNotPossibleException;
import org.jabogaf.common.TestWithExampleGridLayoutBoard;
import org.jabogaf.core.resource.MovePoint;
import org.jabogaf.core.subject.GameSubjectNull;
import org.jabogaf.test.cdi.ArquillianGameContext;
import org.jabogaf.test.cdi.BeforeInGameContext;
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