package at.ahammer.heroquest.subject.look;

import at.ahammer.boardgame.cdi.GameScoped;
import at.ahammer.boardgame.object.field.Field;
import at.ahammer.boardgame.subject.look.Look;
import at.ahammer.boardgame.subject.look.LookStrategy;

/**
 * Created by andreas on 8/29/14.
 */
@GameScoped
@LookStrategy(LookAll.class)
public class LookAll implements Look {

    @Override
    public boolean canLook(Field start, Field end) {
        return true;
    }
}
