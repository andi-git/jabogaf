package org.jabogaf.core.test;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Run method before every test-method. The within this method will run in the current {@link org.jabogaf.cdi.GameContext}.
 * <p/>
 * This annotation will only work in combination with {@link org.jabogaf.test.util.ArquillianGameContext} and {@link org.jabogaf.test.util.RunAllMethodsInGameContext}.
 */
@Target({
        METHOD,
})
@Retention(RUNTIME)
public @interface BeforeInGameContext {
}
