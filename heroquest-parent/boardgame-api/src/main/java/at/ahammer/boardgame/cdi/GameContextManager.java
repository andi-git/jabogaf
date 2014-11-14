package at.ahammer.boardgame.cdi;

import javax.enterprise.inject.spi.BeanManager;

/**
 * This class provides some helper-methods for the {@link at.ahammer.boardgame.cdi.GameScoped}, e. g. add a new
 * instantiated class to the context.
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
     * Add a new instantiated bean to the {@link at.ahammer.boardgame.cdi.GameScoped}. All CDI-components (e.g. {@link
     * javax.inject.Inject} will be resolved with the beans from the current {@link at.ahammer.boardgame.cdi.GameScoped}
     * via {@link #resolve(Object)}.
     * <p/>
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
     * All CDI-components (e.g. {@link javax.inject.Inject} will be resolved with the beans from the current {@link
     * at.ahammer.boardgame.cdi.GameScoped}.
     *
     * @param bean the bean to resolve the CDI-Annotations
     * @param <T>  the type of the bean
     * @return the assigned bean with the resolved CDI-components
     */
    <T> T resolve(T bean);

    /**
     * Run a code within a new {@link at.ahammer.boardgame.cdi.GameScoped}.
     *
     * @param beanManager the {@link javax.enterprise.inject.spi.BeanManager}
     * @param runnable    the code to run
     * @param <T>         the type of the return-value
     * @return a return-value
     */
    <T> T runInGameContext(BeanManager beanManager, RunInGameContext<T> runnable) throws Throwable;
}
