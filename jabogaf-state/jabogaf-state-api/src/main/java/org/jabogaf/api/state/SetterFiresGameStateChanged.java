package org.jabogaf.api.state;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Every method that mutates a value (starts with "set", "add", "remove", "clear") will fire the event {@code GameStateChanged}.
 */
@InterceptorBinding
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SetterFiresGameStateChanged {

}
