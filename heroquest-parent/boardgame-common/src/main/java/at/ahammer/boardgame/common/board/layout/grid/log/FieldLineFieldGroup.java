package at.ahammer.boardgame.common.board.layout.grid.log;

import at.ahammer.boardgame.api.board.field.FieldGroup;
import at.ahammer.boardgame.util.string.StringUtil;

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
}
