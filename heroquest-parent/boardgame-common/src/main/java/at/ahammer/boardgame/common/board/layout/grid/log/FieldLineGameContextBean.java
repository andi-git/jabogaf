package at.ahammer.boardgame.common.board.layout.grid.log;

import at.ahammer.boardgame.api.cdi.GameContextBean;
import at.ahammer.boardgame.util.string.StringUtil;

/**
 * A line with the id of a concrete {@link at.ahammer.boardgame.api.cdi.GameContextBean}.
 */
public class FieldLineGameContextBean extends FieldLine {

    private final GameContextBean gameContextBean;

    public FieldLineGameContextBean(GameContextBean gameContextBean, StringUtil stringUtil) {
        super(stringUtil);
        this.gameContextBean = gameContextBean;
    }

    @Override
    public String text() {
        return ">" + gameContextBean.getId();
    }

    @Override
    public char firstChar() {
        return '|';
    }

    @Override
    public char lastChar() {
        return '|';
    }
}
