package org.jabogaf.api.resource;

import org.jabogaf.api.state.GameState;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * This class contains available {@link Resource}s of a {@link ResourceHolder}.
 */
public interface ResourceManager {

    void add(Resource resource, GameStateResources state);

    void remove(Resource resource, GameStateResources state) throws NotEnoughResourceException;

    boolean has(Resource resource, GameStateResources state);

    boolean has(Class<? extends Resource> resource, GameStateResources state);

    void clear(GameStateResources state);

    Resource get(Class<? extends Resource> type, GameStateResources state);

    void setResource(Resource resource, GameStateResources state);

    boolean canPay(Payment payment, GameStateResources state);

    void pay(Payment payment, GameStateResources state) throws NotEnoughResourceException;

    void earn(Payment payment, GameStateResources state);

    int amountOf(Class<? extends Resource> resource, GameStateResources state);

    Set<Resource> getResourceManager(GameStateResources state);

    List<Resource> getSortedResources(GameStateResources state);

    List<Resource> getSortedResources(Comparator<? super Resource> comparator, GameStateResources state);
}
