package org.jabogaf.core.gamecontext.bean;

import org.jabogaf.api.gamecontext.GameScoped;

@GameScoped
@SimpleQualifier
public class BeanWithGameScopedAndQualifier extends BeanWithGameScoped {

    @Override
    public String getString() {
        return "qualifier";
    }
}
