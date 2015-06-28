package org.jabogaf.core.cdi.bean;

import org.jabogaf.api.cdi.GameScoped;

@GameScoped
@SimpleQualifier
public class BeanWithGameScopedAndQualifier extends BeanWithGameScoped {

    @Override
    public String getString() {
        return "qualifier";
    }
}
