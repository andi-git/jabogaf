package at.ahammer.boardgame.core.board.layout;

import at.ahammer.boardgame.api.board.layout.FunctionGetConnection;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.field.FieldConnection;
import at.ahammer.boardgame.core.board.field.FieldConnectionNull;
import at.ahammer.boardgame.util.stream.OptionalDefault;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Set;

@ApplicationScoped
public class FunctionGetConnectionBasic implements FunctionGetConnection {

    @Inject
    private OptionalDefault optionalDefault;

    @Override
    public FieldConnection getConnection(Set<FieldConnection> fieldConnections, Field source, Field target) {
        if (fieldConnections == null) {
            return new FieldConnectionNull();
        }
        return optionalDefault.defaultGet(fieldConnections.stream().filter(fc -> fc.connects(source, target)).findFirst(), new FieldConnectionNull());
    }
}
