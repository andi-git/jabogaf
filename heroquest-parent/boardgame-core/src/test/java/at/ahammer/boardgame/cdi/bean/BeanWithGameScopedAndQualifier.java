package at.ahammer.boardgame.cdi.bean;

import at.ahammer.boardgame.cdi.GameScoped;

@GameScoped
@SimpleQualifier
public class BeanWithGameScopedAndQualifier extends BeanWithGameScoped {

    @Override
    public String getString() {
        return "qualifier";
    }
}
