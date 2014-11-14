package at.ahammer.boardgame.cdi.bean;

import at.ahammer.boardgame.cdi.GameContextBean;
import at.ahammer.boardgame.cdi.GameScoped;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Typed;
import javax.inject.Inject;

@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
@Typed()
public class MyGameContextBean extends GameContextBean {

    @Inject
    private BeanWithGameScoped beanWithGameScoped;

    public MyGameContextBean(String id) {
        super(id);
    }

    @PostConstruct
    private void init() {
        System.out.println("create " + this.getClass());
    }

    public String getString() {
        return "GameContextBean";
    }

    public BeanWithGameScoped getBeanWithGameScoped() {
        return beanWithGameScoped;
    }
}
