package at.ahammer.boardgame.common.object.field;

import at.ahammer.boardgame.api.board.field.Hidden;

public class SecretDoor extends Door implements Hidden {

    public SecretDoor(String id) {
        super(id);
    }
}