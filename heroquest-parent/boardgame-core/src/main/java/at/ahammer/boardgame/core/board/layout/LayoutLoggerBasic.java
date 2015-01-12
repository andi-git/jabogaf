package at.ahammer.boardgame.core.board.layout;

import at.ahammer.boardgame.api.board.layout.Layout;
import at.ahammer.boardgame.api.board.layout.log.AbstractLayoutLogger;
import at.ahammer.boardgame.api.board.layout.log.LayoutLogger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

@ApplicationScoped
public class LayoutLoggerBasic implements LayoutLogger {

    @Inject
    @Any
    private Instance<AbstractLayoutLogger<? extends Layout>> layoutLoggers;

    @Override
    public String toString(Layout layout) {
        AbstractLayoutLogger layoutLogger = getLayoutLogger(layout);
        return layoutLogger != null ? layoutLogger.toString(layout) : "no LayoutLogger for " + layout.getClass() + " available";
    }

    private AbstractLayoutLogger getLayoutLogger(Layout layout) {
        for(AbstractLayoutLogger abstractLayoutLogger : layoutLoggers) {
            if (abstractLayoutLogger.canHandle(layout)) {
                return abstractLayoutLogger;
            }
        }
        return null;
    }
}
