package org.jabogaf.core.object;

import org.jabogaf.api.object.GameObject;
import org.jabogaf.api.resource.Resource;
import org.jabogaf.core.cdi.GameContextBeanBasic;
import org.jabogaf.core.resource.MovePoint;
import org.jabogaf.api.state.GameState;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public abstract class GameObjectBasic<POSITION> extends GameContextBeanBasic implements GameObject<POSITION> {

    @Inject
    private State<POSITION> state;

    /**
     * Create a new {@link GameObjectBasic} with an id.
     *
     * @param id the id of the {@link GameObjectBasic}
     */
    public GameObjectBasic(String id) {
        this(id, null);
    }

    public GameObjectBasic(String id, POSITION position) {
        super(id);
        state.setPosition(position);
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

    @Override
    public POSITION getPosition() {
        return state.getPosition();
    }

    @Override
    public Resource movementCost() {
        return new MovePoint(0);
    }

    @Dependent
    public static class State<POSITION> extends GameState {

        private boolean visible = false;

        private POSITION position;

        public boolean isVisible() {
            return visible;
        }

        public void setVisible(boolean visible) {
            this.visible = visible;
        }

        public POSITION getPosition() {
            return position;
        }

        public void setPosition(POSITION position) {
            this.position = position;
        }

        @Override
        public Class classOfContainingBean() {
            return GameObjectBasic.class;
        }
    }
}
