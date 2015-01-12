package at.ahammer.boardgame.common.board.layout.log;

import at.ahammer.boardgame.api.board.field.Field;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FieldLineLastUsage extends FieldLineUsage<FieldLineLast> {

    @Override
    public boolean canBeUsedFor(int line) {
        return line == getMaxHeight() - 1;
    }

    @Override
    public FieldLineLast create(Field field, int line) {
        return new FieldLineLast(field, line);
    }

    @Override
    public boolean isDefault() {
        return false;
    }
}
