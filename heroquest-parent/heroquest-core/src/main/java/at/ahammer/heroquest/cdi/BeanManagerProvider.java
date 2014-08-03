package at.ahammer.heroquest.cdi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Created by andreas on 02.08.14.
 */
class BeanManagerProvider {

    private static final BeanManager beanManager;

    private static final Logger log = LoggerFactory.getLogger(BeanManagerProvider.class);

    static {
        BeanManager beanManagerTmp;
        beanManagerTmp = getViaLookup();
        if (beanManagerTmp == null) {
            beanManagerTmp = getViaWeld();
        }
        if (beanManagerTmp == null) {
            log.error("unable to get " + BeanManager.class);
        }
        beanManager = beanManagerTmp;
    }

    private static BeanManager getViaLookup() {
        try {
            InitialContext ic = new InitialContext();
            return (BeanManager) ic.lookup("java:comp/" + BeanManager.class.getSimpleName());
        } catch (NamingException e) {
            log.warn("unable to get " + BeanManager.class.getSimpleName() + " via lookup");
            return null;
        }
    }

    private static BeanManager getViaWeld() {
        try {
            Class weldClass = Class.forName("org.jboss.weld.environment.se.Weld");
            Object weld = weldClass.newInstance();
            Object weldContainer = weldClass.getDeclaredMethod("initialize").invoke(weld);
            return (BeanManager) weldContainer.getClass().getDeclaredMethod("getBeanManager").invoke(weldContainer);
        } catch (Exception e) {
            log.warn("unable to get " + BeanManager.class.getSimpleName() + " via Weld\n" + e);
            return null;
        }
    }

    static BeanManager getBeanManager() {
        return beanManager;
    }
}
