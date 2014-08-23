package at.ahammer.boardgame.entity.subject.move;

import at.ahammer.boardgame.entity.object.field.Field;
import at.ahammer.boardgame.entity.object.field.FieldConnectionObject;
import at.ahammer.boardgame.entity.subject.GameSubject;
import at.ahammer.boardgame.entity.subject.GameSubjectBehavior;

/**
 * Created by andreas on 8/14/14.
 */
public interface Move extends GameSubjectBehavior {

    boolean canMove(Field start, Field end);
}
