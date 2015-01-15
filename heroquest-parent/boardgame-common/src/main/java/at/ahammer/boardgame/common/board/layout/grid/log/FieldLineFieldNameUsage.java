package at.ahammer.boardgame.common.board.layout.grid.log;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FieldLineFieldNameUsage extends FieldLineUsage<FieldLineFieldName> {

    @Override
    public int rank() {
        return 20;
    }

    @Override
    public boolean isLast(FieldLineFactory.State state) {
        return true;
    }

    @Override
    public FieldLineFieldName create(FieldLineFactory.State state) {
        return new FieldLineFieldName(state.getField(), state.getStringUtil());
    }

    @Override
    public boolean atLeastOnce() {
        return true;
    }
}
