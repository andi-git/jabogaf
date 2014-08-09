package at.ahammer.boardgame.test.util;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Run method after every test-method. The within this method will run in the current {@link at.ahammer.boardgame.cdi.GameContext}.
 * <p/>
 * This annotation will only work in combination with {@link at.ahammer.boardgame.test.util.ArquillianGameContext} and {@link at.ahammer.boardgame.test.util.RunAllMethodsInGameContext}.
 */
@Target({
        METHOD,
})
@Retention(RUNTIME)
public @interface AfterInGameContext {
}
