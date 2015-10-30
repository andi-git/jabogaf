package org.jabogaf.api.board.layout;

import org.jabogaf.api.board.field.ContainsGameObjects;
import org.jabogaf.api.gamecontext.GameContextBean;
import org.jabogaf.api.gamecontext.GameContextBeanWithState;
import org.jabogaf.api.resource.Resource;

/**
 * This summarizes all classes that have impact on actions performed on the layout, i.e. a move, a look,...
 */
public interface LayoutActionImpact<T extends GameContextBeanWithState, POSITION extends ContainsGameObjects> extends GameContextBeanWithState<T> {

    POSITION getPosition();

    Resource movementCost();

    Resource lookCost();
}
