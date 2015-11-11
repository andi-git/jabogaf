package org.jabogaf.core.behavior.move;

import com.google.common.base.Preconditions;
import org.jabogaf.api.behavior.move.Moveable;
import org.jabogaf.api.board.BoardManager;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.gamecontext.GameScoped;
import org.jabogaf.api.resource.Resource;
import org.jabogaf.core.resource.MovePoint;
import org.jabogaf.core.state.CachedValueMap;
import org.jabogaf.core.util.ParameterForCacheOfTwoFields;
import org.jabogaf.util.log.SLF4J;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.function.Function;

import static com.google.common.base.Preconditions.checkState;

@GameScoped
public class MovePointCollectorBasic implements MovePointCollector {

    @Inject
    private MovePointCollectorCache movePointCollectorCache;

    @Override
    public Resource collect(Moveable moveable, Field target) {
        return collect(moveable.getPosition(), target);
    }

    @Override
    public Resource collect(Field position, Field target) {
        checkState(position.isConnected(target), position + " and" + target + " are not connected");
        return movePointCollectorCache.get(new ParameterForCacheOfTwoFields(position, target));
    }

    @GameScoped
    public static class MovePointCollectorCache extends CachedValueMap<MovePoint, ParameterForCacheOfTwoFields> {

        @Inject
        @SLF4J
        private Logger log;

        @Inject
        private BoardManager boardManager;

        @Override
        protected Function<ParameterForCacheOfTwoFields, MovePoint> create() {
            return parameter -> {
                int mpFieldConnectionObjects = boardManager.getAllGameObjectsOnFieldConnection(parameter.getPosition(), parameter.getTarget()).stream()
                        .mapToInt(fco -> fco.movementCost().getAmount())
                        .sum();
                int mpField = parameter.getTarget().movementCost().getAmount();
                return new MovePoint(mpFieldConnectionObjects + mpField);
            };
        }

        @Override
        protected Logger log() {
            return log;
        }
    }
}
