package org.jabogaf.core.gamecontext;

import org.jabogaf.api.gamecontext.GameContextBeanWithState;

public abstract class GameContextBeanWithStateBasic<T extends GameContextBeanWithState> extends GameContextBeanBasic<T> implements GameContextBeanWithState<T> {

    public GameContextBeanWithStateBasic() {
        super();
    }

    public GameContextBeanWithStateBasic(String id) {
        super(id);
    }
}
