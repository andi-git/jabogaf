package at.ahammer.boardgame.api.resource;

import at.ahammer.boardgame.api.cdi.GameContextBean;

/**
 * A resource that can be hold by a {@link at.ahammer.boardgame.api.resource.ResourceHolder} and used by a {@link
 * at.ahammer.boardgame.api.resource.Payment}.
 * <p/>
 * This class is immutable.
 */
public interface Resource<T extends Resource> extends GameContextBean {

    Resource add(int add);

    Resource add(Resource resource);

    Resource remove(int remove) throws NotEnoughResourceException;

    Resource remove(Resource resource) throws NotEnoughResourceException;

    int getAmount();

    Resource setAmount(int amount);

    Resource setAmount(Resource resource) throws NotSameResourceException;

    boolean canPay(int amount);

    boolean canPay(Resource resource);

    boolean isResourceType(Resource resource);

    boolean isResourceType(Class<? extends Resource> resource);

    T clone();

    Payment asPayment();

    boolean sameAmount(Resource resource);

    boolean greater(Resource resource);

    boolean greaterEquals(Resource resource);

    boolean lesser(Resource resource);

    boolean lesserEquals(Resource resource);
}
