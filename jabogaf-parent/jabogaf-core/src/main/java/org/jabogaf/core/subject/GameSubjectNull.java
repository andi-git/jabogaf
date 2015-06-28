package org.jabogaf.core.subject;

import org.jabogaf.api.board.field.Field;
import org.jabogaf.core.behavior.look.LookBehaviorNull;
import org.jabogaf.core.behavior.move.MoveBehaviorNull;
import org.jabogaf.core.board.field.FieldNull;

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
