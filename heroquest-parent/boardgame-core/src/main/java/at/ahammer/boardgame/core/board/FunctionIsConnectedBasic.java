package at.ahammer.boardgame.core.board;

import at.ahammer.boardgame.api.board.FunctionIsConnected;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.field.FieldConnection;

import javax.enterprise.context.ApplicationScoped;
import java.util.Set;

@ApplicationScoped
public class FunctionIsConnectedBasic implements FunctionIsConnected {

    @Override
    public boolean isConnected(Set<FieldConnection> fieldConnections, Field source, Field target) {
        return fieldConnections.stream().anyMatch(fc -> fc.connects(source, target));
    }
}
