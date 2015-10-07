package org.jabogaf.api.state;

import org.jabogaf.util.copy.CopyProperties;
import org.jabogaf.util.serialize.JSON;
import org.jabogaf.util.serialize.Serializer;

import javax.inject.Inject;

/**
 * The basic class for the state of the game. When getting all instances of this type at runtime, the complete
 * game-state is available, e.g. to persist.
 * <p/>
 * When a method is called to mutate a value, an event {@code GameStateChanged} is fired (via {@link
 * SetterFiresGameStateChanged}).
 * <p/>
 * So the attributes (values) of a {@code GameContextBean} are hold in a separate cdi-bean. This is necessary, because a
 * {@code GameContextBean} is not a cdi-bean, so some cdi-interceptors like interceptors (e.g. the {@link
 * SetterFiresGameStateChanged}) are not working in the {@code GameContextBean}.
 */
@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
@SetterFiresGameStateChanged
public abstract class GameState<T> {

    @Inject
    @JSON
    private Serializer serializer;

    @Inject
    private CopyProperties copyProperties;

    public String serialize() {
        return serializer.serialize(this);
    }

    public void deserialize(String string) {
        copyProperties.copy(serializer.deserialize(string, getClass()), this);
    }

    public abstract Class<T> classOfContainingBean();
}
