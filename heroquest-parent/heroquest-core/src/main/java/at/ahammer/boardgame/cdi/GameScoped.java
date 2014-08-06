package at.ahammer.boardgame.cdi;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.enterprise.context.NormalScope;

/**
 * The cdi-scope of all elements within a game. Every component
 * with this scope cast unique within the {@link GameContext}.
 * 
 * @autor TA2MIG
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
