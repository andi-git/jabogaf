package at.ahammer.boardgame.board;

import at.ahammer.boardgame.cdi.GameContext;
import at.ahammer.boardgame.cdi.NewInstanceInGameContext;
import at.ahammer.boardgame.controller.PlayerController;
import at.ahammer.boardgame.object.field.Field;
import at.ahammer.boardgame.test.util.ArquillianGameContext;
import at.ahammer.boardgame.test.util.ArquillianGameContextTest;
import at.ahammer.boardgame.test.util.BeforeInGameContext;
import at.ahammer.boardgame.test.util.RunAllMethodsInGameContext;
import at.ahammer.heroquest.subject.Barbarian;
import at.ahammer.heroquest.subject.Mage;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

/**
 * Created by andreas on 8/24/14.
 */
@RunWith(ArquillianGameContext.class)
public class TestWithDummyBoard extends ArquillianGameContextTest implements RunAllMethodsInGameContext {

    protected Board board;

    protected Barbarian barbarian;

    protected Mage mage;

    @Inject
    protected PlayerController playerController;

    @BeforeInGameContext
    public void setUp() {
        board = new Board("dummyBoard", new GridLayout("dummyGridLayout", new GridLayoutCreationDummy()));
        barbarian = new Barbarian("barbarian", getField(0, 4));
        mage = new Mage("mage", getField(4, 4));
        playerController.setCurrentPlayer(barbarian);
    }

    private Board getBoard() {
        return board;
    }

    protected GridLayout getLayout() {
        return (GridLayout) board.getLayout();
    }

    protected Field getField(int x, int y) { return getLayout().getField(x, y); }

    protected <T> T getById(String id, Class<T> clazz) {
        return GameContext.current().getNewInstanceInGameContext(clazz, id);
    }

    protected NewInstanceInGameContext getById(String id) {
        return getById(id, NewInstanceInGameContext.class);
    }

    @Test
    public void dummy() {
        // nothing
    }
}
