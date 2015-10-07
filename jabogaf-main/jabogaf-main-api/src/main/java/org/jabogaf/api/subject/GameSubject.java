package org.jabogaf.api.subject;

import org.jabogaf.api.behavior.look.Lookable;
import org.jabogaf.api.behavior.move.*;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.gamecontext.GameContextBean;
import org.jabogaf.api.gamecontext.GameContextBeanWithState;
import org.jabogaf.api.resource.NotEnoughResourceException;
import org.jabogaf.api.resource.ResourceHolder;
import org.jabogaf.api.subject.artifact.ArtifactHolder;

import java.util.List;
import java.util.Map;

/**
 * A subject (i.e. hero, monster,...) in the game.
 */
public interface GameSubject extends GameContextBeanWithState<GameSubject>, Moveable, Lookable, ResourceHolder, ArtifactHolder {

    /**
     * @see {@link Moveable#move(Field, ResourceHolder)}
     */
    Field move(Field target) throws FieldsNotConnectedException, MoveNotPossibleException, NotEnoughResourceException;

    /**
     * @see {@link Moveable#move(MovePath, ResourceHolder)}
     */
    Field move(MovePath movePath) throws FieldsNotConnectedException, MoveNotPossibleException, NotEnoughResourceException;

    /**
     * @see {@link Moveable#canMove(Field, ResourceHolder)}
     */
    CanMoveReport canMove(Field target);

    /**
     * @see {@link Moveable#canMove(MovePath, ResourceHolder)}
     */
    boolean canMove(MovePath movePath);

    List<MovePath> getMovableFields();

    MovePath getShortestPath(Field target);

}