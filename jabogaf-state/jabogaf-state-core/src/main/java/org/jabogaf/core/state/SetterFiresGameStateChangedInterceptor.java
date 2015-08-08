package org.jabogaf.core.state;

import org.jabogaf.api.event.GameStateChanged;
import org.jabogaf.api.state.SetterFiresGameStateChanged;
import org.jabogaf.util.log.SLF4J;
import org.slf4j.Logger;

import javax.annotation.Priority;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

@Interceptor
@SetterFiresGameStateChanged
@Priority(Interceptor.Priority.APPLICATION + 100)
@Dependent
public class SetterFiresGameStateChangedInterceptor {

    @Inject
    private Event<GameStateChanged> gameStateChangedEvent;

    @Inject
    @SLF4J
    private Logger log;

    @Inject
    private MutableMethods mutableMethods;

    @AroundInvoke
    private Object intercept(InvocationContext context) throws Exception {
        Method method = context.getMethod();
        log.debug("intercept method {}", method.getName());
        // if it is a setter, fire the event
        if (mutableMethods.isMutableMethod(method)) {
            log.debug("  is a setter method");
            gameStateChangedEvent.fire(new GameStateChanged(method));
        } else {
            log.debug("  is not a setter method");
        }
        return context.proceed();
    }
}
