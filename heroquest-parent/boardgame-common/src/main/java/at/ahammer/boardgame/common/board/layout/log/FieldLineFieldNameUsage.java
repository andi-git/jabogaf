package at.ahammer.boardgame.common.board.layout.log;

import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.cdi.GameContextBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

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
