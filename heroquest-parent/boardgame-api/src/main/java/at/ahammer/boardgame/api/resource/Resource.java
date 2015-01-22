package at.ahammer.boardgame.api.resource;

import at.ahammer.boardgame.api.cdi.GameContextBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * A resource that can be hold by a {@link at.ahammer.boardgame.api.resource.ResourceHolder} and used by a {@link
 * at.ahammer.boardgame.api.resource.Payment}.
 */
public interface Resource<T extends Resource> extends GameContextBean {

    int add(int add);

    int add(Resource resource) throws NotSameResourceException;

    int remove(int remove) throws NotEnoughResourceException;

    int remove(Resource resource) throws NotEnoughResourceException, NotSameResourceException;

    int getAmount();

    void setAmount(Resource resource) throws NotSameResourceException;

    void setAmount(int amount);

    boolean canPay(int amount);

    boolean canPay(Resource resource) throws NotSameResourceException;

    boolean isResourceType(Resource resource);

    boolean isResourceType(Class<? extends Resource> resource);

    T clone();

    Payment asPayment();
}
