package at.ahammer.boardgame.cdi;

import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by ahammer on 06.08.2014.
 */
public class GameContextInstance {

    private final UUID id;

    private final Map<Contextual<?>, BeanInstance<?>> contexttualMap = new HashMap<>();

    private Instant lastAccess;

    public GameContextInstance(UUID id) {
        this.id = id;
        this.lastAccess = Instant.now();
    }

    public UUID getId() {
        return id;
    }

    public <T> BeanInstance<T> get(Contextual<T> contextual) {
        this.lastAccess = Instant.now();
        return (BeanInstance<T>) contexttualMap.get(contextual);
    }

    public <T> T addBeanInstance(Contextual<T> contextual, CreationalContext<T> creationalContext) {
        BeanInstance<T> beanInstance = new BeanInstance<T>(contextual, creationalContext);
        contexttualMap.put(contextual, beanInstance);
        return beanInstance.get();
    }

    public Instant getLastAccess() {
        return lastAccess;
    }
}
