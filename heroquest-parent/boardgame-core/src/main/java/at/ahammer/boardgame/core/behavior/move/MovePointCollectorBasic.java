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
        Resource movePoints = new MovePoint();
        boardManager.getAllFieldConnectionObjects(moveable.getPosition(), target).stream().forEach(fco -> movePoints.add(fco.movementCost()));
        movePoints.add(target.movementCost());
        return movePoints;
    }
}
