package at.ahammer.boardgame.common.board.layout.log;

import at.ahammer.boardgame.api.board.field.FieldGroup;
import at.ahammer.boardgame.api.cdi.GameContextBean;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class FieldLineFieldGroupUsage extends FieldLineUsage<FieldLineFieldGroup> {

    @Override
    public int rank() {
        return 30;
    }

    @Override
    public boolean isLast(FieldLineFactory.State state) {
        if (isPreLastElement(state)) {
            return true;
        }
        if (countAlreadyExistingFieldFieldGroups(state, FieldLineFieldGroup.class) == state.getField().getFieldsGroups().size()) {
            return true;
        }
        return false;
    }

    @Override
    public FieldLineFieldGroup create(FieldLineFactory.State state) {
        return new FieldLineFieldGroup(getNextFieldGroup(state), state.getStringUtil());
    }

    @Override
    public boolean atLeastOnce() {
        return false;
    }

    private FieldGroup getNextFieldGroup(FieldLineFactory.State state) {
        return state.getField().getFieldsGroups().get((int) countAlreadyExistingFieldFieldGroups(state, FieldLineFieldGroup.class));
    }
}
