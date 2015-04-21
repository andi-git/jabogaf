package at.ahammer.boardgame.common.board.layout.grid.log;

import at.ahammer.boardgame.api.cdi.GameContextBean;
import at.ahammer.boardgame.util.string.StringUtil;

import javax.enterprise.context.ApplicationScoped;

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

    @ApplicationScoped
    public static class Usage extends FieldLineUsage<FieldLineGameContextBean> {

        @Override
        public int rank() {
            return 50;
        }

        @Override
        public boolean isLast(FactoryForFieldLines.State state) {
            return isPreLastElement(state) || areAllGameContextBeansAlreadyAdded(state);
        }

        @Override
        public FieldLineGameContextBean create(FactoryForFieldLines.State state) {
            return new FieldLineGameContextBean(getNextGameContextBean(state), state.getStringUtil());
        }

        @Override
        public boolean atLeastOnce() {
            return false;
        }

        private boolean areAllGameContextBeansAlreadyAdded(FactoryForFieldLines.State state) {
            return countAlreadyExistingFieldLineGameContextBeans(state) == state.getGameContextBeans().size();
        }

        private GameContextBean getNextGameContextBean(FactoryForFieldLines.State state) {
            return state.getGameContextBeans().get((int) countAlreadyExistingFieldLineGameContextBeans(state));
        }

        private long countAlreadyExistingFieldLineGameContextBeans(FactoryForFieldLines.State state) {
            return state.getFieldLines().stream().filter(f -> f instanceof FieldLineGameContextBean).count();
        }
    }
}
