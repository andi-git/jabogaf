package org.jabogaf.util.copy;

import org.apache.commons.beanutils.BeanUtils;
import org.jabogaf.util.log.SLF4J;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.lang.reflect.InvocationTargetException;

@ApplicationScoped
public class CopyPropertiesBasic implements CopyProperties {

    @Inject
    @SLF4J
    private Logger log;

    @Override
    public void copy(Object source, Object target) {
        try {
            BeanUtils.copyProperties(target, source);
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("unable to add properties from {} to {}", source, target);
            throw new RuntimeException("unable to add properties from " + source + " to " + target);
        }
    }
}
