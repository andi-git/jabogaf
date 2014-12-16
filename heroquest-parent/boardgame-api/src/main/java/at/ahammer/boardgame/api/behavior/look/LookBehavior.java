package at.ahammer.boardgame.api.behavior.look;

import at.ahammer.boardgame.api.board.Layout;
import at.ahammer.boardgame.api.board.field.Field;

import java.util.List;
import java.util.Set;

/**
 * The strategy of a look from one {@link at.ahammer.boardgame.api.board.field.Field} to another,
 * <p/>
 * With {@link #canLook(at.ahammer.boardgame.api.board.field.Field, at.ahammer.boardgame.api.board.field.Field)} it can
 * be checked if it is possible to look from one {@link at.ahammer.boardgame.api.board.field.Field} to another.
 */
public interface LookBehavior {

    /**
     * Check if a look from {@code position} to {@code target} is possible. It is possible, that a {@link
     * at.ahammer.boardgame.api.object.GameObject} or {@link at.ahammer.boardgame.api.subject.GameSubject} blocks the
     * look.
     *
     * @param position the {@link at.ahammer.boardgame.api.board.field.Field} where to look starts
     * @param target   the {@link at.ahammer.boardgame.api.board.field.Field} where the look ends
     * @return {@code true} if the look is possible
     */
    boolean canLook(Field position, Field target);

    /**
     * Get a set of all {@link at.ahammer.boardgame.api.board.field.Field}s that can be looked to.
     *
     * @param position the {@link at.ahammer.boardgame.api.board.field.Field} where to look starts
     * @return a set of {@link at.ahammer.boardgame.api.board.field.Field}s that can be looked to
     */
    Set<Field> getLookableFields(Field position);

    /**
     * Check if the current {@link at.ahammer.boardgame.api.behavior.look.LookBehavior} can be used on a {@link
     * at.ahammer.boardgame.api.board.Layout}.
     *
     * @return {@code true} if the current {@link at.ahammer.boardgame.api.behavior.look.LookBehavior} can be used on a
     * {@link at.ahammer.boardgame.api.board.Layout}
     */
    boolean canBeUsedOn(Layout layout);
}
