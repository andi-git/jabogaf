package at.ahammer.boardgame.test.util;

import javax.enterprise.context.NormalScope;
import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
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
