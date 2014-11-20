package at.ahammer.boardgame.core.board;

import at.ahammer.boardgame.api.board.FunctionIsConnected;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.field.FieldConnection;

import javax.enterprise.context.ApplicationScoped;
import java.util.Set;

@ApplicationScoped
public class FunctionIsConnectedBasic implements at.ahammer.boardgame.api.board.FunctionIsConnected {

    @Override
    public boolean isConnected(Set<FieldConnection> fieldConnections, Field source, Field target) {
        if (fieldConnections == null) {
            return false;
        }
        return fieldConnections.stream().anyMatch(fc -> fc.connects(source, target));
    }
}
