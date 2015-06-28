package org.jabogaf.common.board.layout.grid.log;

import org.jabogaf.api.board.field.FieldGroup;
import org.jabogaf.util.string.StringUtil;

import javax.enterprise.context.ApplicationScoped;

/**
 * A line for a {@link org.jabogaf.api.board.field.FieldGroup} where the {@link
 * org.jabogaf.api.board.field.Field} is located.
 */
@ApplicationScoped
public class FieldLineFieldGroup extends FieldLine<FieldLineFieldGroup.Representation> {

    @Override
    public int rank() {
        return 30;
    }

    @Override
    public boolean isLast(FactoryForFieldLines.State state) {
        return isPreLastElement(state) || areAllFieldGroupsAlreadyAdded(state);
    }

    @Override
    public FieldLineFieldGroup.Representation create(FactoryForFieldLines.State state) {
        return new FieldLineFieldGroup.Representation(getNextFieldGroup(state), state.getStringUtil());
    }

    @Override
    public boolean atLeastOnce() {
        return false;
    }

    private boolean areAllFieldGroupsAlreadyAdded(FactoryForFieldLines.State state) {
        return getAlreadyExistingLineGroupElements(state) == state.getField().getFieldsGroups().size();
    }

    private int getAlreadyExistingLineGroupElements(FactoryForFieldLines.State state) {
        return (int) countAlreadyExistingFieldLineElements(state, FieldLineFieldGroup.Representation.class);
    }

    private FieldGroup getNextFieldGroup(FactoryForFieldLines.State state) {
        return state.getField().getFieldsGroups().get(getAlreadyExistingLineGroupElements(state));
    }

    public static class Representation extends FieldLine.Representation {

        private final FieldGroup fieldGroup;

        public Representation(FieldGroup fieldGroup, StringUtil stringUtil) {
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
    }
}