package at.ahammer.heroquest.subject.move;

import at.ahammer.boardgame.cdi.GameScoped;
import at.ahammer.boardgame.object.field.Field;
import at.ahammer.boardgame.subject.move.Move;

/**
 * Created by andreas on 8/22/14.
 */
@GameScoped
public class MoveAll implements Move {

    @Override
    public boolean canMove(Field start, Field end) {
        return true;
    }
}
