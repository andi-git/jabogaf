package at.ahammer.boardgame.core.controller;

import at.ahammer.boardgame.api.controller.PlayerController;
import at.ahammer.boardgame.api.subject.GameSubject;
import at.ahammer.boardgame.core.subject.GameSubjectNull;
import at.ahammer.boardgame.core.test.ArquillianGameContext;
import at.ahammer.boardgame.core.test.ArquillianGameContextTest;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(ArquillianGameContext.class)
public class PlayerControllerTest extends ArquillianGameContextTest {

    @Inject
    private PlayerController playerController;

    @Test
    public void testEmptyPlayerController() {
        assertEquals(GameSubjectNull.class, playerController.getCurrentPlayer().getClass());
    }

    @Test
    public void testPlayerController() {
        GameSubject subject = new GameSubjectNull();
        playerController.setCurrentPlayer(subject);
        assertEquals(subject, playerController.getCurrentPlayer());
    }
}
