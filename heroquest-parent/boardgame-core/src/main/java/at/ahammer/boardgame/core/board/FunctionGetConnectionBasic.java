package at.ahammer.boardgame.core.board;

import at.ahammer.boardgame.api.board.FunctionGetConnection;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.field.FieldConnection;

import java.util.Set;

public class FunctionGetConnectionBasic implements FunctionGetConnection {

    @Override
    public FieldConnection getConnection(Set<FieldConnection> fieldConnections, Field source, Field target) {
        return fieldConnections.stream().filter(fc -> fc.connects(source, target)).findFirst().get();
    }
}
