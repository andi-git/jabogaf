package at.ahammer.heroquest.object.field;

import at.ahammer.boardgame.entity.object.field.FieldConnectionObject;
import at.ahammer.boardgame.entity.subject.HiddenGameSubject;
import at.ahammer.boardgame.entity.subject.look.Look;
import at.ahammer.boardgame.entity.subject.move.Move;

/**
 * Created by andreas on 8/14/14.
 */
public class SecretDoor extends Door implements HiddenGameSubject {

    protected SecretDoor(String id) {
        super(id);
    }
}