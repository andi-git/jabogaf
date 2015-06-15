package at.ahammer.boardgame.api.behavior.look;

import at.ahammer.boardgame.api.behavior.move.MoveBlock;
import at.ahammer.boardgame.api.cdi.GameContextBean;
import at.ahammer.boardgame.api.resource.Resource;

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
