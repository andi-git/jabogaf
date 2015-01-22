package at.ahammer.boardgame.core.resource;

import at.ahammer.boardgame.api.resource.Resource;

public class ResourceStone extends ResourceBasic<ResourceStone> {

    public ResourceStone() {
        super(0);
    }

    public ResourceStone(int amount) {
        super(amount);
    }
}
