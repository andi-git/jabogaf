package at.ahammer.boardgame.core.resource;

import at.ahammer.boardgame.api.cdi.GameContextBean;
import at.ahammer.boardgame.api.resource.NotEnoughResourceException;
import at.ahammer.boardgame.api.resource.NotSameResourceException;
import at.ahammer.boardgame.api.resource.Payment;
import at.ahammer.boardgame.api.resource.Resource;
import at.ahammer.boardgame.core.cdi.GameContextBeanBasic;
import org.slf4j.Logger;

import javax.inject.Inject;

/**
 * A resource that can be hold by a {@link at.ahammer.boardgame.api.resource.ResourceHolder} and used by a {@link
 * at.ahammer.boardgame.api.resource.Payment}.
 */
@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public abstract class ResourceBasic<T extends Resource> extends GameContextBeanBasic implements Resource<T> {

    private int amount;

    @Inject
    private Logger log;

    protected ResourceBasic() {
        super();
    }

    protected ResourceBasic(int amount) {
        super();
        if (amount < 0) {
            throw new IllegalStateException("amount is " + amount + " but must be > 0");
        }
        this.amount = amount;
    }

    @Override
    public int add(int add) {
        amount += add;
        return amount;
    }

    @Override
    public int add(Resource resource) throws NotSameResourceException {
        checkSameResource(resource);
        amount += resource.getAmount();
        return amount;
    }

    @Override
    public int remove(int remove) throws NotEnoughResourceException {
        if (amount < remove) {
            throw new NotEnoughResourceException(this, remove);
        }
        amount -= remove;
        return amount;
    }

    @Override
    public int remove(Resource resource) throws NotEnoughResourceException, NotSameResourceException {
        checkSameResource(resource);
        if (amount < resource.getAmount()) {
            throw new NotEnoughResourceException(this, resource.getAmount());
        }
        amount -= resource.getAmount();
        return amount;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void setAmount(Resource resource) throws NotSameResourceException {
        checkSameResource(resource);
        this.amount = resource.getAmount();
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean canPay(int amount) {
        return this.amount >= amount;
    }

    @Override
    public boolean canPay(Resource resource) throws NotSameResourceException {
        checkSameResource(resource);
        return this.amount >= resource.getAmount();
    }

    private void checkSameResource(Resource resource) throws NotSameResourceException {
        if (this.getClass() != resource.getClass()) {
            throw new NotSameResourceException(this, resource);
        }
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

    public T clone() {
        T clone = null;
        try {
            clone = (T) getClass().newInstance();
            clone.setAmount(getAmount());
        } catch (InstantiationException e) {
            log.error(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            log.error(e.getMessage(), e);
        }
        return clone;
    }

    public Payment asPayment() {
        return new PaymentBasic(this.clone());
    }
}
