package org.jabogaf.core.resource;

import org.jabogaf.api.gamecontext.FireEvent;
import org.jabogaf.api.resource.NotEnoughResourceException;
import org.jabogaf.api.resource.NotSameResourceException;
import org.jabogaf.api.resource.Payment;
import org.jabogaf.api.resource.Resource;
import org.jabogaf.core.gamecontext.GameContextBeanBasic;
import org.jabogaf.util.log.SLF4J;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public abstract class ResourceBasic<T extends ResourceBasic> extends GameContextBeanBasic<T> implements Resource<T> {

    @Inject
    @SLF4J
    private Logger log;

    private int amount;
    
    protected ResourceBasic() {
        this(0);
    }

    protected ResourceBasic(int amount) {
        super();
        if (amount < 0) {
            throw new IllegalStateException("amount is " + amount + " but must be > 0");
        }
        this.amount = amount;
    }

    public ResourceBasic(int amount, FireEvent fireEvent) {
        super(fireEvent);
        if (amount < 0) {
            throw new IllegalStateException("amount is " + amount + " but must be > 0");
        }
        this.amount = amount;
    }

    @Override
    public Resource add(int add) {
        return newInstance(this.amount + add);
    }

    @Override
    public T add(Resource resource) {
        if (isResourceType(resource)) {
            return newInstance(this.amount + resource.getAmount());
        }
        return clone();
    }

    @Override
    public Resource remove(int remove) throws NotEnoughResourceException {
        if (this.amount < remove) {
            throw new NotEnoughResourceException(this, remove);
        }
        return newInstance(this.amount - remove);
    }

    @Override
    public T remove(Resource resource) throws NotEnoughResourceException {
        if (isResourceType(resource)) {
            if (this.amount < resource.getAmount()) {
                throw new NotEnoughResourceException(this, resource.getAmount());
            }
            return newInstance(this.amount - resource.getAmount());
        }
        return clone();
    }

    @Override
    public int getAmount() {
        return this.amount;
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
        return this.amount >= amount;
    }

    @Override
    public boolean canPay(Resource resource) {
        return isResourceType(resource) && this.amount >= resource.getAmount();
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
        return newInstance(this.amount);
    }

    public Payment asPayment() {
        return new PaymentBasic(clone());
    }

    @Override
    public boolean sameAmount(Resource resource) {
        return isResourceType(resource) && this.amount == resource.getAmount();
    }

    @Override
    public boolean greater(Resource resource) {
        return isResourceType(resource) && this.amount > resource.getAmount();
    }

    @Override
    public boolean greaterEquals(Resource resource) {
        return isResourceType(resource) && this.amount >= resource.getAmount();
    }

    @Override
    public boolean lesser(Resource resource) {
        return isResourceType(resource) && this.amount < resource.getAmount();
    }

    @Override
    public boolean lesserEquals(Resource resource) {
        return isResourceType(resource) && this.amount <= resource.getAmount();
    }
}
