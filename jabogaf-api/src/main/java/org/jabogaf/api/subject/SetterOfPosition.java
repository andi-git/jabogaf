package org.jabogaf.api.subject;

import org.jabogaf.api.board.field.Field;

/**
 * The function to set the position ({@link Field}) of the {@link GameSubject}.
 */
@FunctionalInterface
public interface SetterOfPosition {

    /**
     * Set the position ({@link Field}) of the {@link GameSubject}.
     *
     * @param field the {@link Field} to set the position to
     */
    void setPosition(Field field);
}
