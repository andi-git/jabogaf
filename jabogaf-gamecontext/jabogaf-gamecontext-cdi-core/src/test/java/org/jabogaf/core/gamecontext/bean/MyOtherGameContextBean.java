package org.jabogaf.core.gamecontext.bean;

import org.jabogaf.api.state.GameState;
import org.jabogaf.core.gamecontext.GameContextBeanBasic;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Typed;
import javax.inject.Inject;

@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
@Typed()
public class MyOtherGameContextBean extends GameContextBeanBasic {

    @Inject
    private State state;

    @Inject
    private BeanWithGameScoped beanWithGameScoped;

    public MyOtherGameContextBean() {
        super();
    }

    public MyOtherGameContextBean(String id) {
        super(id);
    }

    @PostConstruct
    private void init() {
        System.out.println("create " + this.getClass());
    }

    public String getString() {
        return "OtherGameContextBean";
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
