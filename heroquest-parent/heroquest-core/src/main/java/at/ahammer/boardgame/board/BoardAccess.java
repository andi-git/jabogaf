package at.ahammer.boardgame.board;

import at.ahammer.boardgame.cdi.GameContext;
import at.ahammer.boardgame.cdi.GameScoped;

import javax.ws.rs.Produces;

/**
 * Created by andreas on 8/29/14.
 */
@GameScoped
public class BoardAccess {

    public Board getBoard() {
        return GameContext.current().getNewInstanceInGameContext(Board.class);
    }
}
