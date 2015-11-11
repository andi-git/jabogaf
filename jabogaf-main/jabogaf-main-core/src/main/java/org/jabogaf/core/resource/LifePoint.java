package org.jabogaf.core.resource;

/**
 * The representation of life.
 */
public class LifePoint extends ResourceBasic<LifePoint> {

    public static final LifePoint MAX = new LifePoint(Integer.MAX_VALUE);

    public static final LifePoint NULL = new LifePoint(0);

    public static final LifePoint ONE = new LifePoint(1);

    public LifePoint() {
        super();
    }

    public LifePoint(int amount) {
        super(amount);
    }
}
