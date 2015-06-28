package org.jabogaf.core.behavior.move;

import org.jabogaf.api.behavior.move.Moveable;
import org.jabogaf.api.board.BoardManager;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.resource.Resource;
import org.jabogaf.core.resource.MovePoint;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MovePointCollectorBasic implements MovePointCollector {

    @Inject
    private BoardManager boardManager;

    @Override
    public Resource collect(Moveable moveable, Field target) {
        return collect(moveable.getPosition(), target);
    }

    @Override
    public Resource collect(Field position, Field target) {
        if (!position.isConnected(target)) {
            throw new IllegalStateException(position + " and" + target + " are not connected");
        }
        return new MovePoint(
                boardManager.getAllFieldConnectionObjects(position, target).stream().mapToInt(fco -> fco.movementCost().getAmount()).sum() +
                        target.movementCost().getAmount()
        );
    }
}
