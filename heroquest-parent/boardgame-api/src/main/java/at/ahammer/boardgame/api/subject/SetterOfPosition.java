package at.ahammer.boardgame.api.subject;

import at.ahammer.boardgame.api.artifact.Artifact;
import at.ahammer.boardgame.api.board.field.Field;

/**
 * The function to set the position ({@link at.ahammer.boardgame.api.board.field.Field}) of the {@link at.ahammer.boardgame.api.subject.GameSubject}.
 */
@FunctionalInterface
public interface SetterOfPosition {

    /**
     * Set the position ({@link at.ahammer.boardgame.api.board.field.Field}) of the {@link at.ahammer.boardgame.api.subject.GameSubject}.
     *
     * @param field the {@link at.ahammer.boardgame.api.board.field.Field} to set the position to
     */
    void setPosition(Field field);
}
