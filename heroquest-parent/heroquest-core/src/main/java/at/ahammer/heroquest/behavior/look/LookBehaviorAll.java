package at.ahammer.heroquest.behavior.look;

import at.ahammer.boardgame.cdi.GameScoped;
import at.ahammer.boardgame.object.field.Field;
import at.ahammer.boardgame.behavior.look.LookBehavior;

/**
 * Created by andreas on 8/29/14.
 */
@GameScoped
public class LookBehaviorAll implements LookBehavior {

    @Override
    public boolean canLook(Field start, Field end) {
        return true;
    }
}
