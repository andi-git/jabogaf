package org.jabogaf.core.behavior.move;

import org.jabogaf.api.behavior.move.MovePath;
import org.jabogaf.api.behavior.move.Moveable;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.api.board.field.FieldGroup;
import org.jabogaf.api.cdi.GameScoped;
import org.jabogaf.api.object.GameObject;
import org.jabogaf.api.resource.Resource;
import org.jabogaf.api.resource.ResourceHolder;
import org.jabogaf.api.state.GameState;
import org.jabogaf.api.subject.GameSubject;
import org.jabogaf.core.resource.MovePoint;
import org.jabogaf.core.resource.ResourcesBasic;
import org.jabogaf.core.state.CachedValueMap;
import org.jabogaf.util.log.SLF4J;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Create and cache all moveable {@link Field}s of a {@link Moveable} and a {@link ResourceHolder}, i.e. a {@link
 * GameSubject}.
 */
@GameScoped
public class MoveableFields extends CachedValueMap<List<MovePath>, MoveableFields.Parameter> {

    @Inject
    private MovePointCollector movePointCollector;

    @Inject
    @SLF4J
    private Logger log;

    private Set<ResolvedField> resolvedFields = new HashSet<>();

    private Set<UnresolvedField> unresolvedFields = new HashSet<>();

    @Override
    protected Logger log() {
        return log;
    }

    private List<MovePath> convertToSortedList(Set<ResolvedField> resolvedFields) {
        return resolvedFields.stream().map(ResolvedField::getMovePath).sorted((mp1, mp2) -> mp1.cost().getAmount() - mp2.cost().getAmount()).collect(Collectors.toList());
    }

    private void checkFieldsToResolve(ResolvedField resolvedField, Moveable moveable) {
        log.debug("  check fields to resolve connected to {}", resolvedField);
        for (Field possibleFieldToResolve : resolvedField.getConnectedFields()) {
            log.debug("    possible field is {}", possibleFieldToResolve);
            if (!resolvedFieldsContains(possibleFieldToResolve) && !isMoveBlocked(moveable, resolvedField, possibleFieldToResolve)) {
                UnresolvedField unresolvedField = new UnresolvedField(possibleFieldToResolve, resolvedField.getMovePath(), movePointCollector);
                log.debug("    possible unresolved field is {}", unresolvedField);
                if (!unresolvedFieldsContains(unresolvedField)) {
                    log.debug("    new unresolved field {}", unresolvedField);
                    unresolvedFields.add(unresolvedField);
                } else {
                    log.debug("    field is already unresolved", unresolvedField);
                    if (unresolvedFields.stream().filter(f -> f.equals(unresolvedField)).findFirst().get().movementCost().greater(unresolvedField.movementCost())) {
                        log.debug("    new unresolved is cheaper");
                        unresolvedFields.remove(unresolvedField);
                        unresolvedFields.add(unresolvedField);
                    }
                }
            }
        }
    }

    private boolean isMoveBlocked(Moveable moveable, Field position, Field target) {
        return moveable.cloneMoveable(position).canMove(target, new ResourcesBasic()).isBlocked();
    }

    private boolean resolvedFieldsContains(Field field) {
        return resolvedFields.stream().anyMatch(f -> f.getTarget().equals(field));
    }

    private boolean unresolvedFieldsContains(UnresolvedField field) {
        return unresolvedFields.contains(field);
    }

    private Set<UnresolvedField> unresolvedFieldsReachableWith(MovePoint movePoint) {
        return unresolvedFields.stream().filter(f -> f.movementCost().lesserEquals(movePoint)).collect(Collectors.toSet());
    }

    private MovePoint mp(int movePoint) {
        return new MovePoint(movePoint);
    }

    @Override
    protected Function<Parameter, List<MovePath>> create() {
        return parameter -> {
            log.debug("clear maps");
            resolvedFields.clear();
            unresolvedFields.clear();
            log.debug("add start position " + parameter.getMoveable().getPosition());
            ResolvedField firstResolvedField = new ResolvedField(new MovePathBasic(parameter.getMoveable().getPosition(), parameter.getMoveable().getPosition(), mp(0)));
            resolvedFields.add(firstResolvedField);
            checkFieldsToResolve(firstResolvedField, parameter.getMoveable());
            for (int movePoint = 1; movePoint <= parameter.getResourceHolder().amountOf(MovePoint.class); movePoint++) {
                MovePoint mp = mp(movePoint);
                log.debug("check for movePoint {}", movePoint);
                while (!unresolvedFieldsReachableWith(mp).isEmpty()) {
                    log.debug("resolved fields   " + resolvedFields);
                    log.debug("unresolved fields " + unresolvedFields);
                    Set<UnresolvedField> unresolvedFieldsToResolve = unresolvedFields.stream().filter(f -> f.movementCost().lesserEquals(mp)).collect(Collectors.toSet());
                    log.debug("  there are some unresolved-fields that has to be resolved {}", unresolvedFieldsToResolve);
                    for (UnresolvedField unresolvedFieldToResolve : unresolvedFieldsToResolve) {
                        unresolvedFields.remove(unresolvedFieldToResolve);
                        ResolvedField resolvedField = new ResolvedField(unresolvedFieldToResolve);
                        log.debug("  add field to resolve {}", resolvedField);
                        resolvedFields.add(resolvedField);
                        checkFieldsToResolve(resolvedField, parameter.getMoveable());
                    }
                }
            }
            resolvedFields.remove(firstResolvedField);
            return convertToSortedList(resolvedFields);
        };
    }

    private static class ResolvedField implements Field {

        private final MovePath movePath;

        public ResolvedField(MovePath movePath) {
            this.movePath = movePath;
        }

        public ResolvedField(UnresolvedField unresolvedField) {
            this.movePath = new MovePathBasic(unresolvedField.getMovePathBefore(), unresolvedField.getTarget());
        }

        public Field getTarget() {
            return movePath.getTarget();
        }

        public MovePath getMovePath() {
            return movePath;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof ResolvedField) {
                return getTarget().equals(((ResolvedField) o).getTarget());
            } else if (o instanceof UnresolvedField) {
                return getTarget().equals(((UnresolvedField) o).getTarget());
            } else {
                return getTarget().equals(o);
            }
        }

        @Override
        public int hashCode() {
            return getTarget().hashCode();
        }

        @Override
        public String toString() {
            return getTarget().toString();
        }

        @Override
        public boolean isConnected(Field target) {
            return getMovePath().getTarget().isConnected(target);
        }

        @Override
        public FieldConnection getConnectionTo(Field target) {
            return getMovePath().getTarget().getConnectionTo(target);
        }

        @Override
        public List<GameSubject> getGameSubjects() {
            return getMovePath().getTarget().getGameSubjects();
        }

        @Override
        public List<GameObject> getGameObjects() {
            return getMovePath().getTarget().getGameObjects();
        }

        @Override
        public List<FieldGroup> getFieldsGroups() {
            return getMovePath().getTarget().getFieldsGroups();
        }

        @Override
        public Set<Field> getConnectedFields() {
            return getMovePath().getTarget().getConnectedFields();
        }

        @Override
        public Resource movementCost() {
            return getMovePath().getTarget().movementCost();
        }

        @Override
        public String getId() {
            return getMovePath().getTarget().getId();
        }

        @Override
        public GameState getState() {
            return getTarget().getState();
        }

        @Override
        public int compareTo(@SuppressWarnings("NullableProblems") Object o) {
            //noinspection unchecked
            return getMovePath().getTarget().compareTo(o);
        }
    }

    private static class UnresolvedField implements Field {

        private final MovePointCollector movePointCollector;

        private final Field target;

        private final MovePath movePathBefore;

        public UnresolvedField(Field target, MovePath movePathBefore, MovePointCollector movePointCollector) {
            this.target = target;
            this.movePathBefore = movePathBefore;
            this.movePointCollector = movePointCollector;
        }

        public Field getTarget() {
            return target;
        }

        public Field getLastFieldBeforeTarget() {
            return movePathBefore.getTarget();
        }

        public MovePath getMovePathBefore() {
            return movePathBefore;
        }

        public Resource costBefore() {
            return movePathBefore.cost();
        }

        public Resource costFromLastFieldToTarget() {
            return movePointCollector.collect(getLastFieldBeforeTarget(), target);
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof ResolvedField) {
                return getTarget().equals(((ResolvedField) o).getTarget());
            } else if (o instanceof UnresolvedField) {
                return getTarget().equals(((UnresolvedField) o).getTarget());
            } else {
                return getTarget().equals(o);
            }
        }

        @Override
        public int hashCode() {
            return getTarget().hashCode();
        }

        @Override
        public String toString() {
            return getTarget().toString();
        }

        @Override
        public boolean isConnected(Field target) {
            return getTarget().isConnected(target);
        }

        @Override
        public FieldConnection getConnectionTo(Field target) {
            return getTarget().getConnectionTo(target);
        }

        @Override
        public List<GameSubject> getGameSubjects() {
            return getTarget().getGameSubjects();
        }

        @Override
        public List<GameObject> getGameObjects() {
            return getTarget().getGameObjects();
        }

        @Override
        public List<FieldGroup> getFieldsGroups() {
            return getTarget().getFieldsGroups();
        }

        @Override
        public Set<Field> getConnectedFields() {
            return getTarget().getConnectedFields();
        }

        @Override
        public Resource movementCost() {
            return costBefore().add(costFromLastFieldToTarget());
        }

        @Override
        public String getId() {
            return getTarget().getId();
        }

        @Override
        public GameState getState() {
            return getTarget().getState();
        }

        @Override
        public int compareTo(@SuppressWarnings("NullableProblems") Object o) {
            //noinspection unchecked
            return getTarget().compareTo(o);
        }
    }

    public static class Parameter {

        private final Moveable moveable;

        private final ResourceHolder resourceHolder;

        public Parameter(GameSubject gameSubject) {
            this(gameSubject, gameSubject);
        }

        public Parameter(Moveable moveable, ResourceHolder resourceHolder) {
            this.moveable = moveable;
            this.resourceHolder = resourceHolder;
        }

        public Moveable getMoveable() {
            return moveable;
        }

        public ResourceHolder getResourceHolder() {
            return resourceHolder;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Parameter parameter = (Parameter) o;

            return moveable.equals(parameter.moveable) && resourceHolder.equals(parameter.resourceHolder);

        }

        @Override
        public int hashCode() {
            int result = moveable.hashCode();
            result = 31 * result + resourceHolder.hashCode();
            return result;
        }
    }
}
