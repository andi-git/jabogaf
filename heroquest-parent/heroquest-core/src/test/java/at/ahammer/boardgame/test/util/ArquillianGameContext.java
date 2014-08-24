package at.ahammer.boardgame.test.util;

import at.ahammer.boardgame.cdi.GameContext;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.State;
import org.jboss.arquillian.test.spi.TestMethodExecutor;
import org.jboss.arquillian.test.spi.TestResult;
import org.jboss.arquillian.test.spi.TestRunnerAdaptor;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * A JUnit-TestRunner for Arquillian-Test with an active {@link at.ahammer.boardgame.cdi.GameContext}.
 * <p/>
 * If the underlying test-class extends {@link ArquillianGameContextTest}, then all methods run within a new {@link at.ahammer.boardgame.cdi.GameContext}.
 * <p/>
 * This class will also add all classes defined in {@link ActivateAlternatives} to the {@link AlternativesHolder}, so the {@link CDIActivationExtension} will add these classes dynamically (without beans.xml) as alternatives.
 * Note that the defined alternatives will be activated for <strong>every</strong> test-method.
 */
public class ArquillianGameContext extends Arquillian {

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
        // collect all alternatives to activate
        AlternativesHolder.clear();
        for (Annotation annotation : getTestClass().getJavaClass().getAnnotations()) {
            if (annotation.annotationType() == ActivateAlternatives.class) {
                for (Class<?> clazz : ((ActivateAlternatives) annotation).value()) {
                    AlternativesHolder.add(clazz);
                }
            }
        }
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
                            if (test instanceof RunAllMethodsInGameContext) {
                                // it the test-class is an instance of ArquillianGameContextTest, then start a new GameContext
                                GameContext.run(((ArquillianGameContextTest) test).getBeanManager(), (gameContextId) -> {
                                    // call before-method
                                    invokeAnnotatedMethod(test, BeforeInGameContext.class);
//                                    for(Method method : test.getClass().getDeclaredMethods()) {
//                                        if (method.isAnnotationPresent(BeforeInGameContext.class)) {
//                                            method.setAccessible(true);
//                                            method.invoke(test);
//                                            break;
//                                        }
//                                    }
                                    try {
                                        method.invokeExplosively(test, parameters);
                                    } catch (Throwable throwable) {
                                        throw throwable;
                                    }
                                    // call after-method
                                    invokeAnnotatedMethod(test, AfterInGameContext.class);
//                                    for(Method method : test.getClass().getDeclaredMethods()) {
//                                        if (method.isAnnotationPresent(AfterInGameContext.class)) {
//                                            method.setAccessible(true);
//                                            method.invoke(test);
//                                            break;
//                                        }
//                                    }
                                    return gameContextId;
                                });
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

    private void invokeAnnotatedMethod(Object test, java.lang.Class<? extends Annotation> annotationClass) throws InvocationTargetException, IllegalAccessException {
        Method annotatedMethod = getMethodAnnotatedWith(test.getClass(), annotationClass);
        if (annotatedMethod != null) {
            annotatedMethod.setAccessible(true);
            annotatedMethod.invoke(test);
        }
    }

    private Method getMethodAnnotatedWith(Class<?> testClass, java.lang.Class<? extends Annotation> annotationClass) {
        for(Method method : testClass.getDeclaredMethods()) {
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
