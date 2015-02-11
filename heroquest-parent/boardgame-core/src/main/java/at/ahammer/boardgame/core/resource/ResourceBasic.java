package at.ahammer.boardgame.core.resource;

import at.ahammer.boardgame.api.resource.NotEnoughResourceException;
import at.ahammer.boardgame.api.resource.NotSameResourceException;
import at.ahammer.boardgame.api.resource.Payment;
import at.ahammer.boardgame.api.resource.Resource;
import at.ahammer.boardgame.core.cdi.GameContextBeanBasic;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public abstract class ResourceBasic<T extends Resource> extends GameContextBeanBasic implements Resource<T> {

    private final int amount;

    @Inject
    private Logger log;

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

    @Override
    public Resource add(int add) {
        return newInstance(amount + add);
    }

    @Override
    public T add(Resource resource) {
        if (isResourceType(resource)) {
            return newInstance(amount + resource.getAmount());
        }
        return clone();
    }

    @Override
    public Resource remove(int remove) throws NotEnoughResourceException {
        if (amount < remove) {
            throw new NotEnoughResourceException(this, remove);
        }
        return newInstance(amount - remove);
    }

    @Override
    public T remove(Resource resource) throws NotEnoughResourceException {
        if (isResourceType(resource)) {
            if (amount < resource.getAmount()) {
                throw new NotEnoughResourceException(this, resource.getAmount());
            }
            return newInstance(amount - resource.getAmount());
        }
        return clone();
    }

    @Override
    public int getAmount() {
        return amount;
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
            Constructor constructor = getClass().getDeclaredConstructor(int.class);
            newInstance = (T) constructor.newInstance(amount);
        } catch (InstantiationException e) {
            log.error(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            log.error(e.getMessage(), e);
        } catch (NoSuchMethodException e) {
            log.error(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            log.error(e.getMessage(), e);
        }
        return newInstance;
    }

    public T clone() {
        return newInstance(amount);
    }

    public Payment asPayment() {
        return new PaymentBasic(clone());
    }

    @Override
    public boolean sameAmount(Resource resource) {
        if (isResourceType(resource)) {
            return amount == resource.getAmount();
        }
        return false;
    }

    @Override
    public boolean greater(Resource resource) {
        if (isResourceType(resource)) {
            return amount > resource.getAmount();
        }
        return false;
    }

    @Override
    public boolean greaterEquals(Resource resource) {
        if (isResourceType(resource)) {
            return amount >= resource.getAmount();
        }
        return false;
    }

    @Override
    public boolean lesser(Resource resource) {
        if (isResourceType(resource)) {
            return amount < resource.getAmount();
        }
        return false;
    }

    @Override
    public boolean lesserEquals(Resource resource) {
        if (isResourceType(resource)) {
            return amount <= resource.getAmount();
        }
        return false;
    }
}
