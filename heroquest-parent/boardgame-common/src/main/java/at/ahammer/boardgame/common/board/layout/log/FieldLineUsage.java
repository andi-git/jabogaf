package at.ahammer.boardgame.common.board.layout.log;

import at.ahammer.boardgame.api.board.field.Field;

public abstract class FieldLineUsage<T extends FieldLine> {

    public abstract boolean canBeUsedFor(int line);

    public abstract T create(Field field, int line);

    public abstract boolean isDefault();

    protected int getMaxWidth() {
        return GridLayoutLogger.WIDTH;
    }

    protected int getMaxHeight() {
        return GridLayoutLogger.HEIGHT;
    }

    protected int getMaxInnerWidth() {
        return getMaxWidth() - 2;
    }
}
