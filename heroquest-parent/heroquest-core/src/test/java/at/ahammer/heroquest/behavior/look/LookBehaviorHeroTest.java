package at.ahammer.heroquest.behavior.look;

import at.ahammer.boardgame.board.TestWithDummyBoard;
import at.ahammer.boardgame.behavior.look.LookNotPossibleException;
import at.ahammer.boardgame.test.util.BeforeInGameContext;
import at.ahammer.heroquest.subject.Barbarian;
import org.junit.Test;

/**
 * Created by andreas on 02.10.14.
 */
public class LookBehaviorHeroTest extends TestWithDummyBoard {

    private Barbarian barbarian;

    @BeforeInGameContext
    public void setUp() {
        super.setUp();
        barbarian = new Barbarian("Barbarian", getField(0, 4));
    }

    @Test()
    public void look() throws Exception {
        barbarian.look(getField(1, 4));
    }

    @Test(expected = LookNotPossibleException.class)
    public void lookWithLookNotPossibleException() throws Exception {
        barbarian.look(getField(0, 3));
    }
}
