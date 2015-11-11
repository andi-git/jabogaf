package org.jabogaf.core.resource;

import org.jabogaf.api.gamecontext.FireEvent;

/**
 * The representation of movement-points.
 */
public class MovePoint extends ResourceBasic<MovePoint> {

    public static final MovePoint MAX = new MovePoint(Integer.MAX_VALUE);

    public static final MovePoint NULL = new MovePoint(0);

    public static final MovePoint ONE = new MovePoint(1);

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
