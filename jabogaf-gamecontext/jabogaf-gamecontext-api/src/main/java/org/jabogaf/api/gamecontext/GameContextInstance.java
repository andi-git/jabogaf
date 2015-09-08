package org.jabogaf.api.gamecontext;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import java.lang.annotation.Annotation;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

public interface GameContextInstance {

    UUID getId();

    <T> T addToCreationalContext(Bean<T> bean, CreationalContext<T> creationalContext);

    <T> boolean isAvailableInCreationalContext(Bean<T> bean, CreationalContext<T> creationalContext);

    <T> T getFromCreationalContext(Bean<T> bean, CreationalContext<T> creationalContext);

    Instant getLastAccess();

    <T> T getFromDynamicContext(Class<T> type, Annotation... qualifiers);

    <T extends GameContextBean> T addGameContextBean(T bean);

    Set<GameContextBean> getGameContextBeans();

    <T extends GameContextBean> Set<T> getGameContextBeans(Class<T> type);

    GameContextBean getGameContextBean(String id);

    <T extends GameContextBean> T getGameContextBean(Class<T> type);

    <T> T getGameContextBean(Class<T> type, String id);
}
