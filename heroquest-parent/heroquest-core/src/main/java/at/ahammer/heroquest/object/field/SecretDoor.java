package at.ahammer.heroquest.object.field;

import at.ahammer.boardgame.subject.HiddenGameSubject;

/**
 * Created by andreas on 8/14/14.
 */
public class SecretDoor extends Door implements HiddenGameSubject {

    protected SecretDoor(String id) {
        super(id);
    }
}