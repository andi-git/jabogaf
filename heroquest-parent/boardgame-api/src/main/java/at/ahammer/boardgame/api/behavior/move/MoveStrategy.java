package at.ahammer.boardgame.api.behavior.move;

import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.subject.SetterOfPosition;

import java.util.Set;

/**
 * The strategy to move a {@link at.ahammer.boardgame.api.behavior.move.Moveable}.
 */
public interface MoveStrategy {

    Field move(Moveable moveable, SetterOfPosition setterOfPosition, Field target, Set<MoveBlock> moveBlocks) throws FieldsNotConnectedException, MoveNotPossibleException;
}
