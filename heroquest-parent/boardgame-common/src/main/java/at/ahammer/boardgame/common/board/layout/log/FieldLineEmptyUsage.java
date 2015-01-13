package at.ahammer.boardgame.common.board.layout.log;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FieldLineEmptyUsage extends FieldLineUsage<FieldLineEmpty> {

    @Override
    public int rank() {
        return 40;
    }

    @Override
    public boolean isLast(FieldLineFactory.State state) {
        return state.getFieldLines().size() == getMaxHeight() - 1;
    }

    @Override
    public FieldLineEmpty create(FieldLineFactory.State state) {
        return new FieldLineEmpty(state.getStringUtil());
    }

    @Override
    public boolean atLeastOnce() {
        return false;
    }
}
