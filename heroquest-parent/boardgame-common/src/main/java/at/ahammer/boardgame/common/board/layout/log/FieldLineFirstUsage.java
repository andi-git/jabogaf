package at.ahammer.boardgame.common.board.layout.log;

import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.cdi.GameContextBean;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

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
