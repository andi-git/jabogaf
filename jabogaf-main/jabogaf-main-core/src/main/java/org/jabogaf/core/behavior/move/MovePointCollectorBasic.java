package org.jabogaf.core.behavior.move;

import org.jabogaf.api.behavior.move.Moveable;
import org.jabogaf.api.board.BoardManager;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.gamecontext.GameScoped;
import org.jabogaf.api.resource.Resource;
import org.jabogaf.core.resource.MovePoint;
import org.jabogaf.core.state.CachedValueMap;
import org.jabogaf.util.log.SLF4J;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.function.Function;

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
        if (!position.isConnected(target)) {
            throw new IllegalStateException(position + " and" + target + " are not connected");
        }
        return movePointCollectorCache.get(new MovePointCollectorCache.Parameter(position, target));
    }

    @GameScoped
    public static class MovePointCollectorCache extends CachedValueMap<MovePoint, MovePointCollectorCache.Parameter> {

        @Inject
        @SLF4J
        private Logger log;

        @Inject
        private BoardManager boardManager;

        @Override
        protected Function<MovePointCollectorCache.Parameter, MovePoint> create() {
            return parameter -> {
                int mpFieldConnectionObjects = boardManager.getAllFieldConnectionObjects(parameter.getPosition(), parameter.getTarget()).stream()
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

        public static class Parameter {

            private final Field position;

            private final Field target;

            public Parameter(Field position, Field target) {
                this.position = position;
                this.target = target;
            }

            public Field getPosition() {
                return position;
            }

            public Field getTarget() {
                return target;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Parameter parameter = (Parameter) o;
                return position.equals(parameter.position) && target.equals(parameter.target);
            }

            @Override
            public int hashCode() {
                int result = position.hashCode();
                result = 31 * result + target.hashCode();
                return result;
            }

            @Override
            public String toString() {
                return "Parameter{" +
                        "position=" + position +
                        ", target=" + target +
                        '}';
            }
        }
    }
}
