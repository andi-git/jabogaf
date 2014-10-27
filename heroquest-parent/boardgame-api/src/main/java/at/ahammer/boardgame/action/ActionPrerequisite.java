package at.ahammer.boardgame.action;

/**
 * Encapsulates the code that check if all prerequisites are fulfilled before a {@link
 * at.ahammer.boardgame.action.GameAction} can be performed.
 * <p/>
 * If the prerequisites are not fulfilled, an {@link at.ahammer.boardgame.action.ActionNotPossibleException} will be
 * thrown.
 */
@FunctionalInterface
public interface ActionPrerequisite {

    /**
     * Check if all prerequisites are fulfilled.
     *
     * @throws ActionNotPossibleException if not all prerequisites are fulfilled
     */
    void checkPrerequisite() throws ActionNotPossibleException;
}
