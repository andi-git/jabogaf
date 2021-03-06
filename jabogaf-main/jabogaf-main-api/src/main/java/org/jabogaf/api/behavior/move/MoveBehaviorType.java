package org.jabogaf.api.behavior.move;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * This qualifier is used to specify the concrete {@link MoveBehavior}.
 */
@Qualifier
@Retention(RUNTIME)
@Target({TYPE, METHOD, FIELD, PARAMETER})
public @interface MoveBehaviorType {

    /**
     * The concrete type of {@link MoveBehavior}.
     *
     * @return
     */
    public Class<? extends MoveBehavior> value();
}
