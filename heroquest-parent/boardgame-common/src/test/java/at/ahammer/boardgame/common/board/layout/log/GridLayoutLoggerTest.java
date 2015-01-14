package at.ahammer.boardgame.common.board.layout.log;

import at.ahammer.boardgame.api.subject.GameSubject;
import at.ahammer.boardgame.common.TestWithExampleGridLayoutBoard;
import at.ahammer.boardgame.common.board.layout.GridLayout;
import at.ahammer.boardgame.core.subject.GameSubjectNull;
import at.ahammer.boardgame.core.test.ArquillianGameContext;
import at.ahammer.boardgame.core.test.BeforeInGameContext;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(ArquillianGameContext.class)
public class GridLayoutLoggerTest extends TestWithExampleGridLayoutBoard {

    @Inject
    private GridLayoutLogger gridLayoutLogger;

    private GameSubject player1;

    private GameSubject player2;

    private GameSubject player3;

    @BeforeInGameContext
    public void before() {
        super.before();
        player1 = new GameSubjectNull("player1", getField(1, 1));
        player2 = new GameSubjectNull("player2", getField(2, 2));
        player3 = new GameSubjectNull("player3", getField(4, 0));
    }

    @Test
    public void testGenericType() throws Exception {
        assertEquals(GridLayout.class, gridLayoutLogger.genericType());
    }

    @Test
    public void testToString() throws Exception {
        String string = gridLayoutLogger.toString(getLayout());
        System.out.println(string);
        String expected = "id:dummyGridLayout\n" +
                "+------------------+-+------------------+-+------------------+-+------------------+-+------------------+-+------------------+\n" +
                "|Field:0,0         | |Field:1,0         |W|Field:2,0         | |Field:3,0         | |Field:4,0         | |Field:5,0         |\n" +
                "|*FGroup:room0     | |*FGroup:room0     |a|*FGroup:floor4    | |*FGroup:floor4    | |*FGroup:floor5    | |*FGroup:floor5    |\n" +
                "|                  | |                  |l|*FGroup:floor5    | |*FGroup:floor5    | |>player3          | |                  |\n" +
                "|                  | |                  |l|                  | |                  | |                  | |                  |\n" +
                "|                  | |                  |:|                  | |                  | |                  | |                  |\n" +
                "|                  | |                  |1|                  | |                  | |                  | |                  |\n" +
                "|                  | |                  | |                  | |                  | |                  | |                  |\n" +
                "|                  | |                  | |                  | |                  | |                  | |                  |\n" +
                "+------------------+-+------------------+-+------------------+-+------------------+-+------------------+-+------------------+\n" +
                "|                  | |                  | |                  | |                  | |Door:2,4-3,4     /| |Wall:5,0-5,1      |\n" +
                "+------------------+-+------------------+-+------------------+-+------------------+-+------------------+-+------------------+\n" +
                "|Field:0,1         | |Field:1,1         |D|Field:2,1         | |Field:3,1         |W|Field:4,1         | |Field:5,1         |\n" +
                "|*FGroup:room0     | |*FGroup:room0     |o|*FGroup:floor4    | |*FGroup:floor4    |a|*FGroup:room1     | |*FGroup:room1     |\n" +
                "|                  | |>player1          |o|                  | |                  |l|                  | |                  |\n" +
                "|                  | |                  |r|                  | |                  |l|                  | |                  |\n" +
                "|                  | |                  |:|                  | |                  |:|                  | |                  |\n" +
                "|                  | |                  |1|                  | |                  |2|                  | |                  |\n" +
                "|                  | |                  | |                  | |                  | |                  | |                  |\n" +
                "|                  | |                  |~|                  | |                  | |                  | |                  |\n" +
                "+------------------+-+------------------+-+------------------+-+------------------+-+------------------+-+------------------+\n" +
                "|Wall:0,1-0,2      | |SecDoor:1,1-1,2 _~| |                  | |                  | |                  | |                  |\n" +
                "+------------------+-+------------------+-+------------------+-+------------------+-+------------------+-+------------------+\n" +
                "|Field:0,2         | |Field:1,2         | |Field:2,2         | |Field:3,2         |W|Field:4,2         | |Field:5,2         |\n" +
                "|*FGroup:floor3    | |*FGroup:floor3    | |*FGroup:floor3    | |*FGroup:floor3    |a|*FGroup:room1     | |*FGroup:room1     |\n" +
                "|                  | |                  | |*FGroup:floor4    | |*FGroup:floor4    |l|                  | |                  |\n" +
                "|                  | |                  | |>player2          | |                  |l|                  | |                  |\n" +
                "|                  | |                  | |                  | |                  |:|                  | |                  |\n" +
                "|                  | |                  | |                  | |                  |3|                  | |                  |\n" +
                "|                  | |                  | |                  | |                  | |                  | |                  |\n" +
                "|                  | |                  | |                  | |                  | |                  | |                  |\n" +
                "+------------------+-+------------------+-+------------------+-+------------------+-+------------------+-+------------------+\n" +
                "|Wall:0,2-0,3      | |Door:1,2-1,3     ~| |Wall:2,2-2,3      | |Wall:3,2-3,3      | |                  | |                  |\n" +
                "+------------------+-+------------------+-+------------------+-+------------------+-+------------------+-+------------------+\n" +
                "|Field:0,3         | |Field:1,3         | |Field:2,3         | |Field:3,3         |D|Field:4,3         | |Field:5,3         |\n" +
                "|*FGroup:room2     | |*FGroup:room2     | |*FGroup:room2     | |*FGroup:room2     |o|*FGroup:room1     | |*FGroup:room1     |\n" +
                "|                  | |                  | |                  | |                  |o|                  | |                  |\n" +
                "|                  | |                  | |                  | |                  |r|                  | |                  |\n" +
                "|                  | |                  | |                  | |                  |:|                  | |                  |\n" +
                "|                  | |                  | |                  | |                  |3|                  | |                  |\n" +
                "|                  | |                  | |                  | |                  | |                  | |                  |\n" +
                "|                  | |                  | |                  | |                  |~|                  | |                  |\n" +
                "+------------------+-+------------------+-+------------------+-+------------------+-+------------------+-+------------------+\n";
        assertEquals(expected, string);
    }
}