package at.ahammer.boardgame.core.cdi.bean;

import at.ahammer.boardgame.api.cdi.GameScoped;

@GameScoped
@SimpleQualifier
public class BeanWithGameScopedAndQualifier extends BeanWithGameScoped {

    @Override
    public String getString() {
        return "qualifier";
    }
}
