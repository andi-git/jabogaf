package org.jabogaf.api.board;

import org.jabogaf.api.board.layout.Layout;
import org.jabogaf.api.gamecontext.GameContextBean;

/**
 * The board of a game. The design is specified by the current {@link Layout}.
 */
public interface Board extends GameContextBean {

    Layout getLayout();
}
