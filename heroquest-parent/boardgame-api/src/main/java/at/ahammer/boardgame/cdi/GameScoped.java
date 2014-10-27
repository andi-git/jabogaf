package at.ahammer.boardgame.cdi;

import javax.enterprise.context.NormalScope;
import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * The CDI-scope of all elements within a game. Every component with this scope cast unique within the GameContext.
 */
@Target({
        TYPE,
        METHOD,
        FIELD
})
@Retention(RUNTIME)
@Documented
@Inherited
@NormalScope
public @interface GameScoped {

}
