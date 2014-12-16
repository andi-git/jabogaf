package at.ahammer.boardgame.common.artifact.shield;

import at.ahammer.boardgame.api.artifact.shield.Shield;
import at.ahammer.boardgame.api.artifact.shield.ShieldType;

public class SimpleShield extends Shield {

    public SimpleShield() {
        this("Simple Shield");
    }

    public SimpleShield(String id) {
        super(id, ShieldType.ONE_HAND_DEFENSE);
    }

}
