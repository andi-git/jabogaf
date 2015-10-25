package org.jabogaf.api.board.field;

import org.jabogaf.api.subject.GameSubject;

import java.util.List;

/**
 * This components contains {@link GameSubject}s.
 */
public interface ContainsGameSubjects {

    /**
     * Get a {@link List} of all available {@link GameSubject}s on the current {@link Field}. The {@link GameSubject}s
     * are ordered by natural order.
     *
     * @return a {@link List} of {@link GameSubject}s available on the current {@link Field} sorted by natural order.
     */
    List<GameSubject> getGameSubjects();
}
