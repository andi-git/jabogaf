package at.ahammer.boardgame.core.board;

import at.ahammer.boardgame.api.board.Board;
import at.ahammer.boardgame.api.board.layout.Layout;
import at.ahammer.boardgame.api.cdi.GameContextBean;
import at.ahammer.boardgame.core.cdi.GameContextBeanBasic;

/**
 * The board of a game. The design is specified by the current {@link at.ahammer.boardgame.api.board.layout.Layout}.
 */
public class BoardBasic extends GameContextBeanBasic implements Board {

    private final Layout layout;

    /**
     * Create a new {@link at.ahammer.boardgame.core.board.BoardBasic}
     *
     * @param id     the id of the {@link at.ahammer.boardgame.core.board.BoardBasic}
     * @param layout the {@link at.ahammer.boardgame.api.board.layout.Layout} (design) of the board
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
