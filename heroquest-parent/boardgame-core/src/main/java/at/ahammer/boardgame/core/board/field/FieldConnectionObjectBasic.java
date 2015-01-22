package at.ahammer.boardgame.core.board.field;

import at.ahammer.boardgame.api.board.field.FieldConnectionObject;
import at.ahammer.boardgame.core.object.GameObjectBasic;

public abstract class FieldConnectionObjectBasic extends GameObjectBasic implements FieldConnectionObject {

    public FieldConnectionObjectBasic(String id) {
        super(id);
    }
}
