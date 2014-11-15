package at.ahammer.boardgame.core.controller;

import at.ahammer.boardgame.api.controller.PlayerController;
import at.ahammer.boardgame.core.test.ArquillianGameContext;
import at.ahammer.boardgame.core.test.ArquillianGameContextTest;
import at.ahammer.boardgame.api.subject.GameSubjectNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(ArquillianGameContext.class)
public class PlayerControllerTest extends ArquillianGameContextTest {

    @Inject
    private PlayerController playerController;

    @Test
    public void testEmptyPlayerController() {
        Assert.assertEquals(GameSubjectNull.class, playerController.getCurrentPlayer().getClass());
    }
}
