package at.ahammer.boardgame.api.board.layout.log;

import at.ahammer.boardgame.api.board.layout.Layout;

/**
 * Helper class to convert a {@link at.ahammer.boardgame.api.board.layout.Layout} to a {@link java.lang.String}.
 */
public interface LayoutLoggerManager {

    /**
     * Convert a concrete {@link at.ahammer.boardgame.api.board.layout.Layout} to a {@link java.lang.String}. A concrete
     * {@link LayoutLogger} must be available that can handle this
     * {@link at.ahammer.boardgame.api.board.layout.Layout}.
     *
     * @param layout - the concrete {@link at.ahammer.boardgame.api.board.layout.Layout}
     * @return a {@link java.lang.String} representing the {@link at.ahammer.boardgame.api.board.layout.Layout}
     */
    String toString(Layout layout);
}
