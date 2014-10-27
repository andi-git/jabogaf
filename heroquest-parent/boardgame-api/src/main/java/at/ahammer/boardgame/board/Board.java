package at.ahammer.boardgame.board;

import at.ahammer.boardgame.cdi.GameContextBean;

/**
 * The board of a game. The design is specified by the current {@link at.ahammer.boardgame.board.Layout}.
 */
public class Board extends GameContextBean {

    private final Layout layout;

    /**
     * Create a new {@link at.ahammer.boardgame.board.Board}
     *
     * @param id     the id of the {@link at.ahammer.boardgame.board.Board}
     * @param layout the {@link at.ahammer.boardgame.board.Layout} (design) of the board
     */
    public Board(String id, Layout layout) {
        super(id);
        this.layout = layout;
    }

    public Layout getLayout() {
        return layout;
    }
}
