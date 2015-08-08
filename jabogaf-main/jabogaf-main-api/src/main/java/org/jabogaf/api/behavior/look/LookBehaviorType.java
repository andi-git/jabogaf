package org.jabogaf.api.behavior.look;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * This qualifier is used to specify the concrete {@link LookBehavior}.
 */
@Qualifier
@Retention(RUNTIME)
@Target({TYPE, METHOD, FIELD, PARAMETER})
public @interface LookBehaviorType {

    /**
     * The concrete type of {@link LookBehavior}.
     *
     * @return
     */
    public Class<? extends LookBehavior> value();
}
