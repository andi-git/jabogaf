package at.ahammer.boardgame.core.behavior.move;

import at.ahammer.boardgame.api.behavior.move.MovePath;
import at.ahammer.boardgame.api.behavior.move.Moveable;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.resource.ResourceHolder;
import at.ahammer.boardgame.core.resource.MovePoint;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class MoveableFieldCollectorBasic implements MoveableFieldsCollector {

    @Inject
    private MovePointCollector movePointCollector;

    @Inject
    private Logger log;

    @Override
    public Map<Field, MovePath> getMovableFields(Moveable moveable, ResourceHolder resourceHolder) {
        Map<Field, MovePath> movePaths = new HashMap<>();
        collect(moveable, moveable.getPosition(), moveable.getPosition(), resourceHolder, movePaths);
        movePaths.remove(moveable.getPosition());
        return Collections.unmodifiableMap(movePaths);
    }

    private void collect(Moveable moveable, Field position, Field target, ResourceHolder resourceHolder, Map<Field, MovePath> movePaths) {
        log.debug("collect path from " + position + " to " + target + ", movePathsSize: " + movePaths.size());
        if (position == null) {
            throw new IllegalStateException("position must not be null");
        }
        if (target == null) {
            throw new IllegalStateException("target must not be null");
        }
        if (!position.equals(target) && !position.isConnected(target)) {
            throw new IllegalStateException(position + " and " + target + " are not connected");
        }
        if (!positionAndTargetAreTheSame(position, target) && !targetIsPositionOfMoveable(target, moveable)) {
            MovePath newMovePath = createNewMovePath(position, target, moveable, movePaths);
            if (isShorterThanExisting(newMovePath, movePaths) && moveable.canMove(newMovePath, resourceHolder)) {
                movePaths.remove(target);
                movePaths.values().stream().forEach(mp -> {
                    mp.update(newMovePath);
                });
                movePaths.put(target, newMovePath);
                target.getConnectedFields().stream().forEach(field -> {
                    collect(moveable, target, field, resourceHolder, movePaths);
                });
            }
        }
        if (position.equals(target)) {
            movePaths.put(position, new MovePathBasic(position, position, new MovePoint(0)));
            target.getConnectedFields().stream().forEach(field -> {
                collect(moveable, target, field, resourceHolder, movePaths);
            });
        }
    }

    private boolean isShorterThanExisting(MovePath newMovePath, Map<Field, MovePath> movePaths) {
        boolean isShorterThanExisting = true;
        if (movePaths.containsKey(newMovePath.getTarget())) {
            isShorterThanExisting = newMovePath.cost().lesser(movePaths.get(newMovePath.getTarget()).cost());
        }
        return isShorterThanExisting;
    }

    private MovePath createNewMovePath(Field position, Field target, Moveable moveable, Map<Field, MovePath> movePaths) {
        MovePath newMovePath;
        if (movePaths.containsKey(position)) {
            newMovePath = new MovePathBasic(movePaths.get(position), target);
        } else {
            newMovePath = new MovePathBasic(moveable.getPosition(), target);
        }
        return newMovePath;
    }

    private boolean targetIsPositionOfMoveable(Field target, Moveable moveable) {
        return moveable.getPosition().equals(target);
    }

    private boolean positionAndTargetAreTheSame(Field position, Field target) {
        return position.equals(target);
    }
}
