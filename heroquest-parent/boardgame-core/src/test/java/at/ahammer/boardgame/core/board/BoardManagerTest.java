package at.ahammer.boardgame.core.board;

import at.ahammer.boardgame.api.board.BoardManager;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.core.board.field.FieldBasic;
import at.ahammer.boardgame.core.subject.GameSubjectNull;
import at.ahammer.boardgame.core.test.ArquillianGameContext;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(ArquillianGameContext.class)
public class BoardManagerTest extends AbstractBoardTest {

    @Inject
    private BoardManager boardManager;

    @Test
    public void testBoardManager() {
        assertEquals("board", boardManager.getBoard().getId());
        assertEquals(3, boardManager.getFields().size());
        assertEquals(2, boardManager.getAllFieldConnectionObjects(field1, field2).size());
    }

    @Test
    public void testGetAllGameSubjects() {
        new GameSubjectNull("subject1", field1);
        new GameSubjectNull("subject2", field1);
        new GameSubjectNull("subject3");
        assertEquals(3, boardManager.getAllGameSubjects().size());
    }

    @Test
    public void testGetAllGameSubjectsOnField() {
        new GameSubjectNull("subject1", field1);
        new GameSubjectNull("subject2", field1);
        new GameSubjectNull("subject3");
        assertEquals(2, boardManager.getAllGameSubjects(field1).size());
        assertEquals(0, boardManager.getAllGameSubjects(field2).size());
    }

}
