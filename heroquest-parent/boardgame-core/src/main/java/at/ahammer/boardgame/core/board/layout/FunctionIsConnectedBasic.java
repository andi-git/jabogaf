package at.ahammer.boardgame.core.board.layout;

import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.field.FieldConnection;
import at.ahammer.boardgame.api.board.layout.FunctionIsConnected;

import javax.enterprise.context.ApplicationScoped;
import java.util.Set;

@ApplicationScoped
public class FunctionIsConnectedBasic implements FunctionIsConnected {

    @Override
    public boolean isConnected(Set<FieldConnection> fieldConnections, Field source, Field target) {
        if (fieldConnections == null) {
            return false;
        }
        return fieldConnections.stream().anyMatch(fc -> fc.connects(source, target));
    }
}
