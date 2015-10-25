package org.jabogaf.core.behavior.move;

import org.jabogaf.api.behavior.move.MoveBlock;
import org.jabogaf.api.behavior.move.Moveable;
import org.jabogaf.api.board.BoardManager;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.api.board.layout.LayoutActionImpact;

import javax.inject.Inject;
import java.util.Optional;

/**
 * A move is completely blocked because of a type.
 */
@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public abstract class MoveIsBlockedByType<T extends LayoutActionImpact<?, ?>> implements MoveBlock {

    @Inject
    private BoardManager boardManager;

    @Override
    public boolean blocks(Moveable moveable, Field target) {
        Optional<FieldConnection> fieldConnection = boardManager.getBoard().getLayout().getConnection(moveable.getPosition(), target);
        return fieldConnection.isPresent() && fieldConnection.get().getGameObjects().stream().anyMatch(o -> o.getClass() == type());
    }

    @Override
    public String toString() {
        return type().getSimpleName();
    }

    protected abstract Class<T> type();
}
