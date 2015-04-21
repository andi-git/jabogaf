package at.ahammer.boardgame.api.board.layout.log;

import at.ahammer.boardgame.api.board.layout.Layout;

/**
 * Helper class to convert a {@link Layout} to a {@link String}.
 */
public interface LayoutLoggerManager {

    /**
     * Convert a concrete {@link Layout} to a {@link String}. A concrete {@link LayoutLogger} must be available that can
     * handle this {@link Layout}.
     *
     * @param layout    the concrete {@link Layout}
     * @param parameter the {@link LayoutLoggerParameter} to print the layout
     * @return a {@link String} representing the {@link Layout}
     */
    String toString(Layout layout, LayoutLoggerParameter parameter);
}
