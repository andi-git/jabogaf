package at.ahammer.boardgame.core.subject;

import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.core.behavior.look.LookBehaviorNull;
import at.ahammer.boardgame.core.behavior.move.MoveBehaviorNull;
import at.ahammer.boardgame.core.board.field.FieldNull;

import javax.enterprise.inject.Typed;

@Typed
public class GameSubjectNull extends GameSubjectBasic {

    public GameSubjectNull() {
        this("GameSubjectNull", new FieldNull());
    }

    public GameSubjectNull(String id) {
        this(id, new FieldNull());
    }

    public GameSubjectNull(String id, Field position) {
        super(id, position, new MoveBehaviorNull(), new LookBehaviorNull());
    }
}
