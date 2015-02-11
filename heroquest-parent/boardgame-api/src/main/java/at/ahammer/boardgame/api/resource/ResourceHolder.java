package at.ahammer.boardgame.api.resource;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * Indicates that the implementing class holds {@link at.ahammer.boardgame.api.resource.Resource}s.
 */
public interface ResourceHolder {

    Resource get(Class<? extends Resource> type);

    void setResource(Resource resource);

    boolean canPay(Payment payment);

    void pay(Payment payment) throws NotEnoughResourceException;

    void earn(Payment payment);

    int amountOf(Class<? extends Resource> resource);

    Set<Resource> getResources();

    List<Resource> getSortedResources();

    List<Resource> getSortedResources(Comparator<? super Resource> comparator);

    ResourceHolder cloneResourceHolder();
}