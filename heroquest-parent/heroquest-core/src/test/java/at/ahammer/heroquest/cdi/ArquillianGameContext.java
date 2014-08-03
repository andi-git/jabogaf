package at.ahammer.heroquest.cdi;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

/**
 * Created by andreas on 03.08.14.
 */
public class ArquillianGameContext extends Arquillian{

    public ArquillianGameContext(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    protected void runChild(FrameworkMethod method, RunNotifier notifier) {
        GameContext.run(() -> {
            ArquillianGameContext.super.runChild(method, notifier);
        });
    }
}
