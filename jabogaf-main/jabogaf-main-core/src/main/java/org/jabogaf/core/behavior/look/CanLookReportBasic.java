package org.jabogaf.core.behavior.look;

import org.jabogaf.api.behavior.look.CanLookReport;
import org.jabogaf.api.gamecontext.GameContextBean;

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