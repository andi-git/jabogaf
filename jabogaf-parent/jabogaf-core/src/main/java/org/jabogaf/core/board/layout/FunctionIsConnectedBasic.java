package org.jabogaf.core.board.layout;

import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.api.board.layout.FunctionIsConnected;

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
