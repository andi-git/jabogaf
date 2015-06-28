package org.jabogaf.api.board.layout.log;

import org.jabogaf.api.board.layout.Layout;

/**
 * Every implementation of this class can convert a concrete {@link Layout} to a {@link String}. To use this class, call
 * {@link LayoutLoggerManager#toString(Layout, LayoutLoggerParameter)} with the concrete {@link Layout}.
 *
 * @param <LAYOUT> the concrete type of the {@link Layout}
 * @param <PARA> the concrete type of the {@link LayoutLoggerParameter}
 */
public interface LayoutLogger<LAYOUT extends Layout, PARA extends LayoutLoggerParameter> {

    /**
     * Check if the current implementation of {@link LayoutLogger} can handle the concrete {@link Layout}.
     *
     * @param layout the concrete {@link Layout}
     * @return {@code true}, if it can handle the current {@link Layout}
     */
    boolean canHandle(Layout layout);

    /**
     * Get the generic type as {@link Class}.
     *
     * @return the generic type as {@link Class}
     */
    Class genericType();

    /**
     * Convert the concrete {@link Layout} to a {@link String}.
     *
     * @param layout    the concrete {@link Layout}
     * @param parameter the {@link LayoutLoggerParameter} to print the layout
     * @return a {@link String} representing the {@link Layout}
     */
    String toString(LAYOUT layout, PARA parameter);
}
