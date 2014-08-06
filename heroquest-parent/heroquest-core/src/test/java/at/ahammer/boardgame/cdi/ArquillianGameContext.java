package at.ahammer.boardgame.cdi;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.State;
import org.jboss.arquillian.test.spi.TestMethodExecutor;
import org.jboss.arquillian.test.spi.TestResult;
import org.jboss.arquillian.test.spi.TestRunnerAdaptor;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import javax.enterprise.inject.Typed;
import java.lang.reflect.Method;

/**
 * Created by andreas on 03.08.14.
 */
public class ArquillianGameContext extends Arquillian {

    public ArquillianGameContext(Class<?> klass) throws InitializationError {
        super(klass);
    }

    // this cast a copy (and extension) of the super-class!!!!
    @Override
    protected Statement methodInvoker(FrameworkMethod method, Object test) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                Method getAdaptor = State.class.getDeclaredMethod("getTestAdaptor");
                getAdaptor.setAccessible(true);
                TestRunnerAdaptor adaptor = (TestRunnerAdaptor) getAdaptor.invoke(State.class);

                TestResult result = adaptor.test(new TestMethodExecutor() {
                    @Override
                    public void invoke(Object... parameters) throws Throwable {
                        try {
                            if (test instanceof BeanManagerProducer) {
                                GameContext.run(((BeanManagerProducer) test).getBeanManager(), (gameContextId) -> {
                                    try {
                                        method.invokeExplosively(test, parameters);
                                    } catch (Throwable throwable) {
                                        throw throwable;
                                    }
                                    return gameContextId;
                                });
                            } else {
                                method.invokeExplosively(test, parameters);
                            }
                        } catch (Throwable e) {
                            // Force a way to return the thrown Exception from the Container the client.
                            State.caughtTestException(e);
                            throw e;
                        }
                    }

                    public Method getMethod() {
                        return method.getMethod();
                    }

                    public Object getInstance() {
                        return test;
                    }
                });
                if (result.getThrowable() != null) {
                    throw result.getThrowable();
                }
            }
        };
    }
}
