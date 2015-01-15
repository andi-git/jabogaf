package at.ahammer.boardgame.common.board.layout.grid.log;

import at.ahammer.boardgame.api.cdi.GameContextBean;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FieldLineGameContextBeanUsage extends FieldLineUsage<FieldLineGameContextBean> {

    @Override
    public int rank() {
        return 50;
    }

    @Override
    public boolean isLast(FieldLineFactory.State state) {
        if (isPreLastElement(state)) {
            return true;
        }
        if (countAlreadyExistingFieldLineGameContextBeans(state) == state.getGameContextBeans().size()) {
            return true;
        }
        return false;
    }

    @Override
    public FieldLineGameContextBean create(FieldLineFactory.State state) {
        return new FieldLineGameContextBean(getNextGameContextBean(state), state.getStringUtil());
    }

    @Override
    public boolean atLeastOnce() {
        return false;
    }

    private GameContextBean getNextGameContextBean(FieldLineFactory.State state) {
        return state.getGameContextBeans().get((int) countAlreadyExistingFieldLineGameContextBeans(state));
    }

    private long countAlreadyExistingFieldLineGameContextBeans(FieldLineFactory.State state) {
        return state.getFieldLines().stream().filter(fl -> fl instanceof FieldLineGameContextBean).count();
    }
}
