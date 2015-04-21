package at.ahammer.boardgame.common.board.layout.grid.log;

import at.ahammer.boardgame.api.board.field.FieldGroup;
import at.ahammer.boardgame.util.string.StringUtil;

import javax.enterprise.context.ApplicationScoped;

/**
 * A line for a {@link at.ahammer.boardgame.api.board.field.FieldGroup} where the {@link
 * at.ahammer.boardgame.api.board.field.Field} is located.
 */
public class FieldLineFieldGroup extends FieldLine {

    private final FieldGroup fieldGroup;

    public FieldLineFieldGroup(FieldGroup fieldGroup, StringUtil stringUtil) {
        super(stringUtil);
        this.fieldGroup = fieldGroup;
    }

    @Override
    public String text() {
        return "*" + fieldGroup.getId();
    }

    @Override
    public char firstChar() {
        return '|';
    }

    @Override
    public char lastChar() {
        return '|';
    }

    @ApplicationScoped
    public static class Usage extends FieldLineUsage<FieldLineFieldGroup> {

        @Override
        public int rank() {
            return 30;
        }

        @Override
        public boolean isLast(FactoryForFieldLines.State state) {
            return isPreLastElement(state) || areAllFieldGroupsAlreadyAdded(state);
        }

        @Override
        public FieldLineFieldGroup create(FactoryForFieldLines.State state) {
            return new FieldLineFieldGroup(getNextFieldGroup(state), state.getStringUtil());
        }

        @Override
        public boolean atLeastOnce() {
            return false;
        }

        private boolean areAllFieldGroupsAlreadyAdded(FactoryForFieldLines.State state) {
            return getAlreadyExistingLineGroupElements(state) == state.getField().getFieldsGroups().size();
        }

        private int getAlreadyExistingLineGroupElements(FactoryForFieldLines.State state) {
            return (int) countAlreadyExistingFieldLineElements(state, FieldLineFieldGroup.class);
        }

        private FieldGroup getNextFieldGroup(FactoryForFieldLines.State state) {
            return state.getField().getFieldsGroups().get(getAlreadyExistingLineGroupElements(state));
        }
    }
}
