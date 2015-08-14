package org.jabogaf.util.copy;

import org.apache.commons.beanutils.BeanUtils;
import org.jabogaf.util.log.SLF4J;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@ApplicationScoped
public class CloneBeanBasic implements CloneBean {

    @Inject
    @SLF4J
    private Logger log;

    @Override
    public <T> Optional<T> clone(T bean) {
        try {
            //noinspection unchecked
            return Optional.of((T) BeanUtils.cloneBean(bean));
        } catch (Exception e) {
            log.error("unable to create a copy of {}: {]" + bean, e.getLocalizedMessage());
            return Optional.empty();
        }
    }
}
