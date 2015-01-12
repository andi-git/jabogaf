package at.ahammer.boardgame.common.board.layout.log;

import at.ahammer.boardgame.api.board.field.Field;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FieldLineDefaultUsage extends FieldLineUsage<FieldLineDefault> {

    @Override
    public boolean canBeUsedFor(int line) {
        return line > 0 && line < getMaxHeight() - 1;
    }

    @Override
    public FieldLineDefault create(Field field, int line) {
        return new FieldLineDefault(field, line);
    }

    @Override
    public boolean isDefault() {
        return true;
    }
}
