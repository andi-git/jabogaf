package at.ahammer.boardgame.core.behavior.look;

import at.ahammer.boardgame.api.behavior.look.CanLookReport;
import at.ahammer.boardgame.api.behavior.look.LookBehavior;
import at.ahammer.boardgame.api.behavior.look.LookBlock;
import at.ahammer.boardgame.api.behavior.look.Lookable;
import at.ahammer.boardgame.api.board.BoardManager;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.field.FieldConnection;
import at.ahammer.boardgame.api.board.field.FieldConnectionObject;
import at.ahammer.boardgame.api.cdi.GameContextBean;

import javax.inject.Inject;
import java.util.Set;

/**
 * Basic implementation of {@link LookBehavior}.
 */
@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public abstract class LookBehaviorBasic implements LookBehavior {

    @Inject
    private BoardManager boardManager;

    @Override
    public CanLookReport canLook(Lookable lookable, Field target) {
        CanLookReport canLookReport = new CanLookReportBasic();
        for (FieldConnection fieldConnection : boardManager.getBoard().getLayout().getLookConnections(lookable.getPosition(), target)) {
            fieldConnection.getObjectsOnConnection().stream().
                    filter(o -> isBlocker(o)).
                    forEach(canLookReport::addLookBlock);
            fieldConnection.getLeftHand().getGameSubjects();
        }
        return canLookReport;
    }

    private boolean isBlocker(GameContextBean gameContextBean) {
        return false;
    }

    @Override
    public Set<Field> getLookableFields(Lookable lookable) {
        return null;
    }
}
