package at.ahammer.boardgame.controller;

import at.ahammer.boardgame.test.util.ArquillianGameContext;
import at.ahammer.boardgame.test.util.ArquillianGameContextTest;
import at.ahammer.boardgame.test.util.RunAllMethodsInGameContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

/**
 * Created by andreas on 08.10.14.
 */
@RunWith(ArquillianGameContext.class)
public class PlayerControllerTest extends ArquillianGameContextTest implements RunAllMethodsInGameContext {

    @Inject
    private PlayerController playerController;

    @Test
    public void testInitialPlayer() {
        Assert.assertEquals("GameSubjectNull", playerController.getCurrentPlayer().toString());
    }

    @Test
    public void testIsCurrentPlayer() {
        Assert.assertTrue(playerController.isCurrentPlayer(playerController.getCurrentPlayer()));
    }
}
