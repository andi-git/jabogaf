package org.jabogaf.core.gamecontext;

import org.jabogaf.api.gamecontext.FireEvent;
import org.jabogaf.api.gamecontext.GameContextBeanWithState;

public abstract class GameContextBeanWithStateBasic<T extends GameContextBeanWithState> extends GameContextBeanBasic<T> implements GameContextBeanWithState<T> {

    public GameContextBeanWithStateBasic() {
        super(FireEvent.Default);
    }

    public GameContextBeanWithStateBasic(FireEvent fireEvent) {
        super(fireEvent);
    }

    public GameContextBeanWithStateBasic(String id) {
        super(id, FireEvent.Default);
    }

    public GameContextBeanWithStateBasic(String id, FireEvent fireEvent) {
        super(id, fireEvent);
    }
}
