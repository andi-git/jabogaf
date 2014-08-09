package at.ahammer.boardgame.cdi;

import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;

/**
 * Representation of a concrete {@link javax.enterprise.inject.spi.Bean}.
 */
public class BeanInstance<T> {

    private final Contextual<T> contextual;

    private final CreationalContext<T> creationalContext;

    private final T instance;

    public BeanInstance(Contextual<T> contextual, CreationalContext<T> creationalContext) {
        this(contextual, creationalContext, contextual.create(creationalContext));
    }

    public BeanInstance(Contextual<T> contextual, CreationalContext<T> creationalContext, T instance) {
        this.contextual = contextual;
        this.creationalContext = creationalContext;
        this.instance = instance;
    }

    public void destroy() {
        this.contextual.destroy(this.instance, this.creationalContext);
    }

    public T get() {
        return this.instance;
    }

    protected Contextual<T> getContextual() {
        return this.contextual;
    }

    protected CreationalContext<T> getCreationalContext() {
        return this.creationalContext;
    }

}
