package org.jabogaf.core.resource;

import org.jabogaf.api.gamecontext.FireEvent;

/**
 * The representation of movement-points.
 */
public class LookPoint extends ResourceBasic<LookPoint> {

    public static final LookPoint MAX = new LookPoint(Integer.MAX_VALUE);

    public static final LookPoint NULL = new LookPoint(0);

    public LookPoint() {
        super();
    }

    public LookPoint(int amount) {
        super(amount);
    }

    public LookPoint(int amount, FireEvent fireEvent) {
        super(amount, fireEvent);
    }
}
