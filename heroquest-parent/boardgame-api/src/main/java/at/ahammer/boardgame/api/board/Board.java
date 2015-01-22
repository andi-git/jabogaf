package at.ahammer.boardgame.api.board;

import at.ahammer.boardgame.api.board.layout.Layout;
import at.ahammer.boardgame.api.cdi.GameContextBean;

/**
 * The board of a game. The design is specified by the current {@link at.ahammer.boardgame.api.board.layout.Layout}.
 */
public interface Board extends GameContextBean {

    Layout getLayout();
}
