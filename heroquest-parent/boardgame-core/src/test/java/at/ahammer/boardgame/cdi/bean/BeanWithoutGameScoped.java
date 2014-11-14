package at.ahammer.boardgame.cdi.bean;

import javax.inject.Inject;

public class BeanWithoutGameScoped extends BeanWithoutGameScopedSuperclass {

    @Inject
    private BeanWithGameScoped beanWithGameScoped;

    public BeanWithGameScoped getBeanWithGameScoped() {
        return beanWithGameScoped;
    }

    public void setBeanWithGameScoped(BeanWithGameScoped beanWithGameScoped) {
        this.beanWithGameScoped = beanWithGameScoped;
    }
}
