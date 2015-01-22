package at.ahammer.boardgame.core.resource;

import at.ahammer.boardgame.api.resource.Resource;

public class ResourceWood extends ResourceBasic<ResourceWood> {

    public ResourceWood() {
        super(0);
    }

    public ResourceWood(int amount) {
        super(amount);
    }
}
