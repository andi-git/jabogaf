package org.jabogaf.common.behavior.move;

import org.jabogaf.api.behavior.move.MoveBlock;
import org.jabogaf.api.behavior.move.Moveable;
import org.jabogaf.api.board.BoardManager;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.common.object.field.Chair;
import org.jabogaf.common.object.field.Wall;
import org.jabogaf.core.behavior.move.MoveIsBlockedByType;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;

@ApplicationScoped
public class MoveBlockChair extends MoveIsBlockedByType<Chair> {

    @Override
    protected Class<Chair> type() {
        return Chair.class;
    }
}
