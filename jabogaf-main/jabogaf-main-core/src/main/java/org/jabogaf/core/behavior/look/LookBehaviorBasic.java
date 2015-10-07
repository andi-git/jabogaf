package org.jabogaf.core.behavior.look;

import org.jabogaf.api.behavior.look.CanLookReport;
import org.jabogaf.api.behavior.look.LookBehavior;
import org.jabogaf.api.behavior.look.Lookable;
import org.jabogaf.api.board.BoardManager;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.api.gamecontext.GameContextBean;

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
                    filter(this::isBlocker).
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
