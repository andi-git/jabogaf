package org.jabogaf.core.board;

import org.jabogaf.api.board.Board;
import org.jabogaf.api.board.layout.Layout;
import org.jabogaf.core.cdi.GameContextBeanBasic;

/**
 * The board of a game. The design is specified by the current {@link org.jabogaf.api.board.layout.Layout}.
 */
public class BoardBasic extends GameContextBeanBasic implements Board {

    private final Layout layout;

    /**
     * Create a new {@link org.jabogaf.core.board.BoardBasic}
     *
     * @param id     the id of the {@link org.jabogaf.core.board.BoardBasic}
     * @param layout the {@link org.jabogaf.api.board.layout.Layout} (design) of the board
     */
    public BoardBasic(String id, Layout layout) {
        super(id);
        this.layout = layout;
    }

    @Override
    public Layout getLayout() {
        return layout;
    }
}
