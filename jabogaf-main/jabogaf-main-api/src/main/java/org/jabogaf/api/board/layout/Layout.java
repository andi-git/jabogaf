package org.jabogaf.api.board.layout;

import org.jabogaf.api.behavior.look.LookPath;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.api.board.field.FieldGroup;
import org.jabogaf.api.gamecontext.GameContextBean;
import org.jabogaf.api.object.GameObject;
import org.jabogaf.api.subject.GameSubject;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

/**
 * All the {@link Field}s of a board are arranged by a concrete layout. So the layout defines the available {@link
 * Field}s, how the {@link Field}s are connected via {@link FieldConnection}s and grouped via {@link FieldGroup}.
 */
public interface Layout extends GameContextBean<Layout> {

    void initAfterBoard();

    /**
     * Get all {@link Field}s of this {@link Layout}
     *
     * @return all {@link Field}s of this {@link Layout}
     */
    Set<Field> getFields();

    /**
     * Get all {@link Field}s as {@link Stream}.
     *
     * @return the {@link Field}s as {@link Stream}
     */
    Stream<Field> getFieldsAsStream();

    Set<FieldConnection> getFieldConnections();

    Set<FieldGroup> getFieldGroups();

    /**
     * @see FunctionIsConnected#isConnected(Set, Field, Field)
     */
    boolean isConnected(Field source, Field target);

    /**
     * @see FunctionGetConnection#getConnection(Set, Field, Field)
     */
    Optional<FieldConnection> getConnection(Field source, Field target);

    /**
     * @see FunctionGetAllGameObjectsOf#getAllGameObjectsOf(Set, Field, Field)
     */
    Set<GameObject> getAllGameObjectsOnFieldConnection(Field leftHand, Field rightHand);

    /**
     * Get a {@link Set} of all {@link FieldGroup}s where the assigned {@link Field} is located.
     *
     * @param field the {@link Field} to check
     * @return a {@link Set} of all {@link FieldGroup}s where the assigned {@link Field} is located
     */
    Set<FieldGroup> getFieldsGroupsFor(Field field);

    /**
     * Get all {@link FieldConnection}s where the {@link Field} is included.
     *
     * @param field the {@link Field} to get all {@link FieldConnection}s of
     * @return all {@link FieldConnection}s where the {@link Field} is included
     */
    Set<FieldConnection> getFieldConnections(Field field);

    /**
     * Get all connected {@link Field} of an assigned {@link Field}.
     *
     * @param field the {@link Field} to get all connected {@link Field}s of
     * @return all connected {@link Field} of an assigned {@link Field}
     */
    Set<Field> getConnectedFields(Field field);

    /**
     * Get the {@link LookPath} from one {@link Field} to another {@link Field}.
     *
     * @param fieldFrom from one {@link Field}
     * @param fieldTo   to another {@link Field}
     * @return the {@link LookPath} from one {@link Field} to another {@link Field}
     */
    Optional<LookPath> getLookPath(Field fieldFrom, Field fieldTo);

    /**
     * Get all available {@link LookPath}s.
     *
     * @return all available {@link LookPath}s
     */
    Map<KeyTwoFields, LookPath> getLookPaths();

    /**
     * Get all {@link LayoutActionImpact}s ({@link GameObject}s, {@link GameSubject}) that impacts the connection
     * between two {@link Field}s.
     *
     * @param fieldFrom from one {@link Field}
     * @param fieldTo   to another {@link Field}
     * @return all {@link LayoutActionImpact}s ({@link GameObject}s, {@link GameSubject}) that impacts the connection
     * between two {@link Field}s
     */
    List<LayoutActionImpact<?, ?>> getAllLayoutActionImpacts(Field fieldFrom, Field fieldTo);
}
