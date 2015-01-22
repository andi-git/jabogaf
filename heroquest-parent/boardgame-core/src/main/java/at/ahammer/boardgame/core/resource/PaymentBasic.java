package at.ahammer.boardgame.core.resource;

import at.ahammer.boardgame.api.resource.Payment;
import at.ahammer.boardgame.api.resource.Resource;

/**
 * The cost of a payment, based on {@link at.ahammer.boardgame.api.resource.Resource}.
 */
public class PaymentBasic implements Payment {

    private final Resource resource;

    public PaymentBasic(Resource resource) {
        this.resource = resource;
    }

    @Override
    public Resource getResource() {
        return resource.clone();
    }

    @Override
    public int getAmount() {
        return resource.getAmount();
    }
}
