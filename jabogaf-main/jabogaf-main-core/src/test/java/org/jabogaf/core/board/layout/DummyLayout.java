package org.jabogaf.core.board.layout;

import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class DummyLayout extends LayoutBasic {

    protected DummyLayout() {
        super("DummyLayout" + randomId(), new HashSet<>(), new HashSet<>(), new HashSet<>());
    }

    @Override
    public Stream<Field> getFieldsAsStream() {
        return null;
    }

    @Override
    public Set<FieldConnection> getLookConnections(Field position, Field target) {
        return null;
    }
}
