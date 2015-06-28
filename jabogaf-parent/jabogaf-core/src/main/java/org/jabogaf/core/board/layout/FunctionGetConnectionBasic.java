package org.jabogaf.core.board.layout;

import org.jabogaf.api.board.layout.FunctionGetConnection;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.core.board.field.FieldConnectionNull;
import org.jabogaf.util.stream.OptionalDefault;

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
