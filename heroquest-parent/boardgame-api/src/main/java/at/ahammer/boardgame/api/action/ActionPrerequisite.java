package at.ahammer.boardgame.api.action;

/**
 * Encapsulates the code that check if all prerequisites are fulfilled before a {@link
 * GameAction} can be performed.
 * <p/>
 * If the prerequisites are not fulfilled, an {@link ActionNotPossibleException} will be
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
