package org.jabogaf.api.behavior.look;

import org.jabogaf.api.gamecontext.GameContextBean;

import java.util.Set;

/**
 * Encapsulates all information if the look is possible.
 */
public interface CanLookReport {

    void addLookBlock(GameContextBean gameContextBean);

    void addLookBlock(Set<GameContextBean> gameContextBeans);

    boolean isPossible();

    boolean isBlocked();

    Set<GameContextBean> lookBlocks();
}
