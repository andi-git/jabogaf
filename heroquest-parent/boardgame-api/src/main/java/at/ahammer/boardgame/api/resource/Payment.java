package at.ahammer.boardgame.api.resource;

/**
 * The cost of a payment, based on {@link at.ahammer.boardgame.api.resource.Resource}.
 */
public interface Payment {

    Resource getResource();

    int getAmount();
}
