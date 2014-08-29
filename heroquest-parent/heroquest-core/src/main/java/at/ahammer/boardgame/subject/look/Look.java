package at.ahammer.boardgame.subject.look;

import at.ahammer.boardgame.object.field.Field;
import at.ahammer.boardgame.subject.GameSubjectBehavior;

/**
 * Created by andreas on 8/14/14.
 */
public interface Look extends GameSubjectBehavior {

    boolean canLook(Field start, Field end);

}
