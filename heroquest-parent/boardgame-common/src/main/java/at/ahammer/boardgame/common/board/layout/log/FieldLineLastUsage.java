package at.ahammer.boardgame.common.board.layout.log;

import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.cdi.GameContextBean;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

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
