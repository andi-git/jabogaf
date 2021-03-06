package org.jabogaf.core.gamecontext.bean;

import javax.inject.Inject;

public class BeanWithoutGameScopedSuperclass {

    @Inject
    @SimpleQualifier
    private BeanWithGameScoped beanWithGameScopedInSuperclass;

    public BeanWithGameScoped getBeanWithGameScopedInSuperclass() {
        return beanWithGameScopedInSuperclass;
    }

    public void setBeanWithGameScopedInSuperclass(BeanWithGameScoped beanWithGameScopedInSuperclass) {
        this.beanWithGameScopedInSuperclass = beanWithGameScopedInSuperclass;
    }
}
