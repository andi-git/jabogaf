package at.ahammer.boardgame.common.board.layout.log;

/**
 * This class knows, when to use a concrete {@link at.ahammer.boardgame.common.board.layout.log.FieldLine} and is a
 * factory for this concrete class.
 *
 * @param <T> the concrete type of {@link at.ahammer.boardgame.common.board.layout.log.FieldLine}
 */
public abstract class FieldLineUsage<T extends FieldLine> {

    /**
     * To have the right order of {@link at.ahammer.boardgame.common.board.layout.log.FieldLine}s, this method is used.
     *
     * @return the rank (position) of the current {@link at.ahammer.boardgame.common.board.layout.log.FieldLine}
     */
    public abstract int rank();

    /**
     * Check if it was the last occurrence of {@link at.ahammer.boardgame.common.board.layout.log.FieldLine} in the
     * {@link at.ahammer.boardgame.common.board.layout.log.FieldLineUsage}.
     *
     * @param state the current {@link at.ahammer.boardgame.common.board.layout.log.FieldLineFactory.State}
     * @return {@code true} if the last occurrence was reached
     */
    public abstract boolean isLast(FieldLineFactory.State state);

    /**
     * Factory-method to create a new concrete {@link at.ahammer.boardgame.common.board.layout.log.FieldLine} of type
     * T.
     *
     * @param state the current {@link at.ahammer.boardgame.common.board.layout.log.FieldLineFactory.State}
     * @return a new concrete {@link at.ahammer.boardgame.common.board.layout.log.FieldLine} of type T
     */
    public abstract T create(FieldLineFactory.State state);

    /**
     * Check if at least one occurrence of the concrete {@link at.ahammer.boardgame.common.board.layout.log.FieldLine}
     * should be used.
     *
     * @return {@code true}, if at least one occurrence of the concrete {@link at.ahammer.boardgame.common.board.layout.log.FieldLine}
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
