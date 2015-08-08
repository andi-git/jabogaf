package org.jabogaf.core.resource;

import org.jabogaf.api.resource.*;
import org.jabogaf.api.state.GameState;
import org.jabogaf.core.cdi.GameContextBeanBasic;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Typed;
import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

@Typed
public class ResourcesBasic extends GameContextBeanBasic implements Resources {

    @Inject
    private State state;

    @Override
    public void add(Resource resource) {
        Resource existingResource = getExistingResource(resource);
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
    public void remove(Resource resource) throws NotEnoughResourceException {
        Resource existingResource = getExistingResource(resource);
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
    public boolean has(Resource resource) {
        return getExistingResource(resource) != null;
    }

    @Override
    public boolean has(Class<? extends Resource> resource) {
        return getExistingResource(resource) != null;
    }

    @Override
    public void clear() {
        state.clearResource();
    }

    @Override
    public Resource get(Class<? extends Resource> type) {
        return getExistingResource(type);
    }

    @Override
    public void setResource(Resource resource) {
        state.removeResource(getExistingResource(resource.getClass()));
        state.addResource(resource.clone());
    }

    @Override
    public boolean canPay(Payment payment) {
        Resource existingResource = getExistingResource(payment.getResource());
        return existingResource != null && existingResource.canPay(payment.getAmount());
    }

    @Override
    public void pay(Payment payment) throws NotEnoughResourceException {
        Resource existingResource = getExistingResource(payment.getResource());
        if (existingResource != null) {
            if (existingResource.canPay(payment.getAmount())) {
                Resource newResource = existingResource.remove(payment.getResource());
                state.removeResource(existingResource);
                state.addResource(newResource);
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
        return Collections.unmodifiableSet(state.getResources().stream().map(Resource::clone).collect(Collectors.toSet()));
    }

    @Override
    public List<Resource> getSortedResources(Comparator<? super Resource> comparator) {
        return state.getResources().stream().map(Resource::clone).sorted(comparator).collect(Collectors.toList());
    }

    @Override
    public ResourceHolder cloneResourceHolder() {
        ResourceHolder resourceHolder = new ResourcesBasic();
        state.getResources().forEach(resourceHolder::setResource);
        return resourceHolder;
    }

    @Override
    public List<Resource> getSortedResources() {
        return getSortedResources((r1, r2) -> r1.toString().compareTo(r2.toString()));
    }

    private Resource getExistingResource(Class<? extends Resource> resource) {
        Resource existingResource = null;
        Optional<Resource> first = state.getResources().stream().filter(r -> r.isResourceType(resource)).findFirst();
        if (first.isPresent()) {
            existingResource = first.get();
        }
        return existingResource;
    }

    private Resource getExistingResource(Resource resource) {
        return resource != null ? getExistingResource(resource.getClass()) : null;
    }

    @Dependent
    public static class State extends GameState {

        private final Set<Resource> resources = new HashSet<>();

        public Set<Resource> getResources() {
            return resources;
        }

        public void addResource(Resource resource) {
            resources.add(resource);
        }

        public void clearResource() {
            resources.clear();
        }

        public void removeResource(Resource resource) {
            resources.remove(resource);
        }

    }
}