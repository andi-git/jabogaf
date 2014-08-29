package at.ahammer.boardgame.subject.look;

import at.ahammer.boardgame.subject.move.Move;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by andreas on 8/24/14.
 */
@Qualifier
@Retention(RUNTIME)
@Target({TYPE, METHOD, FIELD, PARAMETER})
public @interface LookStrategy {
    Class<? extends Look> value();
}
