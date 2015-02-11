package at.ahammer.boardgame.core.behavior.move;

import at.ahammer.boardgame.api.behavior.move.Moveable;
import at.ahammer.boardgame.api.board.BoardManager;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.resource.Resource;
import at.ahammer.boardgame.core.resource.MovePoint;

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
