package org.jabogaf.core.behavior.look;

import org.jabogaf.api.behavior.look.Lookable;
import org.jabogaf.api.board.BoardManager;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.gamecontext.GameScoped;
import org.jabogaf.api.resource.Resource;
import org.jabogaf.core.resource.LookPoint;
import org.jabogaf.core.state.CachedValueMap;
import org.jabogaf.core.util.ParameterForCacheOfTwoFields;
import org.jabogaf.util.log.SLF4J;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.function.Function;

@GameScoped
public class LookPointCollectorBasic implements LookPointCollector {

    @Inject
    private LookPointCollectorCache lookPointCollectorCache;

    @Override
    public Resource collect(Lookable lookable, Field target) {
        return collect(lookable.getPosition(), target);
    }

    @Override
    public Resource collect(Field position, Field target) {
        return lookPointCollectorCache.get(new ParameterForCacheOfTwoFields(position, target));
    }

    @GameScoped
    public static class LookPointCollectorCache extends CachedValueMap<LookPoint, ParameterForCacheOfTwoFields> {

        @Inject
        @SLF4J
        private Logger log;

        @Inject
        private BoardManager boardManager;

        @Override
        protected Function<ParameterForCacheOfTwoFields, LookPoint> create() {
            return parameter -> new LookPoint(boardManager.getBoard().getLayout().getAllLayoutActionImpacts(parameter.getPosition(), parameter.getTarget()).stream()
                    .mapToInt(lai -> lai.lookCost().getAmount())
                    .sum());
        }

        @Override
        protected Logger log() {
            return log;
        }
    }
}
