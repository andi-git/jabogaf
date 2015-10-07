package org.jabogaf.api.resource;

import java.util.Set;

public interface GameStateResources {

    Set<Resource> getResources();

    void addResource(Resource resource);

    void addResources(Set<Resource> resources);

    void clearResource();

    void removeResource(Resource resource);
}
