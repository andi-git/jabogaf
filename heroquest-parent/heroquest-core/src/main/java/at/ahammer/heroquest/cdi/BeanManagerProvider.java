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
        try {
            InitialContext ic = new InitialContext();
            beanManagerTmp = (BeanManager) ic.lookup("java:comp/" + BeanManager.class.getSimpleName());
        } catch (NamingException e) {
            beanManagerTmp = null;
            e.printStackTrace();
        }
        beanManager = beanManagerTmp;
    }

    static BeanManager getBeanManager() {
        return beanManager;
    }
}
