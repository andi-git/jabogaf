package at.ahammer.boardgame.common.artifact.shield;

import at.ahammer.boardgame.api.artifact.shield.Shield;
import at.ahammer.boardgame.api.artifact.shield.ShieldType;
import at.ahammer.boardgame.core.artifact.shield.ShieldBasic;

public class SimpleShield extends ShieldBasic {

    public SimpleShield() {
        this("Simple Shield");
    }

    public SimpleShield(String id) {
        super(id, ShieldType.ONE_HAND_DEFENSE);
    }

}
