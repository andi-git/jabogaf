package at.ahammer.boardgame.common.board.layout.grid.log;

import at.ahammer.boardgame.api.cdi.GameContextBean;
import at.ahammer.boardgame.util.string.StringUtil;

import javax.enterprise.context.ApplicationScoped;

/**
 * A line with the id of a concrete {@link at.ahammer.boardgame.api.cdi.GameContextBean}.
 */
@ApplicationScoped
public class FieldLineGameContextBean extends FieldLine<FieldLineGameContextBean.Representation> {

    @Override
    public int rank() {
        return 50;
    }

    @Override
    public boolean isLast(FactoryForFieldLines.State state) {
        return isPreLastElement(state) || areAllGameContextBeansAlreadyAdded(state);
    }

    @Override
    public FieldLineGameContextBean.Representation create(FactoryForFieldLines.State state) {
        return new FieldLineGameContextBean.Representation(getNextGameContextBean(state), state.getStringUtil());
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
        return state.getFieldLineRepresentations().stream().filter(f -> f instanceof FieldLineGameContextBean.Representation).count();
    }

    public static class Representation extends FieldLine.Representation {

        private final GameContextBean gameContextBean;

        public Representation(GameContextBean gameContextBean, StringUtil stringUtil) {
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
}
