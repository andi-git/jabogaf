package org.jabogaf.test.cdi;

import org.jabogaf.api.cdi.GameScoped;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Run method after every test-method. The within this method will run in the current {@link GameScoped}.
 * <p>
 * This annotation will only work in combination with {@link ArquillianGameContext} and {@link
 * ArquillianGameContextTest}.
 */
@Target({
        METHOD,
})
@Retention(RUNTIME)
public @interface AfterInGameContext {
}
