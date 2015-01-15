package at.ahammer.boardgame.common.board.layout.grid.log;

/**
 * This class knows, when to use a concrete {@link FieldLine} and is a
 * factory for this concrete class.
 *
 * @param <T> the concrete type of {@link FieldLine}
 */
public abstract class FieldLineUsage<T extends FieldLine> {

    /**
     * To have the right order of {@link FieldLine}s, this method is used.
     *
     * @return the rank (position) of the current {@link FieldLine}
     */
    public abstract int rank();

    /**
     * Check if it was the last occurrence of {@link FieldLine} in the
     * {@link FieldLineUsage}.
     *
     * @param state the current {@link FieldLineFactory.State}
     * @return {@code true} if the last occurrence was reached
     */
    public abstract boolean isLast(FieldLineFactory.State state);

    /**
     * Factory-method to create a new concrete {@link FieldLine} of type
     * T.
     *
     * @param state the current {@link FieldLineFactory.State}
     * @return a new concrete {@link FieldLine} of type T
     */
    public abstract T create(FieldLineFactory.State state);

    /**
     * Check if at least one occurrence of the concrete {@link FieldLine}
     * should be used.
     *
     * @return {@code true}, if at least one occurrence of the concrete {@link FieldLine}
     * should be used
     */
    public abstract boolean atLeastOnce();

    protected int getMaxHeight() {
        return FieldLine.HEIGHT;
    }

    protected boolean isPreLastElement(FieldLineFactory.State state) {
        return state.getFieldLines().size() == getMaxHeight() - 1;
    }

    protected<T extends FieldLine> long countAlreadyExistingFieldFieldGroups(FieldLineFactory.State state, Class<T> type) {
        return state.getFieldLines().stream().filter(fl -> type.isAssignableFrom(fl.getClass())).count();
    }

}
