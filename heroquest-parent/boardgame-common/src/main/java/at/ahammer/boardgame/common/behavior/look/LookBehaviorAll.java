package at.ahammer.boardgame.common.behavior.look;

import at.ahammer.boardgame.api.behavior.look.CanLookReport;
import at.ahammer.boardgame.api.behavior.look.LookBehavior;
import at.ahammer.boardgame.api.behavior.look.LookBehaviorType;
import at.ahammer.boardgame.api.behavior.look.Lookable;
import at.ahammer.boardgame.api.behavior.move.MoveBlock;
import at.ahammer.boardgame.api.board.BoardManager;
import at.ahammer.boardgame.api.board.layout.Layout;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.core.behavior.look.CanLookReportBasic;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

/**
 * This implementation of {@link at.ahammer.boardgame.api.behavior.look.LookBehavior} can look to every {@link
 * at.ahammer.boardgame.api.board.field.Field} of the {@link at.ahammer.boardgame.api.board.layout.Layout} and can be used
 * everywhere.
 */
@ApplicationScoped
@LookBehaviorType(LookBehaviorAll.class)
public class LookBehaviorAll implements LookBehavior {

    @Inject
    private BoardManager boardManager;

    @Override
    public CanLookReport canLook(Lookable lookable, Field target) {
        return new CanLookReportBasic();
    }

    @Override
    public Set<Field> getLookableFields(Lookable lookable) {
        return boardManager.getFields();
    }

    @Override
    public Set<MoveBlock> getLookBlocks() {
        return new HashSet<>();
    }
}
