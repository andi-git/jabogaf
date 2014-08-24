package at.ahammer.boardgame.board;

import at.ahammer.boardgame.cdi.NewInstanceInGameContext;

/**
 * Created by andreas on 8/23/14.
 */
public class Board extends NewInstanceInGameContext {

    private final Layout layout;

    public Board(String id, Layout layout) {
        super(id);
        this.layout = layout;
    }

    public Layout getLayout() {
        return layout;
    }
}
