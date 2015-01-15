package at.ahammer.boardgame.common.board.layout.grid.log;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FieldLineLastUsage extends FieldLineUsage<FieldLineLast> {

    @Override
    public int rank() {
        return 100;
    }

    @Override
    public boolean isLast(FieldLineFactory.State state) {
        return true;
    }

    @Override
    public FieldLineLast create(FieldLineFactory.State state) {
        return new FieldLineLast(state.getStringUtil());
    }

    @Override
    public boolean atLeastOnce() {
        return true;
    }
}
