package org.jabogaf.core.gamecontext;

import org.jabogaf.api.gamecontext.GameScoped;
import org.jabogaf.util.log.SLF4J;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.lang.annotation.Annotation;

/**
 * This class represents a CDI-context for a game. All components with the annotation {@link GameScoped} will be
 * unique in this context.
 *
 * @author Andreas
 */
@Singleton
public class GameContext implements Context {

    private static final Logger log = LoggerFactory.getLogger(GameContext.class);

    @Override
    public <T> T get(Contextual<T> contextual) {
        return get(contextual, null);
    }

    @Override
    public <T> T get(Contextual<T> contextual, CreationalContext<T> creationalContext) {
        if (!(contextual instanceof Bean)) {
            throw new RuntimeException("contextual must be an instance of " + Bean.class + " but is " + contextual);
        }
        Bean<T> bean = (Bean<T>) contextual;
        if (getGameContextCache().getCurrentGameContextInstance().isAvailableInCreationalContext(bean, creationalContext)) {
            // bean is already available in context -> return
            return getGameContextCache().getCurrentGameContextInstance().getFromCreationalContext(bean, creationalContext);
        }
        if (creationalContext == null) {
            // creationalContext is null -> return null
            return null;
        } else {
            // create and return bean
            return getGameContextCache().getCurrentGameContextInstance().addToCreationalContext(bean, creationalContext);
        }
    }

    @Override
    public Class<? extends Annotation> getScope() {
        return GameScoped.class;
    }

    @Override
    public boolean isActive() {
        return true;
    }

    private GameContextCache getGameContextCache() {
        return new GameContextCache();
    }
}
