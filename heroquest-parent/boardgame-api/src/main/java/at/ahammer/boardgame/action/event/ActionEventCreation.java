package at.ahammer.boardgame.action.event;

/**
 * The strategy to create an {@link at.ahammer.boardgame.action.event.ActionEvent}.
 *
 * @param <T> the concrete type (subtype of an {@link at.ahammer.boardgame.action.event.ActionEvent})
 */
@FunctionalInterface
public interface ActionEventCreation<T extends ActionEvent> {

    /**
     * Create a new {@link at.ahammer.boardgame.action.event.ActionEvent}
     *
     * @return the new {@link at.ahammer.boardgame.action.event.ActionEvent}
     */
    T create();
}
