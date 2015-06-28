package org.jabogaf.api.resource;

/**
 * This class contains available {@link Resource}s of a {@link ResourceHolder}.
 */
public interface Resources extends ResourceHolder {

    void add(Resource resource);

    void remove(Resource resource) throws NotEnoughResourceException;

    boolean has(Resource resource);

    boolean has(Class<? extends Resource> resource);

    void clear();
}
