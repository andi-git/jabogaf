package at.ahammer.boardgame.common.board.layout.grid.log;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FieldLineFirstUsage extends FieldLineUsage<FieldLineFirst> {

    @Override
    public int rank() {
        return 10;
    }

    @Override
    public boolean isLast(FieldLineFactory.State state) {
        return true;
    }

    @Override
    public FieldLineFirst create(FieldLineFactory.State state) {
        return new FieldLineFirst(state.getStringUtil());
    }

    @Override
    public boolean atLeastOnce() {
        return true;
    }
}
