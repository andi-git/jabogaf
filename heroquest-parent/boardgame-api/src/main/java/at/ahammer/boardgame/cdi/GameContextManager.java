package at.ahammer.boardgame.cdi;

/**
 * This class provides some helper-methods for the {@link at.ahammer.boardgame.cdi.GameScoped}, e. g. add a new
 * instantiated class to the context.
 */
public interface GameContextManager {

    /**
     * Add a new instantiated bean to the {@link at.ahammer.boardgame.cdi.GameScoped}. All CDI-components (e.g. {@link
     * javax.inject.Inject} will be resolved with the beans from the current {@link at.ahammer.boardgame.cdi.GameScoped}
     * via {@link #resolve(Object)}.
     * <p/>
     * If the bean has a method named getId(), the resulting String will be used to store the bean in the context.
     * Otherwise the system-hash will be used as id.
     *
     * @param bean the bean to add
     * @param <T>  the type of the bean
     * @return the bean
     * @see #resolve(Object)
     */
    <T> T add(T bean);

    /**
     * All CDI-components (e.g. {@link javax.inject.Inject} will be resolved with the beans from the current {@link
     * at.ahammer.boardgame.cdi.GameScoped}.
     *
     * @param bean the bean to resolve the CDI-Annotations
     * @param <T>  the type of the bean
     * @return the assigned bean with the resolved CDI-components
     */
    <T> T resolve(T bean);
}
