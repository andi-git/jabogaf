package org.jabogaf.api.board.field;

import org.jabogaf.api.gamecontext.GameContextBean;
import org.jabogaf.api.object.GameObject;

import java.util.Set;

/**
 * Represents a connection between two {@link Field}. On this connection there can be multiple {@link
 * GameObject}s which are the behavior of the connection.
 */
public interface FieldConnection extends GameContextBean, ContainsGameObjects {

    /**
     * Check if the current {@link FieldConnection} connects the two assigned {@link Field}s
     *
     * @param field1 one {@link Field}
     * @param field2 another {@link Field}
     * @return {@code true} if the {@link FieldConnection} connects the 2 assigned {@link Field}s
     */
    boolean connects(Field field1, Field field2);

    void addObjectOnConnection(GameObject fieldConnectionObject);

    void addObjectOnConnection(GameObject... fieldConnectionObject);

    void clearObjectsOnConnection();

    Field getRightHand();

    Field getLeftHand();

    boolean contains(Field field);
}
