package org.jabogaf.core.resource;

import org.jabogaf.api.gamecontext.FireEvent;

/**
 * The representation of movement-points.
 */
public class MovePoint extends ResourceBasic<MovePoint> {

    public MovePoint() {
        super();
    }

    public MovePoint(int amount) {
        super(amount);
    }

    public MovePoint(int amount, FireEvent fireEvent) {
        super(amount, fireEvent);
    }
}
