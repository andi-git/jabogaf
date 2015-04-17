package at.ahammer.boardgame.core.board.layout;

import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.field.FieldConnection;
import at.ahammer.boardgame.api.board.layout.Layout;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class DummyLayout extends LayoutBasic {

    protected DummyLayout() {
        super("DummyLayout" + randomId(), new HashSet<>(), new HashSet<>(), new HashSet<>());
    }

    @Override
    public Stream getFieldsAsStream() {
        return null;
    }

    @Override
    public Set<FieldConnection> getLookConnections(Field position, Field target) {
        return null;
    }
}
