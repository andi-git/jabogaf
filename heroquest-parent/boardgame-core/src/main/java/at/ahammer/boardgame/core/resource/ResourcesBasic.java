package at.ahammer.boardgame.core.resource;

import at.ahammer.boardgame.api.resource.NotEnoughResourceException;
import at.ahammer.boardgame.api.resource.Payment;
import at.ahammer.boardgame.api.resource.Resource;
import at.ahammer.boardgame.api.resource.Resources;
import at.ahammer.boardgame.core.cdi.GameContextBeanBasic;
import org.slf4j.Logger;

import javax.enterprise.inject.Typed;
import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

@Typed
public class ResourcesBasic extends GameContextBeanBasic implements Resources {

    private final Set<Resource> resources = new HashSet<>();

    @Inject
    private Logger log;

    @Override
    public void add(Resource resource) {
        Resource existingResource = getExistingResource(resource);
        if (existingResource != null) {
            existingResource.add(resource);
        } else {
            if (resource != null && resource.getAmount() > 0) {
                resources.add(resource);
            }
        }
    }

    @Override
    public void remove(Resource resource) throws NotEnoughResourceException {
        Resource existingResource = getExistingResource(resource);
        if (existingResource != null) {
            existingResource.remove(resource);
        } else {
            if (resource != null) {
                throw NotEnoughResourceException.newNoResourceAvailable(resource.getClass(), resource.getAmount());
            }
        }
    }

    @Override
    public boolean has(Resource resource) {
        return getExistingResource(resource) != null;
    }

    @Override
    public boolean has(Class<? extends Resource> resource) {
        return getExistingResource(resource) != null;
    }

    @Override
    public void clear() {
        resources.clear();
    }

    @Override
    public boolean canPay(Payment payment) {
        Resource existingResource = getExistingResource(payment.getResource());
        if (existingResource != null) {
            return existingResource.canPay(payment.getAmount());
        } else {
            return false;
        }
    }

    @Override
    public void pay(Payment payment) throws NotEnoughResourceException {
        Resource existingResource = getExistingResource(payment.getResource());
        if (existingResource != null) {
            if (existingResource.canPay(payment.getAmount())) {
                existingResource.remove(payment.getAmount());
            } else {
                throw new NotEnoughResourceException(payment, amountOf(payment.getResource().getClass()));
            }
        } else {
            throw NotEnoughResourceException.newNoResourceAvailable(payment);
        }
    }

    @Override
    public void earn(Payment payment) {
        if (payment != null) {
            add(payment.getResource());
        }
    }

    @Override
    public int amountOf(Class<? extends Resource> resource) {
        int amount = 0;
        Resource existingResource = getExistingResource(resource);
        if (existingResource != null) {
            amount = existingResource.getAmount();
        }
        return amount;
    }

    @Override
    public Set<Resource> getResources() {
        Set<Resource> result = new HashSet<>();
        for (Resource resource : resources) {
            result.add(resource.clone());
        }
        return Collections.unmodifiableSet(result);
    }

    @Override
    public List<Resource> getSortedResources(Comparator<? super Resource> comparator) {
        return resources.stream().map((r) -> r.clone()).sorted(comparator).collect(Collectors.toList());
    }

    @Override
    public List<Resource> getSortedResources() {
        return getSortedResources((r1, r2) -> r1.toString().compareTo(r2.toString()));
    }

    private Resource getExistingResource(Class<? extends Resource> resource) {
        Resource existingResource = null;
        Optional<Resource> first = resources.stream().filter(r -> r.isResourceType(resource)).findFirst();
        if (first.isPresent()) {
            existingResource = first.get();
        }
        return existingResource;
    }

    private Resource getExistingResource(Resource resource) {
        return resource != null ? getExistingResource(resource.getClass()) : null;
    }
}
