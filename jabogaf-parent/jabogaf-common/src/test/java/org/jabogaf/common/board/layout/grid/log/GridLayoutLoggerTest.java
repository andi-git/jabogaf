package org.jabogaf.common.board.layout.grid.log;

import org.jabogaf.common.TestWithExampleGridLayoutBoard;
import org.jabogaf.common.board.layout.grid.GridLayout;
import org.jabogaf.common.object.field.Door;
import org.jabogaf.core.resource.MovePoint;
import org.jabogaf.core.test.ArquillianGameContext;
import org.jabogaf.core.test.BeforeInGameContext;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(ArquillianGameContext.class)
public class GridLayoutLoggerTest extends TestWithExampleGridLayoutBoard {

    @Inject
    private GridLayoutLogger gridLayoutLogger;

    @BeforeInGameContext
    public void before() {
        super.before();
    }

    @Test
    public void testGenericType() throws Exception {
        assertEquals(GridLayout.class, gridLayoutLogger.genericType());
    }

    @Test
    public void testToString() throws Exception {
        String string = gridLayoutLogger.toString(getLayout(), new GridLayoutLoggerParameter(getPlayer2()));
//        System.out.println(string);
        String expected = "id:dummyGridLayout\n" +
                "+------------------+-+------------------+-+------------------+-+------------------+-+------------------+-+------------------+\n" +
                "|Field:0,0         | |Field:1,0         |W|Field:2,0         | |Field:3,0         | |Field:4,0         | |Field:5,0         |\n" +
                "|*FGroup:room0     | |*FGroup:room0     |a|*FGroup:floor4    | |*FGroup:floor4    | |*FGroup:floor5    | |*FGroup:floor5    |\n" +
                "|                  | |                  |l|*FGroup:floor5    | |*FGroup:floor5    | |>player3          | |>mp-player2:5     |\n" +
                "|                  | |                  |l|>mp-player2:2     | |>mp-player2:3     | |>mp-player2:4     | |                  |\n" +
                "|                  | |                  |:|                  | |                  | |                  | |                  |\n" +
                "|                  | |                  |1|                  | |                  | |                  | |                  |\n" +
                "|                  | |                  | |                  | |                  | |                  | |                  |\n" +
                "|                  | |                  | |                  | |                  | |                  | |                  |\n" +
                "+------------------+-+------------------+-+------------------+-+------------------+-+------------------+-+------------------+\n" +
                "|                  | |                  | |                  | |                  | |Door:2,4-3,4     /| |Wall:5,0-5,1      |\n" +
                "+------------------+-+------------------+-+------------------+-+------------------+-+------------------+-+------------------+\n" +
                "|Field:0,1         | |Field:1,1         |D|Field:2,1         | |Field:3,1         |W|Field:4,1         | |Field:5,1         |\n" +
                "|*FGroup:room0     | |*FGroup:room0     |o|*FGroup:floor4    | |*FGroup:floor4    |a|*FGroup:room1     | |*FGroup:room1     |\n" +
                "|                  | |>player1          |o|>mp-player2:1     | |>mp-player2:2     |l|>mp-player2:5     | |>mp-player2:6     |\n" +
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
                "|>mp-player2:2     | |>mp-player2:1     | |*FGroup:floor4    | |*FGroup:floor4    |l|>mp-player2:6     | |>mp-player2:7     |\n" +
                "|                  | |                  | |>player2          | |>mp-player2:1     |l|                  | |                  |\n" +
                "|                  | |                  | |                  | |                  |:|                  | |                  |\n" +
                "|                  | |                  | |                  | |                  |3|                  | |                  |\n" +
                "|                  | |                  | |                  | |                  | |                  | |                  |\n" +
                "|                  | |                  | |                  | |                  | |                  | |                  |\n" +
                "+------------------+-+------------------+-+------------------+-+------------------+-+------------------+-+------------------+\n" +
                "|Wall:0,2-0,3      | |Door:1,2-1,3     ~| |Wall:2,2-2,3      | |Wall:3,2-3,3      | |                  | |                  |\n" +
                "+------------------+-+------------------+-+------------------+-+------------------+-+------------------+-+------------------+\n" +
                "|Field:0,3         | |Field:1,3         | |Field:2,3         | |Field:3,3         |D|Field:4,3         | |Field:5,3         |\n" +
                "|*FGroup:room2     | |*FGroup:room2     | |*FGroup:room2     | |*FGroup:room2     |o|*FGroup:room1     | |*FGroup:room1     |\n" +
                "|                  | |                  | |                  | |                  |o|>mp-player2:7     | |>mp-player2:8     |\n" +
                "|                  | |                  | |                  | |                  |r|                  | |                  |\n" +
                "|                  | |                  | |                  | |                  |:|                  | |                  |\n" +
                "|                  | |                  | |                  | |                  |3|                  | |                  |\n" +
                "|                  | |                  | |                  | |                  | |                  | |                  |\n" +
                "|                  | |                  | |                  | |                  |~|                  | |                  |\n" +
                "+------------------+-+------------------+-+------------------+-+------------------+-+------------------+-+------------------+\n";
        assertEquals(expected, string);

        getGameContextManager().getGameContextBean(Door.class, "Door:1,2-1,3").open();
        getGameContextManager().getGameContextBean(Door.class, "Door:2,4-3,4").close();
        getPlayer3().setResource(new MovePoint(6));
        string = gridLayoutLogger.toString(getLayout(), new GridLayoutLoggerParameter(getPlayer3()));
//        System.out.println(string);
        expected = "id:dummyGridLayout\n" +
                "+------------------+-+------------------+-+------------------+-+------------------+-+------------------+-+------------------+\n" +
                "|Field:0,0         | |Field:1,0         |W|Field:2,0         | |Field:3,0         | |Field:4,0         | |Field:5,0         |\n" +
                "|*FGroup:room0     | |*FGroup:room0     |a|*FGroup:floor4    | |*FGroup:floor4    | |*FGroup:floor5    | |*FGroup:floor5    |\n" +
                "|                  | |                  |l|*FGroup:floor5    | |*FGroup:floor5    | |>player3          | |>mp-player3:1     |\n" +
                "|                  | |                  |l|>mp-player3:2     | |>mp-player3:1     | |                  | |                  |\n" +
                "|                  | |                  |:|                  | |                  | |                  | |                  |\n" +
                "|                  | |                  |1|                  | |                  | |                  | |                  |\n" +
                "|                  | |                  | |                  | |                  | |                  | |                  |\n" +
                "|                  | |                  | |                  | |                  | |                  | |                  |\n" +
                "+------------------+-+------------------+-+------------------+-+------------------+-+------------------+-+------------------+\n" +
                "|                  | |                  | |                  | |                  | |Door:2,4-3,4     ~| |Wall:5,0-5,1      |\n" +
                "+------------------+-+------------------+-+------------------+-+------------------+-+------------------+-+------------------+\n" +
                "|Field:0,1         | |Field:1,1         |D|Field:2,1         | |Field:3,1         |W|Field:4,1         | |Field:5,1         |\n" +
                "|*FGroup:room0     | |*FGroup:room0     |o|*FGroup:floor4    | |*FGroup:floor4    |a|*FGroup:room1     | |*FGroup:room1     |\n" +
                "|                  | |>player1          |o|>mp-player3:3     | |>mp-player3:2     |l|                  | |                  |\n" +
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
                "|>mp-player3:6     | |>mp-player3:5     | |*FGroup:floor4    | |*FGroup:floor4    |l|                  | |                  |\n" +
                "|                  | |                  | |>player2          | |>mp-player3:3     |l|                  | |                  |\n" +
                "|                  | |                  | |>mp-player3:4     | |                  |:|                  | |                  |\n" +
                "|                  | |                  | |                  | |                  |3|                  | |                  |\n" +
                "|                  | |                  | |                  | |                  | |                  | |                  |\n" +
                "|                  | |                  | |                  | |                  | |                  | |                  |\n" +
                "+------------------+-+------------------+-+------------------+-+------------------+-+------------------+-+------------------+\n" +
                "|Wall:0,2-0,3      | |Door:1,2-1,3     /| |Wall:2,2-2,3      | |Wall:3,2-3,3      | |                  | |                  |\n" +
                "+------------------+-+------------------+-+------------------+-+------------------+-+------------------+-+------------------+\n" +
                "|Field:0,3         | |Field:1,3         | |Field:2,3         | |Field:3,3         |D|Field:4,3         | |Field:5,3         |\n" +
                "|*FGroup:room2     | |*FGroup:room2     | |*FGroup:room2     | |*FGroup:room2     |o|*FGroup:room1     | |*FGroup:room1     |\n" +
                "|                  | |>mp-player3:6     | |                  | |                  |o|                  | |                  |\n" +
                "|                  | |                  | |                  | |                  |r|                  | |                  |\n" +
                "|                  | |                  | |                  | |                  |:|                  | |                  |\n" +
                "|                  | |                  | |                  | |                  |3|                  | |                  |\n" +
                "|                  | |                  | |                  | |                  | |                  | |                  |\n" +
                "|                  | |                  | |                  | |                  |~|                  | |                  |\n" +
                "+------------------+-+------------------+-+------------------+-+------------------+-+------------------+-+------------------+\n";
        assertEquals(expected, string);
    }
}
