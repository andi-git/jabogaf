package at.ahammer.boardgame.api.resource;

public class NotSameResourceException extends Exception {

    private final Resource resourceExpected;

    private final Resource resourceAvailable;

    public NotSameResourceException(Resource resourceExpected, Resource resourceAvailable) {
        super("resource expected: " + resourceExpected + " but is: " + resourceAvailable);
        this.resourceExpected = resourceExpected;
        this.resourceAvailable = resourceAvailable;
    }

    public Resource getResourceExpected() {
        return resourceExpected;
    }

    public Resource getResourceAvailable() {
        return resourceAvailable;
    }
}
