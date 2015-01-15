package at.ahammer.boardgame.common.board.layout.grid.log;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FieldLineEmptyUsage extends FieldLineUsage<FieldLineEmpty> {

    @Override
    public int rank() {
        return 60;
    }

    @Override
    public boolean isLast(FieldLineFactory.State state) {
        return isPreLastElement(state);
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
