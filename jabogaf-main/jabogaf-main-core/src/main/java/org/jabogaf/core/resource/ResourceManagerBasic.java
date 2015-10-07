package org.jabogaf.core.resource;

import org.jabogaf.api.resource.*;

import javax.enterprise.context.ApplicationScoped;
import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class ResourceManagerBasic implements ResourceManager {

    @Override
    public void add(Resource resource, GameStateResources state) {
        Resource existingResource = getExistingResource(resource, state);
        if (existingResource != null) {
            Resource newResource = existingResource.add(resource);
            state.removeResource(existingResource);
            state.addResource(newResource);
        } else {
            if (resource != null && resource.getAmount() > 0) {
                state.addResource(resource);
            }
        }
    }

    @Override
    public void remove(Resource resource, GameStateResources state) throws NotEnoughResourceException {
        Resource existingResource = getExistingResource(resource, state);
        if (existingResource != null) {
            Resource newResource = existingResource.remove(resource);
            state.removeResource(existingResource);
            state.addResource(newResource);
        } else {
            if (resource != null) {
                throw NotEnoughResourceException.newNoResourceAvailable(resource.getClass(), resource.getAmount());
            }
        }
    }

    @Override
    public boolean has(Resource resource, GameStateResources state) {
        return getExistingResource(resource, state) != null;
    }

    @Override
    public boolean has(Class<? extends Resource> resource, GameStateResources state) {
        return getExistingResource(resource, state) != null;
    }

    @Override
    public void clear(GameStateResources state) {
        state.clearResource();
    }

    @Override
    public Resource get(Class<? extends Resource> type, GameStateResources state) {
        return getExistingResource(type, state);
    }

    @Override
    public void setResource(Resource resource, GameStateResources state) {
        state.removeResource(getExistingResource(resource.getClass(), state));
        state.addResource(resource.clone());
    }

    @Override
    public boolean canPay(Payment payment, GameStateResources state) {
        Resource existingResource = getExistingResource(payment.getResource(), state);
        return existingResource != null && existingResource.canPay(payment.getAmount());
    }

    @Override
    public void pay(Payment payment, GameStateResources state) throws NotEnoughResourceException {
        Resource existingResource = getExistingResource(payment.getResource(), state);
        if (existingResource != null) {
            if (existingResource.canPay(payment.getAmount())) {
                Resource newResource = existingResource.remove(payment.getResource());
                state.removeResource(existingResource);
                state.addResource(newResource);
            } else {
                throw new NotEnoughResourceException(payment, amountOf(payment.getResource().getClass(), state));
            }
        } else {
            throw NotEnoughResourceException.newNoResourceAvailable(payment);
        }
    }

    @Override
    public void earn(Payment payment, GameStateResources state) {
        if (payment != null) {
            add(payment.getResource(), state);
        }
    }

    @Override
    public int amountOf(Class<? extends Resource> resource, GameStateResources state) {
        int amount = 0;
        Resource existingResource = getExistingResource(resource, state);
        if (existingResource != null) {
            amount = existingResource.getAmount();
        }
        return amount;
    }

    @Override
    public Set<Resource> getResourceManager(GameStateResources state) {
        return Collections.unmodifiableSet(state.getResources().stream().map(Resource::clone).collect(Collectors.toSet()));
    }

    @Override
    public List<Resource> getSortedResources(Comparator<? super Resource> comparator, GameStateResources state) {
        return state.getResources().stream().map(Resource::clone).sorted(comparator).collect(Collectors.toList());
    }

    @Override
    public List<Resource> getSortedResources(GameStateResources state) {
        return getSortedResources((r1, r2) -> r1.toString().compareTo(r2.toString()), state);
    }

    private Resource getExistingResource(Class<? extends Resource> resource, GameStateResources state) {
        Resource existingResource = null;
        Optional<Resource> first = state.getResources().stream().filter(r -> r.isResourceType(resource)).findFirst();
        if (first.isPresent()) {
            existingResource = first.get();
        }
        return existingResource;
    }

    private Resource getExistingResource(Resource resource, GameStateResources state) {
        return resource != null ? getExistingResource(resource.getClass(), state) : null;
    }
}