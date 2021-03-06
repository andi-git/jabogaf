package org.jabogaf.core.behavior.move;

import org.jabogaf.api.behavior.move.CanMoveReport;
import org.jabogaf.api.behavior.move.MovePath;
import org.jabogaf.api.behavior.move.Moveable;
import org.jabogaf.api.board.field.ContainsGameObjects;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.api.board.field.FieldGroup;
import org.jabogaf.api.gamecontext.FireEvent;
import org.jabogaf.api.gamecontext.GameContextBean;
import org.jabogaf.api.gamecontext.GameScoped;
import org.jabogaf.api.object.GameObject;
import org.jabogaf.api.resource.Resource;
import org.jabogaf.api.resource.ResourceHolder;
import org.jabogaf.api.subject.GameSubject;
import org.jabogaf.api.subject.SetterOfPosition;
import org.jabogaf.core.resource.MovePoint;
import org.jabogaf.core.state.CachedValueMap;
import org.jabogaf.core.util.KeyFor2FieldsCreator;
import org.jabogaf.util.log.LogWrapper;
import org.jabogaf.util.log.SLF4J;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private LogWrapper log;

    @Inject
    private MovePointHolder movePointHolder;

    @Inject
    private KeyFor2FieldsCreator keyFor2FieldsCreator;

    private Set<ResolvedField> resolvedFields = new HashSet<>();

    private Set<UnresolvedField> unresolvedFields = new HashSet<>();

    private Map<String, CanMoveReport> canMoveReportCache = new HashMap<>();

    @Override
    protected Logger log() {
        return log.log();
    }

    private List<MovePath> convertToSortedList(Stream<ResolvedField> resolvedFields) {
        return resolvedFields
                .map(ResolvedField::getMovePath)
                .sorted((mp1, mp2) -> mp1.cost().getAmount() - mp2.cost().getAmount())
                .collect(Collectors.toList());
    }

    private void checkFieldsToResolve(ResolvedField resolvedField, MoveableWrapper moveableWrapper, ResourceHolder resourceHolder) {
        log.debug("  check fields to resolve connected to {}", resolvedField);
        for (Field possibleFieldToResolve : resolvedField.getConnectedFields()) {
            log.debug("    possible field is {}", possibleFieldToResolve);
            CanMoveReport canMoveReportToPossibleField = canMoveReport(moveableWrapper, resolvedField, possibleFieldToResolve, resourceHolder);
            if (!resolvedFieldsContains(possibleFieldToResolve) && !canMoveReportToPossibleField.isBlocked()) {
                UnresolvedField unresolvedField = new UnresolvedField(canMoveReportToPossibleField, resolvedField.getMovePath());
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

    private CanMoveReport canMoveReport(MoveableWrapper moveableWrapper, Field position, Field target, ResourceHolder resourceHolder) {
        String key = keyFor2FieldsCreator.generateKey(position, target);
        if (!canMoveReportCache.containsKey(key)) {
            canMoveReportCache.put(key, moveableWrapper.getClonedMoveable(position).canMove(target, resourceHolder));
        }
        return canMoveReportCache.get(key);
    }

    private boolean resolvedFieldsContains(Field field) {
        return resolvedFields.stream().anyMatch(f -> f.getTarget().equals(field));
    }

    private boolean unresolvedFieldsContains(UnresolvedField field) {
        return unresolvedFields.contains(field);
    }

    private Set<UnresolvedField> unresolvedFieldsReachableWith(MovePoint movePoint) {
        return unresolvedFields.stream()
                .filter(f -> f.movementCost().lesserEquals(movePoint))
                .collect(Collectors.toSet());
    }

    private MovePoint mp(int movePoint) {
        return movePointHolder.get(movePoint);
    }

    @Override
    protected Function<Parameter, List<MovePath>> create() {
        return parameter -> {
            MoveableWrapper moveableWrapper = new MoveableWrapper(parameter.getMoveable());
            log.debug("clear maps");
            resolvedFields.clear();
            unresolvedFields.clear();
            canMoveReportCache.clear();
            log.debug("add start position " + parameter.getMoveable().getPosition());
            ResolvedField firstResolvedField = new ResolvedField(new MovePathBasic(parameter.getMoveable().getPosition(), parameter.getMoveable().getPosition(), mp(0)), false);
            resolvedFields.add(firstResolvedField);
            checkFieldsToResolve(firstResolvedField, moveableWrapper, parameter.getResourceHolder());
            for (int movePoint = 1; movePoint <= parameter.getResourceHolder().amountOf(MovePoint.class); movePoint++) {
                MovePoint mp = mp(movePoint);
                log.debug("check for movePoint {}", movePoint);
                while (!unresolvedFieldsReachableWith(mp).isEmpty()) {
                    log.debug("resolved fields   " + resolvedFields);
                    log.debug("unresolved fields " + unresolvedFields);
                    Set<UnresolvedField> unresolvedFieldsToResolve = unresolvedFieldsReachableWith(mp);
                    log.debug("  there are some unresolved-fields that has to be resolved {}", () -> unresolvedFieldsToResolve);
                    for (UnresolvedField unresolvedFieldToResolve : unresolvedFieldsToResolve) {
                        unresolvedFields.remove(unresolvedFieldToResolve);
                        ResolvedField resolvedField = new ResolvedField(unresolvedFieldToResolve);
                        log.debug("  add field to resolve {}", resolvedField);
                        resolvedFields.add(resolvedField);
                        checkFieldsToResolve(resolvedField, moveableWrapper, parameter.getResourceHolder());
                    }
                }
            }
            resolvedFields.remove(firstResolvedField);
            return convertToSortedList(filterMovePathsWhereLastFieldIsNotPossible());
        };
    }

    private Stream<ResolvedField> filterMovePathsWhereLastFieldIsNotPossible() {
        return resolvedFields.stream().filter(ResolvedField::isAbleToEnd);
    }

    private static class ResolvedField implements Field {

        private final MovePath movePath;

        private final boolean ableToEnd;

        public ResolvedField(MovePath movePath, boolean ableToEnd) {
            this.movePath = movePath;
            this.ableToEnd = ableToEnd;
        }

        public ResolvedField(UnresolvedField unresolvedField) {
            this.movePath = new MovePathBasic(unresolvedField.getMovePathBefore(), unresolvedField.getTarget());
            this.ableToEnd = unresolvedField.isAbleToEnd();
        }

        public Field getTarget() {
            return movePath.getTarget();
        }

        public MovePath getMovePath() {
            return movePath;
        }

        public boolean isAbleToEnd() {
            return ableToEnd;
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
        public Set<FieldConnection> getFieldConnections() {
            return getTarget().getFieldConnections();
        }

        @Override
        public List<GameSubject> getGameSubjects() {
            return getMovePath().getTarget().getGameSubjects();
        }

        @Override
        public List<GameObject<? extends ContainsGameObjects>> getGameObjects() {
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
        public void setMovementCost(Resource resource) {
            getTarget().setMovementCost(resource);
        }

        @Override
        public String getId() {
            return getMovePath().getTarget().getId();
        }

        @Override
        public int compareTo(@SuppressWarnings("NullableProblems") GameContextBean o) {
            return getMovePath().getTarget().compareTo(o);
        }
    }

    private static class UnresolvedField implements Field {

        private final Field target;

        private final MovePath movePathBefore;

        private final boolean ableToEnd;

        private final Resource costFromLastFieldToTarget;

        public UnresolvedField(CanMoveReport canMoveReport, MovePath movePathBefore) {
            this.target = canMoveReport.getTarget();
            this.movePathBefore = movePathBefore;
            this.ableToEnd = canMoveReport.isAbleToEnd();
            this.costFromLastFieldToTarget = canMoveReport.moveCost();
        }

        public Field getTarget() {
            return target;
        }

        public MovePath getMovePathBefore() {
            return movePathBefore;
        }

        public boolean isAbleToEnd() {
            return ableToEnd;
        }

        public Resource costBefore() {
            return movePathBefore.cost();
        }

        public Resource costFromLastFieldToTarget() {
            return costFromLastFieldToTarget;
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
        public Set<FieldConnection> getFieldConnections() {
            return getTarget().getFieldConnections();
        }

        @Override
        public List<GameSubject> getGameSubjects() {
            return getTarget().getGameSubjects();
        }

        @Override
        public List<GameObject<? extends ContainsGameObjects>> getGameObjects() {
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
        public void setMovementCost(Resource resource) {
            getTarget().setMovementCost(resource);
        }

        @Override
        public String getId() {
            return getTarget().getId();
        }

        @Override
        public int compareTo(@SuppressWarnings("NullableProblems") GameContextBean o) {
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

    private static class MoveableWrapper {

        private final Moveable moveable;

        private final Moveable clonedMoveable;

        private final SetterOfPosition setterForPositionOfCloneable;

        public MoveableWrapper(Moveable moveable) {
            this.moveable = moveable;
            clonedMoveable = moveable.cloneMoveable();
            try {
                Method method = clonedMoveable.getClass().getDeclaredMethod("getInternalSetterOfPosition");
                method.setAccessible(true);
                setterForPositionOfCloneable = (SetterOfPosition) method.invoke(clonedMoveable);
            } catch (Exception e) {
                throw new RuntimeException("unable to create " + getClass().getName(), e);
            }
        }

        public Moveable getMoveable() {
            return moveable;
        }

        public Moveable getClonedMoveable(Field position) {
            setterForPositionOfCloneable.setPosition(position);
            return clonedMoveable;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MoveableWrapper that = (MoveableWrapper) o;
            return moveable.equals(that.moveable) && clonedMoveable.equals(that.clonedMoveable) && setterForPositionOfCloneable.equals(that.setterForPositionOfCloneable);
        }

        @Override
        public int hashCode() {
            int result = moveable.hashCode();
            result = 31 * result + clonedMoveable.hashCode();
            result = 31 * result + setterForPositionOfCloneable.hashCode();
            return result;
        }
    }

    @ApplicationScoped
    public static class MovePointHolder {

        private final Map<Integer, MovePoint> movePointMap = new HashMap<>();

        @PostConstruct
        private void init() {
            for (int i = 0; i < 1000; i++) {
                movePointMap.put(i, new MovePoint(i, FireEvent.None));
            }
        }

        public MovePoint get(int i) {
            if (movePointMap.containsKey(i)) {
                return movePointMap.get(i);
            } else {
                return new MovePoint(i, FireEvent.None);
            }
        }
    }
}
