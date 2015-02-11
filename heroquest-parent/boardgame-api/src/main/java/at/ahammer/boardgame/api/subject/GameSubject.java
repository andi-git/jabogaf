package at.ahammer.boardgame.api.subject;

import at.ahammer.boardgame.api.behavior.look.Lookable;
import at.ahammer.boardgame.api.behavior.move.FieldsNotConnectedException;
import at.ahammer.boardgame.api.behavior.move.MoveNotPossibleException;
import at.ahammer.boardgame.api.behavior.move.MovePath;
import at.ahammer.boardgame.api.behavior.move.Moveable;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.cdi.GameContextBean;
import at.ahammer.boardgame.api.resource.NotEnoughResourceException;
import at.ahammer.boardgame.api.resource.ResourceHolder;
import at.ahammer.boardgame.api.subject.artifact.ArtifactHolder;

/**
 * A subject (i.e. hero, monster,...) in the game.
 */
public interface GameSubject extends GameContextBean, Moveable, Lookable, ResourceHolder, ArtifactHolder {

    /**
     * @see {@link at.ahammer.boardgame.api.behavior.move.Moveable#move(at.ahammer.boardgame.api.board.field.Field,
     * at.ahammer.boardgame.api.resource.ResourceHolder)}
     */
    Field move(Field target) throws FieldsNotConnectedException, MoveNotPossibleException, NotEnoughResourceException;

    /**
     * @see {@link at.ahammer.boardgame.api.behavior.move.Moveable#move(at.ahammer.boardgame.api.behavior.move.MovePath,
     * at.ahammer.boardgame.api.resource.ResourceHolder)}
     */
    Field move(MovePath movePath) throws FieldsNotConnectedException, MoveNotPossibleException, NotEnoughResourceException;

    /**
     * @see {@link at.ahammer.boardgame.api.behavior.move.Moveable#canMove(at.ahammer.boardgame.api.board.field.Field,
     * at.ahammer.boardgame.api.resource.ResourceHolder)}
     */
    boolean canMove(Field target);

    /**
     * @see {@link at.ahammer.boardgame.api.behavior.move.Moveable#canMove(at.ahammer.boardgame.api.behavior.move.MovePath,
     * at.ahammer.boardgame.api.resource.ResourceHolder)}
     */
    boolean canMove(MovePath movePath);
}