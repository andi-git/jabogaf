package org.jabogaf.util.copy;

import java.util.Optional;

/**
 * Clone a bean.
 */
@FunctionalInterface
public interface CloneBean {

    <T> Optional<T> clone(T bean);
}