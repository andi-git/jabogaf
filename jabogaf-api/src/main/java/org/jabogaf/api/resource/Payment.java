package org.jabogaf.api.resource;

/**
 * The cost of a payment, based on {@link Resource}.
 */
public interface Payment {

    Resource getResource();

    int getAmount();
}
