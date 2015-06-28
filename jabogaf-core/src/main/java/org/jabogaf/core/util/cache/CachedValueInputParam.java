package org.jabogaf.core.util.cache;

import java.util.function.Function;

/**
 * An abstract class for a {@link CachedValue} where the creation of the {@link CachedValue} needs input parameters.
 */
public abstract class CachedValueInputParam<VALUE, PARAM> extends CachedValue<VALUE> {

    private PARAM lastParam;

    /**
     * Get the value. If the cache is valid, the cached value will be returned. If the cache is not valid, the value
     * will be created.
     *
     * @param param the input-parameters
     * @return the value
     */
    public VALUE get(PARAM param) {
        boolean useCache = true;
        if (param != null && !param.equals(lastParam)) {
            useCache = false;
        }
        return getCached(() -> create().apply(param), useCache);
    }

    protected abstract Function<PARAM, VALUE> create();
}
