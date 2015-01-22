package at.ahammer.boardgame.api.cdi;

/**
 * This is the superclass for all new instantiated beans within the {@link GameScoped}. This class only provides the
 * method getId() as abstract method and it's not an obligation to use this class as superclass.
 */
public interface GameContextBean extends Comparable<GameContextBean> {

    /**
     * Get the id of the {@link at.ahammer.boardgame.api.cdi.GameContextBean} in the cdi-context {@link
     * at.ahammer.boardgame.api.cdi.GameScoped}.
     *
     * @return the id of the {@link at.ahammer.boardgame.api.cdi.GameContextBean} in the cdi-context {@link
     * at.ahammer.boardgame.api.cdi.GameScoped}
     */
    String getId();
}
