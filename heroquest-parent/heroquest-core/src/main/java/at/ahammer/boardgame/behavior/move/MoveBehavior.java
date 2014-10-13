package at.ahammer.boardgame.behavior.move;

import at.ahammer.boardgame.object.field.Field;
import at.ahammer.boardgame.subject.GameSubjectBehavior;

/**
 * Created by andreas on 8/14/14.
 */
public interface MoveBehavior extends GameSubjectBehavior {

    boolean canMove(Field start, Field end);
}
