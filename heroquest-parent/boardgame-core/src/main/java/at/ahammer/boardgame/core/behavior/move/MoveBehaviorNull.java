package at.ahammer.boardgame.core.behavior.move;

import at.ahammer.boardgame.api.behavior.move.*;
import at.ahammer.boardgame.api.board.layout.Layout;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.cdi.GameScoped;
import at.ahammer.boardgame.api.resource.ResourceHolder;
import at.ahammer.boardgame.api.subject.SetterOfPosition;
import at.ahammer.boardgame.core.board.field.FieldNull;

import java.util.*;

@GameScoped
@MoveBehaviorType(MoveBehaviorNull.class)
public class MoveBehaviorNull extends MoveBehaviorBasic {

    @Override
    public Set<MoveBlock> getMoveBlocks() {
        return Collections.unmodifiableSet(new HashSet<>());
    }

}
