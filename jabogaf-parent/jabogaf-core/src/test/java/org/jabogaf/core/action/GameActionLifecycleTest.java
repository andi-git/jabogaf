package org.jabogaf.core.action;

import org.jabogaf.api.action.ActionNotPossibleException;
import org.jabogaf.api.action.GameActionLifecycle;
import org.jabogaf.core.test.ArquillianGameContext;
import org.jabogaf.core.test.ArquillianGameContextTest;
import org.jabogaf.core.test.BeforeInGameContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(ArquillianGameContext.class)
public class GameActionLifecycleTest extends ArquillianGameContextTest {

    @Inject
    private GameActionLifecycle gameActionLifecycle;

    @Inject
    private ObserveMyBeforeActionEvent observeMyBeforeActionEvent;

    @Inject
    private ObserveMyAfterActionEvent observeMyAfterActionEvent;

    @BeforeInGameContext
    public void before() {
        observeMyBeforeActionEvent.setInput(null);
        observeMyAfterActionEvent.setInput(null);
    }

    @Test
    public void testDefaultLifecycle() throws ActionNotPossibleException {
        gameActionLifecycle.perform(GameActionPreferencesBasic.newInstance());
    }

    @Test
    public void testFullLifecycle() throws ActionNotPossibleException {
        MyGameActionParameter myGameActionParameter = new MyGameActionParameter("test");
        MyBeforeActionEvent myBeforeActionEvent = new MyBeforeActionEvent(myGameActionParameter);
        MyAfterActionEvent myAfterActionEvent = new MyAfterActionEvent(myGameActionParameter);
        final boolean[] inPrerequsites = {false};
        final boolean[] inPerform = {false};

        gameActionLifecycle.perform(GameActionPreferencesBasic.newInstance()
                        .addBeforeActionEventCreation(() -> {
                            return myBeforeActionEvent;
                        })
                        .addPrerequisite(() -> {
                            inPrerequsites[0] = true;
                        })
                        .addPerform(() -> {
                            inPerform[0] = true;
                            myGameActionParameter.setInput("ok");
                        })
                        .addAfterActionEventCreation(() -> {
                            return myAfterActionEvent;
                        })
        );

        Assert.assertTrue(inPrerequsites[0]);
        Assert.assertTrue(inPerform[0]);
        Assert.assertEquals("test", myBeforeActionEvent.getOriginalInput());
        Assert.assertEquals("ok", myAfterActionEvent.getActionParameter().getInput());
    }

    @Test(expected = ActionNotPossibleException.class)
    public void testPrerequisiteThrowsException() throws ActionNotPossibleException {
        gameActionLifecycle.perform(GameActionPreferencesBasic.newInstance().addPrerequisite(() -> {
            throw new ActionNotPossibleException();
        }));
    }

    @Test(expected = ActionNotPossibleException.class)
    public void testPerformThrowsException() throws ActionNotPossibleException {
        gameActionLifecycle.perform(GameActionPreferencesBasic.newInstance().addPerform(() -> {
            throw new Exception();
        }));
    }
}
