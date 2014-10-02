package at.ahammer.heroquest.subject.move;

import at.ahammer.boardgame.board.TestWithDummyBoard;
import at.ahammer.boardgame.subject.move.FieldsNotConnectedException;
import at.ahammer.boardgame.subject.move.MoveNotPossibleException;
import at.ahammer.boardgame.test.util.BeforeInGameContext;
import at.ahammer.heroquest.subject.Barbarian;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by andreas on 02.10.14.
 */
public class MoveHeroTest extends TestWithDummyBoard {

    private Barbarian barbarian;

    @BeforeInGameContext
    public void setUp() {
        super.setUp();
        barbarian = new Barbarian("Barbarian", getField(0, 4));
    }

    @Test()
    public void move() throws Exception {
        Assert.assertEquals("Field:0,4", barbarian.getPosition().getId());
        barbarian.move(getField(1, 4));
        Assert.assertEquals("Field:1,4", barbarian.getPosition().getId());
    }

    @Test(expected = FieldsNotConnectedException.class)
    public void moveWithFieldsNotConnectedException() throws Exception {
        barbarian.move(getField(2, 4));
    }

    @Test(expected = MoveNotPossibleException.class)
    public void moveWithMoveNotPossibleException() throws Exception {
        barbarian.move(getField(0, 3));
    }
}
