package at.ahammer.boardgame.core.behavior.move;

import at.ahammer.boardgame.api.behavior.move.*;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.subject.SetterOfPosition;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class MoveStrategyDefault implements MoveStrategy {

    @Inject
    private CanMoveStrategy canMoveStrategy;

    @Override
    public Field move(Moveable moveable, SetterOfPosition setterOfPosition, Field target, Set<MoveBlock> moveBlocks) throws FieldsNotConnectedException, MoveNotPossibleException {
        if (!moveable.getPosition().isConnected(target)) {
            throw new FieldsNotConnectedException(moveable.getPosition(), target);
        }
        if (canMoveStrategy.canMove(moveable, target, moveBlocks)) {
            setterOfPosition.setPosition(target);
            return target;
        } else {
            throw new MoveNotPossibleException(moveBlocks.stream().filter(moveBlock -> moveBlock.blocks(moveable, target)).collect(Collectors.toSet()));
        }
    }
}
