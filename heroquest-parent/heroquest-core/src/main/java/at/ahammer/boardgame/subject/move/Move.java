package at.ahammer.boardgame.subject.move;

import at.ahammer.boardgame.object.field.Field;
import at.ahammer.boardgame.subject.GameSubjectBehavior;

/**
 * Created by andreas on 8/14/14.
 */
public interface Move extends GameSubjectBehavior {

    boolean canMove(Field start, Field end);
}
