package at.ahammer.boardgame.core.behavior.move;

import at.ahammer.boardgame.api.behavior.move.MovePath;
import at.ahammer.boardgame.api.behavior.move.Moveable;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.field.FieldConnection;
import at.ahammer.boardgame.api.board.field.FieldGroup;
import at.ahammer.boardgame.api.cdi.GameContextBean;
import at.ahammer.boardgame.api.cdi.GameScoped;
import at.ahammer.boardgame.api.resource.Resource;
import at.ahammer.boardgame.api.resource.ResourceHolder;
import at.ahammer.boardgame.api.subject.GameSubject;
import at.ahammer.boardgame.core.resource.MovePoint;
import at.ahammer.boardgame.core.resource.ResourcesBasic;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

@GameScoped
public class MoveableFieldCollectorBasic implements MoveableFieldsCollector {

    @Inject
    private MovePointCollector movePointCollector;

    @Inject
    private Logger log;

    private Set<ResolvedField> resolvedFields = new HashSet<>();

    private Set<UnresolvedField> unresolvedFields = new HashSet<>();

    @Override
    public List<MovePath> getMovableFields(Moveable moveable, ResourceHolder resourceHolder) {
        if (true) { // TODO implement cache
            log.debug("clear maps");
            resolvedFields.clear();
            unresolvedFields.clear();
            log.debug("add start position " + moveable.getPosition());
            ResolvedField firstResolvedField = new ResolvedField(new MovePathBasic(moveable.getPosition(), moveable.getPosition(), mp(0)));
            resolvedFields.add(firstResolvedField);
            checkFieldsToResolve(firstResolvedField, moveable);
            for (int movePoint = 1; movePoint <= resourceHolder.amountOf(MovePoint.class); movePoint++) {
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
                        checkFieldsToResolve(resolvedField, moveable);
                    }
                }
            }
            resolvedFields.remove(firstResolvedField);
        }
        return convertToSortedList(resolvedFields);
    }

    private List<MovePath> convertToSortedList(Set<ResolvedField> resolvedFields) {
        return resolvedFields.stream().map(f -> f.getMovePath()).sorted((mp1, mp2) -> mp1.cost().getAmount() - mp2.cost().getAmount()).collect(Collectors.toList());
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

    private boolean unresolvedFieldsContains(Field field) {
        return unresolvedFields.stream().anyMatch(f -> f.getTarget().equals(field));
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

        public Resource getCost() {
            return movePath.cost();
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
        public List<FieldGroup> getFieldsGroups() {
            return getMovePath().getTarget().getFieldsGroups();
        }

        @Override
        public Set<FieldConnection> getFieldConnections() {
            return getMovePath().getTarget().getFieldConnections();
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
        public int compareTo(GameContextBean o) {
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
        public List<FieldGroup> getFieldsGroups() {
            return getTarget().getFieldsGroups();
        }

        @Override
        public Set<FieldConnection> getFieldConnections() {
            return getTarget().getFieldConnections();
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
        public int compareTo(GameContextBean o) {
            return getTarget().compareTo(o);
        }
    }
}
