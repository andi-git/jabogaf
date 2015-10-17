package org.jabogaf.api.board.layout;

import org.jabogaf.api.gamecontext.GameContextBean;
import org.jabogaf.api.resource.Resource;

/**
 * This summarizes all classes that have impact on actions performed on the layout, i.e. a move, a look,...
 */
public interface LayoutActionImpact<POSITION> extends GameContextBean {

    POSITION getPosition();

    Resource movementCost();
}
