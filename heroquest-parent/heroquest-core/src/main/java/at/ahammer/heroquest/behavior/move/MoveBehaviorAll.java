package at.ahammer.heroquest.behavior.move;

import at.ahammer.boardgame.cdi.GameScoped;
import at.ahammer.boardgame.object.field.Field;
import at.ahammer.boardgame.behavior.move.MoveBehavior;

/**
 * Created by andreas on 8/22/14.
 */
@GameScoped
public class MoveBehaviorAll implements MoveBehavior {

    @Override
    public boolean canMove(Field start, Field end) {
        return true;
    }
}
