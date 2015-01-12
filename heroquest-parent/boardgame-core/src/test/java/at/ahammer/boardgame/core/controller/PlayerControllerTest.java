package at.ahammer.boardgame.core.controller;

import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.controller.PlayerController;
import at.ahammer.boardgame.api.subject.GameSubject;
import at.ahammer.boardgame.core.cdi.bean.MyGameContextBean;
import at.ahammer.boardgame.core.cdi.bean.MyOtherGameContextBean;
import at.ahammer.boardgame.core.subject.GameSubjectNull;
import at.ahammer.boardgame.core.test.ArquillianGameContext;
import at.ahammer.boardgame.core.test.ArquillianGameContextTest;
import org.junit.Assert;
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
        GameSubject subject = new MySubject();
        playerController.setCurrentPlayer(subject);
        assertEquals(subject, playerController.getCurrentPlayer());
    }

    @Test
    public void testGetAllGameSubjects() {
        Field field1 = new Field("field1");
        Field field2 = new Field("field2");
        new MySubject("subject1", field1);
        new MySubject("subject2", field1);
        new MySubject("subject3", null);
        assertEquals(3, playerController.getAllGameSubjects().size());
    }

    @Test
    public void testGetAllGameSubjectsOnField() {
        Field field1 = new Field("field1");
        Field field2 = new Field("field2");
        new MySubject("subject1", field1);
        new MySubject("subject2", field1);
        new MySubject("subject3", null);
        assertEquals(2, playerController.getAllGameSubjects(field1).size());
        assertEquals(0, playerController.getAllGameSubjects(field2).size());
    }
}
