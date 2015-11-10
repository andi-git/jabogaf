package org.jabogaf.api.board.layout;

import org.jabogaf.api.behavior.look.LookPath;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.api.board.field.FieldGroup;

import java.util.Map;
import java.util.Set;

/**
 * The strategy to create all data for a {@link Layout}
 */
public interface LayoutCreation {

    Set<Field> getFields();

    Set<FieldConnection> getFieldConnections();

    Set<FieldGroup> getFieldGroups();

    Map<KeyTwoFields, LookPath> getLookPaths();
}
