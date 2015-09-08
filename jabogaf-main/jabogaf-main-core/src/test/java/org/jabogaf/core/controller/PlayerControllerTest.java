package org.jabogaf.core.controller;

import org.jabogaf.api.controller.PlayerController;
import org.jabogaf.api.subject.GameSubject;
import org.jabogaf.core.subject.GameSubjectNull;
import org.jabogaf.test.gamecontext.ArquillianGameContext;
import org.jabogaf.test.gamecontext.ArquillianGameContextTest;
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
