package at.ahammer.boardgame.behavior.look;

import at.ahammer.boardgame.object.field.Field;

/**
 * The strategy of a look from one {@link at.ahammer.boardgame.object.field.Field} to another,
 * <p/>
 * With {@link #canLook(at.ahammer.boardgame.object.field.Field, at.ahammer.boardgame.object.field.Field)} it can be
 * checked if it is possible to look from one {@link at.ahammer.boardgame.object.field.Field} to another.
 */
public interface LookBehavior {

    /**
     * Check if a look from {@code position} to {@code target} is possible. It is possible, that a {@link
     * at.ahammer.boardgame.object.GameObject} or {@link at.ahammer.boardgame.subject.GameSubject} blocks the look.
     *
     * @param position the {@link at.ahammer.boardgame.object.field.Field} where to look starts
     * @param target   the {@link at.ahammer.boardgame.object.field.Field} where the look ends
     * @return {@code true} if the look is possible
     */
    boolean canLook(Field position, Field target);

}
