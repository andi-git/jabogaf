package at.ahammer.boardgame.api.resource;

public class NotEnoughResourceException extends Exception {

    private final Resource resource;

    private final int amountNeeded;

    public NotEnoughResourceException(Resource resource, int amountNeeded) {
        super("not enough " + resource + ": need " + amountNeeded + " but only have " + resource.getAmount());
        this.resource = resource;
        this.amountNeeded = amountNeeded;
    }

    public NotEnoughResourceException(Payment payment) {
        this(payment.getResource(), payment.getAmount());
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

    public Resource getResource() {
        return resource;
    }

    public int getAmountNeeded() {
        return amountNeeded;
    }

    public int getAmountAvailable() {
        return resource.getAmount();
    }
}
