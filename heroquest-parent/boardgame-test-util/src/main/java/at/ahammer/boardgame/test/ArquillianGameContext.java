package at.ahammer.boardgame.test;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.State;
import org.jboss.arquillian.test.spi.TestMethodExecutor;
import org.jboss.arquillian.test.spi.TestResult;
import org.jboss.arquillian.test.spi.TestRunnerAdaptor;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * A JUnit-TestRunner for Arquillian-Test with an active {@link at.ahammer.boardgame.cdi.GameScoped}.
 * <p/>
 * If the underlying test-class extends {@link ArquillianGameContextTest}, then all methods run within a new {@link
 * at.ahammer.boardgame.cdi.GameScoped}.
 * <p/>
 * This class will also add all classes defined in {@link ActivateAlternatives} to the {@link
 * at.ahammer.boardgame.cdi.AlternativesInGameContext}, so these alternatives will be activated for
 * <strong>every</strong> test-method.
 */
public class ArquillianGameContext extends Arquillian {

    private static final Logger log = LoggerFactory.getLogger(ArquillianGameContext.class);

    public ArquillianGameContext(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    protected Statement withBeforeClasses(Statement originalStatement) {
        return super.withBeforeClasses(originalStatement);
    }

    @Override
    protected void runChild(FrameworkMethod method, RunNotifier notifier) {
        super.runChild(method, notifier);
    }

    @Override
    public void run(RunNotifier notifier) {
        super.run(notifier);
    }

    @Override
    protected Statement methodBlock(FrameworkMethod method) {
        return super.methodBlock(method);
    }

    @Override
    protected Statement withAfterClasses(Statement originalStatement) {
//        AlternativesHolder.clear();
        return super.withAfterClasses(originalStatement);
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
                            if (test instanceof ArquillianGameContextTest) {
                                ArquillianGameContextTest arquillianGameContextTest = (ArquillianGameContextTest) test;
                                // activate alternatives
                                log.info("alternatives from annotation: " + getAlternativesToActivate());
                                arquillianGameContextTest.getAlternativesInGameContext().addAll(getAlternativesToActivate());
                                // it the test-class is an instance of ArquillianGameContextTest, then start a new GameContext
                                arquillianGameContextTest.getGameContextManager().runInGameContext((gameContextId) -> {
                                    // call before-method
                                    invokeAnnotatedMethod(test, BeforeInGameContext.class);
                                    try {
                                        method.invokeExplosively(test, parameters);
                                    } catch (Throwable throwable) {
                                        throw throwable;
                                    }
                                    // call after-method
                                    invokeAnnotatedMethod(test, AfterInGameContext.class);
                                    return gameContextId;
                                });
                                arquillianGameContextTest.getAlternativesInGameContext().clear();
                            } else {
                                // otherwise run without a GameContext
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

    private Set<Class<?>> getAlternativesToActivate() {
        Set<Class<?>> alternativesToActivate = new HashSet<>();
        for (Annotation annotation : getTestClass().getJavaClass().getAnnotations()) {
            if (annotation.annotationType() == ActivateAlternatives.class) {
                for (Class<?> clazz : ((ActivateAlternatives) annotation).value()) {
                    alternativesToActivate.add(clazz);
                }
            }
        }
        return alternativesToActivate;
    }

    private void invokeAnnotatedMethod(Object test, Class<? extends Annotation> annotationClass) throws InvocationTargetException, IllegalAccessException {
        Method annotatedMethod = getMethodAnnotatedWith(test.getClass(), annotationClass);
        if (annotatedMethod != null) {
            annotatedMethod.setAccessible(true);
            annotatedMethod.invoke(test);
        }
    }

    private Method getMethodAnnotatedWith(Class<?> testClass, Class<? extends Annotation> annotationClass) {
        for (Method method : testClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotationClass)) {
                return method;
            }
        }
        if (testClass.getSuperclass() != null) {
            return getMethodAnnotatedWith(testClass.getSuperclass(), annotationClass);
        }
        return null;
    }
}
