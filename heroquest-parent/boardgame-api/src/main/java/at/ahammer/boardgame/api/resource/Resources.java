package at.ahammer.boardgame.api.resource;

/**
 * This class contains available {@link at.ahammer.boardgame.api.resource.Resource}s of a {@link
 * at.ahammer.boardgame.api.resource.ResourceHolder}.
 */
public interface Resources extends ResourceHolder {

    void add(Resource resource);

    void remove(Resource resource) throws NotEnoughResourceException;

    boolean has(Resource resource);

    boolean has(Class<? extends Resource> resource);

    void clear();
}
