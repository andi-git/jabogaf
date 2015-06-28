package org.jabogaf.common.behavior.move;

import org.jabogaf.api.behavior.move.MoveBlock;
import org.jabogaf.api.behavior.move.Moveable;
import org.jabogaf.api.board.BoardManager;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.object.GameObject;
import org.jabogaf.api.subject.GameSubject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.lang.reflect.Method;

@ApplicationScoped
public class MoveBlockGameSubjectOnField implements MoveBlock {

    @Inject
    private BoardManager boardManager;

    @Override
    public boolean blocks(Moveable moveable, Field target) {
        return false;
    }

    @Override
    public String toString() {
        return GameSubject.class.getSimpleName();
    }
}
