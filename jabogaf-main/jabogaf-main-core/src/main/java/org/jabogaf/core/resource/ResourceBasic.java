package org.jabogaf.core.resource;

import org.jabogaf.api.resource.NotEnoughResourceException;
import org.jabogaf.api.resource.NotSameResourceException;
import org.jabogaf.api.resource.Payment;
import org.jabogaf.api.resource.Resource;
import org.jabogaf.api.state.GameState;
import org.jabogaf.core.cdi.GameContextBeanBasic;
import org.jabogaf.util.log.SLF4J;
import org.slf4j.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public abstract class ResourceBasic<T extends Resource> extends GameContextBeanBasic implements Resource<T> {

    @Inject
    @SLF4J
    private Logger log;

    @Inject
    private State state;

    protected ResourceBasic() {
        this(0);
    }

    protected ResourceBasic(int amount) {
        super();
        if (amount < 0) {
            throw new IllegalStateException("amount is " + amount + " but must be > 0");
        }
        state.setAmount(amount);
    }

    @Override
    public Resource add(int add) {
        return newInstance(state.getAmount() + add);
    }

    @Override
    public T add(Resource resource) {
        if (isResourceType(resource)) {
            return newInstance(state.getAmount() + resource.getAmount());
        }
        return clone();
    }

    @Override
    public Resource remove(int remove) throws NotEnoughResourceException {
        if (state.getAmount() < remove) {
            throw new NotEnoughResourceException(this, remove);
        }
        return newInstance(state.getAmount() - remove);
    }

    @Override
    public T remove(Resource resource) throws NotEnoughResourceException {
        if (isResourceType(resource)) {
            if (state.getAmount() < resource.getAmount()) {
                throw new NotEnoughResourceException(this, resource.getAmount());
            }
            return newInstance(state.getAmount() - resource.getAmount());
        }
        return clone();
    }

    @Override
    public int getAmount() {
        return state.getAmount();
    }

    @Override
    public Resource setAmount(int amount) {
        return newInstance(amount);
    }

    @Override
    public Resource setAmount(Resource resource) throws NotSameResourceException {
        if (!isResourceType(resource)) {
            throw new NotSameResourceException(this, resource);
        }
        return newInstance(resource.getAmount());
    }

    @Override
    public boolean canPay(int amount) {
        return state.getAmount() >= amount;
    }

    @Override
    public boolean canPay(Resource resource) {
        return isResourceType(resource) && state.getAmount() >= resource.getAmount();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    @Override
    public boolean isResourceType(Resource resource) {
        return this.getClass() == resource.getClass();
    }

    public boolean isResourceType(Class<? extends Resource> resource) {
        return this.getClass() == resource;
    }

    public T newInstance(int amount) {
        T newInstance = null;
        try {
            @SuppressWarnings("unchecked") Constructor<T> constructor = (Constructor<T>) getClass().getDeclaredConstructor(int.class);
            newInstance = constructor.newInstance(amount);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            log.error(e.getMessage(), e);
        }
        return newInstance;
    }

    @SuppressWarnings("CloneDoesntCallSuperClone")
    public T clone() {
        return newInstance(state.getAmount());
    }

    public Payment asPayment() {
        return new PaymentBasic(clone());
    }

    @Override
    public boolean sameAmount(Resource resource) {
        return isResourceType(resource) && state.getAmount() == resource.getAmount();
    }

    @Override
    public boolean greater(Resource resource) {
        return isResourceType(resource) && state.getAmount() > resource.getAmount();
    }

    @Override
    public boolean greaterEquals(Resource resource) {
        return isResourceType(resource) && state.getAmount() >= resource.getAmount();
    }

    @Override
    public boolean lesser(Resource resource) {
        return isResourceType(resource) && state.getAmount() < resource.getAmount();
    }

    @Override
    public boolean lesserEquals(Resource resource) {
        return isResourceType(resource) && state.getAmount() <= resource.getAmount();
    }

    @Override
    public GameState getState() {
        return state;
    }

    @Dependent
    public static class State extends GameState<ResourceBasic> {

        private int amount;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        @Override
        public Class<ResourceBasic> classOfContainingBean() {
            return ResourceBasic.class;
        }
    }
}
