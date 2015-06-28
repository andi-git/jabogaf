package org.jabogaf.core.cdi.bean;

import org.jabogaf.core.cdi.GameContextBeanBasic;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Typed;
import javax.inject.Inject;

@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
@Typed()
public class MyOtherGameContextBean extends GameContextBeanBasic {

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
}
