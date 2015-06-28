package org.jabogaf.core.board.layout.log;

import org.jabogaf.api.board.layout.Layout;
import org.jabogaf.api.board.layout.log.LayoutLogger;
import org.jabogaf.api.board.layout.log.LayoutLoggerManager;
import org.jabogaf.api.board.layout.log.LayoutLoggerParameter;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

@ApplicationScoped
public class LayoutLoggerManagerBasic implements LayoutLoggerManager {

    @Inject
    @Any
    private Instance<LayoutLogger<? extends Layout, ? extends LayoutLoggerParameter>> layoutLoggers;

    @Override
    public String toString(Layout layout, LayoutLoggerParameter parameter) {
        LayoutLogger layoutLogger = getLayoutLogger(layout);
        return layoutLogger != null ? layoutLogger.toString(layout, parameter) : "no LayoutLogger for " + layout.getClass() + " available";
    }

    private LayoutLogger getLayoutLogger(Layout layout) {
        for(LayoutLogger layoutLogger : layoutLoggers) {
            if (layoutLogger.canHandle(layout)) {
                return layoutLogger;
            }
        }
        return null;
    }
}
