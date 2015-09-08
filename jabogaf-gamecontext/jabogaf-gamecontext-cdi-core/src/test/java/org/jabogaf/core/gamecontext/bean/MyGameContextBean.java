package org.jabogaf.core.gamecontext.bean;

import org.jabogaf.api.state.GameState;
import org.jabogaf.core.gamecontext.GameContextBeanBasic;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Typed;
import javax.inject.Inject;

@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
@Typed()
public class MyGameContextBean extends GameContextBeanBasic {

    @Inject
    private BeanWithGameScoped beanWithGameScoped;

    @Inject
    private State state;

    public MyGameContextBean() {
        super();
    }

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

    @Override
    public GameState getState() {
        return state;
    }

    @Dependent
    public static class State extends GameState<MyGameContextBean> {

        @Override
        public Class<MyGameContextBean> classOfContainingBean() {
            return MyGameContextBean.class;
        }
    }
}
