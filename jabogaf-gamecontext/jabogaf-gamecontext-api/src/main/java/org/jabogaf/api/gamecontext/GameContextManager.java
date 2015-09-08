package org.jabogaf.api.gamecontext;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

/**
 * This class provides some helper-methods for the game-context, e. g. add a new instantiated class to the context.
 */
public interface GameContextManager {

    /**
     * Calls {@link #add(GameContextBean, String)}.
     *
     * @param bean the bean to add
     * @param <T>  the type of the bean
     * @return the bean
     * @see #add(GameContextBean, String)
     */
    <T extends GameContextBean> T add(T bean);

    /**
     * Add a new instantiated bean to the game-context. All CDI-components (e.g. {@link Inject} will be resolved with
     * the beans from the current game-context via {@link #resolve(Object)}.
     * <p>
     * If the bean has a method named getId(), the resulting String will be used to store the bean in the context.
     * Otherwise the system-hash will be used as id.
     *
     * @param bean the bean to add
     * @param id   the id of the bean
     * @param <T>  the type of the bean
     * @return the bean
     * @see #resolve(Object)
     */
    <T extends GameContextBean> T add(T bean, String id);

    /**
     * All CDI-components (e.g. {@link Inject} will be resolved with the beans from the current game-context.
     *
     * @param bean the bean to resolve the CDI-Annotations
     * @param <T>  the type of the bean
     * @return the assigned bean with the resolved CDI-components
     */
    <T> T resolve(T bean);

    /**
     * Get a {@link GameContextBean} by type and id.
     *
     * @param type the {@link Class} of the bean
     * @param id   the id
     * @param <T>  the generic type of the bean
     * @return the {@link GameContextBean} as T
     */
    <T> T getGameContextBean(Class<T> type, String id);

    /**
     * Get all {@link GameContextBean}s
     *
     * @return all {@link GameContextBean}s
     */
    Set<GameContextBean> getGameContextBeans();

    /**
     * Get all {@link GameContextBean}s of a specific type.
     *
     * @param type a sub-type of {@link GameContextBean}
     * @param <T>  the sub-type of {@link GameContextBean}
     * @return all {@link GameContextBean}s of a specific type
     */
    <T extends GameContextBean> Set<T> getGameContextBeans(Class<T> type);

    /**
     * Get a {@link GameContextBean} by id.
     *
     * @param id the id
     * @return the {@link GameContextBean}
     */
    GameContextBean getGameContextBean(String id);

    /**
     * Get a {@link GameContextBean} by it's type.
     *
     * @param type the type of the bean to get
     * @return the {@link GameContextBean}
     */
    <T extends GameContextBean> T getGameContextBean(Class<T> type);

    /**
     * Fire an {@link javax.enterprise.event.Event} that the game state changed.
     *
     * @param gameContextBean - the current/new {@link GameContextBean}
     */
    <T extends GameContextBean> T fireGameStateChangedEvent(T gameContextBean);

    /**
     * The assigned code will run within a new {@link GameContext}. The context will be started automatically and will
     * be destroyed afterwards.
     *
     * @param runnable - the {@link RunInGameContext} which code must run within a {@link GameContext}
     * @return the result of the {@link RunInGameContext}
     */
    <T> T runInGameContext(BeanManager beanManager, RunInGameContext<T> runnable);

    /**
     * Start a new {@link GameContext} or reuse an existing one via the {@code gameContextId}. The code within the
     * {@code runnable} will be executed within the {@link GameContext}.
     * <p>
     * This method will throw {@link Throwable}, because for JUnit-Tests.
     *
     * @param gameContextId the id (a {@link UUID} to reuse an existing {@link GameContextInstance}
     * @param beanManager   the current {@link BeanManager}
     * @param runnable      the code run within the {@link GameContext}
     * @param <T>           the return-type
     * @return the id of the used {@link GameContextInstance}
     */
    <T> T runInGameContext(UUID gameContextId, BeanManager beanManager, RunInGameContext<T> runnable);

    UUID getId();

    <T> T addToCreationalContext(Bean<T> bean, CreationalContext<T> creationalContext);

    <T> boolean isAvailableInCreationalContext(Bean<T> bean, CreationalContext<T> creationalContext);

    <T> T getFromCreationalContext(Bean<T> bean, CreationalContext<T> creationalContext);

    Instant getLastAccess();

    <T> T getFromDynamicContext(Class<T> type, Annotation... qualifiers);

    <T extends GameContextBean> T addGameContextBean(T bean);
}
