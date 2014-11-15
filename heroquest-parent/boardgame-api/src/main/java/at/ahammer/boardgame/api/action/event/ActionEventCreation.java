package at.ahammer.boardgame.api.action.event;

/**
 * The strategy to create an {@link ActionEvent}.
 *
 * @param <T> the concrete type (subtype of an {@link ActionEvent})
 */
@FunctionalInterface
public interface ActionEventCreation<T extends ActionEvent> {

    /**
     * Create a new {@link ActionEvent}
     *
     * @return the new {@link ActionEvent}
     */
    T create();
}
