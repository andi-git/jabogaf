package at.ahammer.boardgame.common.board.layout.log;

import at.ahammer.boardgame.api.board.field.Field;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FieldLineFirstUsage extends FieldLineUsage<FieldLineFirst> {

    @Override
    public boolean canBeUsedFor(int line) {
        return line == 0;
    }

    @Override
    public FieldLineFirst create(Field field, int line) {
        return new FieldLineFirst(field, line);
    }

    @Override
    public boolean isDefault() {
        return false;
    }
}
