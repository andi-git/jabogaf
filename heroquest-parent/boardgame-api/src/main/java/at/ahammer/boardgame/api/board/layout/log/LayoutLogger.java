package at.ahammer.boardgame.api.board.layout.log;

import at.ahammer.boardgame.api.board.layout.Layout;

/**
 * Every implementation of this class can convert a concrete {@link at.ahammer.boardgame.api.board.layout.Layout} to a
 * {@link String}. To use this class, call {@link LayoutLoggerManager#toString(at.ahammer.boardgame.api.board.layout.Layout)}
 * with the concrete {@link at.ahammer.boardgame.api.board.layout.Layout}.
 *
 * @param <T> the concrete type of the {@link at.ahammer.boardgame.api.board.layout.Layout}
 */
public interface LayoutLogger<T extends Layout> {

    /**
     * Check if the current implementation of {@link LayoutLogger} can
     * handle the concrete {@link at.ahammer.boardgame.api.board.layout.Layout}.
     *
     * @param layout - the concrete {@link at.ahammer.boardgame.api.board.layout.Layout}
     * @return {@code true}, if it can handle the current {@link at.ahammer.boardgame.api.board.layout.Layout}
     */
    boolean canHandle(Layout layout);

    /**
     * Get the generic type as {@link java.lang.Class}.
     *
     * @return the generic type as {@link java.lang.Class}
     */
    Class genericType();

    /**
     * Convert the concrete {@link at.ahammer.boardgame.api.board.layout.Layout} to a {@link java.lang.String}.
     *
     * @param layout - the concrete {@link at.ahammer.boardgame.api.board.layout.Layout}
     * @return a {@link java.lang.String} representing the {@link at.ahammer.boardgame.api.board.layout.Layout}
     */
    String toString(T layout);
}
