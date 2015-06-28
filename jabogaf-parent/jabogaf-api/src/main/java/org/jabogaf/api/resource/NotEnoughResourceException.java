package org.jabogaf.api.resource;

public class NotEnoughResourceException extends Exception {

    private final Class<? extends Resource> resource;

    private final int amountNeeded;

    private final int amountAvailable;

    public NotEnoughResourceException(Resource resource, int amountNeeded) {
        super("not enough " + resource + ": need " + amountNeeded + " but only have " + resource.getAmount());
        this.resource = resource.getClass();
        this.amountAvailable = resource.getAmount();
        this.amountNeeded = amountNeeded;
    }

    public NotEnoughResourceException(Payment payment, int amountAvailable) {
        super("not enough " + payment.getResource() + " for payment: need " + payment.getAmount() + " but only have " + amountAvailable);
        this.resource = payment.getResource().getClass();
        this.amountNeeded = payment.getAmount();
        this.amountAvailable = amountAvailable;
    }

    public static NotEnoughResourceException newNoResourceAvailable(Class<? extends Resource> type, int amountNeeded) {
        try {
            return new NotEnoughResourceException(type.newInstance(), amountNeeded);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException("can't instantiate " + type, e);
        }
    }

    public static NotEnoughResourceException newNoResourceAvailable(Payment payment) {
        return newNoResourceAvailable(payment.getResource().getClass(), payment.getAmount());
    }

    public Class<? extends Resource> getResource() {
        return resource;
    }

    public int getAmountNeeded() {
        return amountNeeded;
    }

    public int getAmountAvailable() {
        return amountAvailable;
    }
}
