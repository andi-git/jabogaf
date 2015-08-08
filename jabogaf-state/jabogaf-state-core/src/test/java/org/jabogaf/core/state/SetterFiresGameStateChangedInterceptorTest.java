package org.jabogaf.core.state;

import org.jabogaf.api.event.GameStateChanged;
import org.jabogaf.api.state.ChangesGameState;
import org.jabogaf.api.state.SetterFiresGameStateChanged;
import org.jabogaf.test.cdi.ArquillianGameContext;
import org.jabogaf.test.cdi.ArquillianGameContextTest;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InterceptorBinding;
import javax.interceptor.InvocationContext;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(ArquillianGameContext.class)
public class SetterFiresGameStateChangedInterceptorTest extends ArquillianGameContextTest {

    @Inject
    private BeanWhereMethodsAreIntercepted bean;

    @Inject
    private MethodHolder methodHolder;

    @Test
    public void testInterceptor() {
        assertNull(methodHolder.getMethod());
        bean.setXXX();
        assertEquals("setXXX", methodHolder.getMethod().getName());
        bean.getXXX();
        assertNull(methodHolder.getMethod());
        bean.addXXX();
        assertEquals("addXXX", methodHolder.getMethod().getName());
        bean.removeXXX();
        assertEquals("removeXXX", methodHolder.getMethod().getName());
        bean.clearXXX();
        assertEquals("clearXXX", methodHolder.getMethod().getName());
        bean.foo();
        assertNull(methodHolder.getMethod());
        bean.bar();
        assertEquals("bar", methodHolder.getMethod().getName());
    }

    public void observe(@Observes GameStateChanged gameStateChanged) {
        methodHolder.setMethod(gameStateChanged.getMethod());
    }

    @ClearMethodHolder
    @SetterFiresGameStateChanged
    public static class BeanWhereMethodsAreIntercepted {

        public void setXXX() {
        }

        public void getXXX() {
        }

        public void addXXX() {
        }

        public void removeXXX() {
        }

        public void clearXXX() {
        }

        public void foo() {
        }

        @ChangesGameState
        public void bar() {
        }
    }

    @ApplicationScoped
    public static class MethodHolder {

        private Method method;

        public Method getMethod() {
            return method;
        }

        public void setMethod(Method method) {
            this.method = method;
        }

        public void clear() {
            method = null;
        }
    }

    @InterceptorBinding
    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ClearMethodHolder {

    }

    @Interceptor
    @SetterFiresGameStateChanged
    @Priority(Interceptor.Priority.APPLICATION + 99)
    @Dependent
    public static class ClearMethodHolderInterceptor {

        @Inject
        private MethodHolder methodHolder;

        @AroundInvoke
        private Object intercept(InvocationContext context) throws Exception {
            methodHolder.clear();
            return context.proceed();
        }
    }
}