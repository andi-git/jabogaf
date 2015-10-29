package org.jabogaf.util.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import java.util.List;
import java.util.function.Supplier;

@Dependent
@SLF4J
public class LogWrapper {

    private final Logger log;

    @Inject
    public LogWrapper(InjectionPoint injectionPoint) {
        this.log = LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass());
    }

    public Logger log() {
        return log;
    }

    public void debug(String message, Object... object) {
        if (log.isDebugEnabled()) {
            log.debug(message, object);
        }
    }

    public void debug(String message, Supplier<Object> supplier) {
        if (log.isDebugEnabled()) {
            log.debug(message, supplier.get());
        }
    }

    public void debug(String message, List<Supplier<Object>> suppliers) {
        if (log.isDebugEnabled()) {
            log.debug(message, getValuesOfSuppliers(suppliers));
        }
    }

    public void info(String message, Object... object) {
        if (log.isInfoEnabled()) {
            log.info(message, object);
        }
    }

    public void info(String message, Supplier<Object> supplier) {
        if (log.isInfoEnabled()) {
            log.info(message, supplier.get());
        }
    }


    public void info(String message, List<Supplier<Object>> suppliers) {
        if (log.isInfoEnabled()) {
            log.info(message, getValuesOfSuppliers(suppliers));
        }
    }

    public void warn(String message, Object... object) {
        if (log.isWarnEnabled()) {
            log.warn(message, object);
        }
    }

    public void warn(String message, Supplier<Object> supplier) {
        if (log.isWarnEnabled()) {
            log.warn(message, supplier.get());
        }
    }


    public void warn(String message, List<Supplier<Object>> suppliers) {
        if (log.isWarnEnabled()) {
            log.warn(message, getValuesOfSuppliers(suppliers));
        }
    }

    public void error(String message, Object... object) {
        if (log.isErrorEnabled()) {
            log.error(message, object);
        }
    }

    public void error(String message, Supplier<Object> supplier) {
        if (log.isErrorEnabled()) {
            log.error(message, supplier.get());
        }
    }

    public void error(String message, List<Supplier<Object>> suppliers) {
        if (log.isErrorEnabled()) {
            log.error(message, getValuesOfSuppliers(suppliers));
        }
    }

    private Object[] getValuesOfSuppliers(List<Supplier<Object>> suppliers) {
        Object[] values = new Object[suppliers.size()];
        for (int i = 0; i < suppliers.size(); i++) {
            Supplier<Object> supplier = suppliers.get(i);
            if (supplier != null) {
                values[i] = supplier.get();
            }
        }
        return values;
    }
}
