package org.jabogaf.api.board.field;

import org.jabogaf.api.object.GameObject;

import java.util.List;

/**
 * This component contains {@link GameObject}s.
 */
public interface ContainsGameObjects {

    /**
     * Get a {@link List} of all available {@link GameObject}s on the current {@link Field}. The {@link GameObject}s are
     * ordered by natural order.
     *
     * @return a {@link List} of {@link GameObject}s available on the current {@link Field} sorted by natural order.
     */
    List<GameObject> getGameObjects();
}
