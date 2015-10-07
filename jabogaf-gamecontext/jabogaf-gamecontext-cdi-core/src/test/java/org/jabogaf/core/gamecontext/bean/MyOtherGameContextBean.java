package org.jabogaf.core.gamecontext.bean;

import org.jabogaf.api.state.GameState;
import org.jabogaf.core.gamecontext.GameContextBeanBasic;
import org.jabogaf.core.gamecontext.GameContextBeanWithStateBasic;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Typed;
import javax.inject.Inject;

@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
@Typed()
public class MyOtherGameContextBean extends GameContextBeanWithStateBasic<MyOtherGameContextBean> {

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
    public GameState<MyOtherGameContextBean> getState() {
        return state;
    }

    @Dependent
    public static class State extends GameState<MyOtherGameContextBean> {

        @Override
        public Class<MyOtherGameContextBean> classOfContainingBean() {
            return MyOtherGameContextBean.class;
        }
    }

}
