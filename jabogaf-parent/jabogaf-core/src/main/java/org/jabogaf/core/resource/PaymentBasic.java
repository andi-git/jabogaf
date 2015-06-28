package org.jabogaf.core.resource;

import org.jabogaf.api.resource.Payment;
import org.jabogaf.api.resource.Resource;

/**
 * The cost of a payment, based on {@link org.jabogaf.api.resource.Resource}.
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
