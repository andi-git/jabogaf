package org.jabogaf.util.copy;

/**
 * Copy properties from one bean to another where both beans have the same type.
 */
@FunctionalInterface
public interface CopyProperties {

    void copy(Object source, Object target);
}
