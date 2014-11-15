package at.ahammer.boardgame.api.board;

import at.ahammer.boardgame.api.cdi.GameContextBean;

/**
 * The board of a game. The design is specified by the current {@link Layout}.
 */
public class Board extends GameContextBean {

    private final Layout layout;

    /**
     * Create a new {@link Board}
     *
     * @param id     the id of the {@link Board}
     * @param layout the {@link Layout} (design) of the board
     */
    public Board(String id, Layout layout) {
        super(id);
        this.layout = layout;
    }

    public Layout getLayout() {
        return layout;
    }
}
