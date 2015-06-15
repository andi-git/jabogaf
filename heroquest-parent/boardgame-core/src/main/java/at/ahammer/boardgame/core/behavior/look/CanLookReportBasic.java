package at.ahammer.boardgame.core.behavior.look;

import at.ahammer.boardgame.api.behavior.look.CanLookReport;
import at.ahammer.boardgame.api.behavior.look.LookBlock;
import at.ahammer.boardgame.api.cdi.GameContextBean;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CanLookReportBasic implements CanLookReport {

    private final Set<GameContextBean> lookBlocks = new HashSet<>();

    @Override
    public void addLookBlock(GameContextBean gameContextBean) {
        lookBlocks.add(gameContextBean);
    }

    @Override
    public void addLookBlock(Set<GameContextBean> gameContextBeans) {
        lookBlocks.addAll(gameContextBeans);
    }

    @Override
    public boolean isPossible() {
        return !isBlocked();
    }

    @Override
    public boolean isBlocked() {
        return !lookBlocks.isEmpty();
    }

    @Override
    public Set<GameContextBean> lookBlocks() {
        return Collections.unmodifiableSet(lookBlocks);
    }
}