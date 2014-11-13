package at.ahammer.boardgame.test;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Add all alternatives that has be activated within this test-class.
 */
@Target({
    TYPE,
})
@Retention(RUNTIME)
public @interface ActivateAlternatives {

    Class<?>[] value();
}
