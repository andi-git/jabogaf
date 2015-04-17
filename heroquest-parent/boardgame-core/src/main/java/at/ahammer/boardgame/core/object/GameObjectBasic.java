package at.ahammer.boardgame.core.object;

import at.ahammer.boardgame.api.cdi.GameContextBean;
import at.ahammer.boardgame.api.object.GameObject;
import at.ahammer.boardgame.core.cdi.GameContextBeanBasic;
import at.ahammer.boardgame.core.state.GameState;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public class GameObjectBasic extends GameContextBeanBasic implements GameObject {

    @Inject
    private State state;

    /**
     * Create a new {@link at.ahammer.boardgame.core.object.GameObjectBasic} with an id.
     *
     * @param id the id of the {@link at.ahammer.boardgame.core.object.GameObjectBasic}
     */
    public GameObjectBasic(String id) {
        super(id);
    }

    @Override
    public boolean isVisible() {
        return state.isVisible();
    }

    @Override
    public void setVisible(boolean visible) {
        state.setVisible(visible);
    }

    @Override
    public void detected() {
        state.setVisible(true);
    }

    @Dependent
    public static class State extends GameState {

        private boolean visible = false;

        public boolean isVisible() {
            return visible;
        }

        public void setVisible(boolean visible) {
            this.visible = visible;
        }
    }
}
