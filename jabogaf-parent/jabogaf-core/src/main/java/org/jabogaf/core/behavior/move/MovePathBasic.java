package org.jabogaf.core.behavior.move;

import org.jabogaf.api.behavior.move.MovePath;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.resource.Resource;
import org.jabogaf.core.cdi.GameContextBeanBasic;
import org.jabogaf.core.resource.MovePoint;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public class MovePathBasic extends GameContextBeanBasic implements MovePath {

    private final Field position;

    private final List<Field> fields = new ArrayList<>();

    private Resource cost;

    @Inject
    private MovePointCollector movePointCollector;

    public MovePathBasic(Field position, Field target, Resource cost) {
        this.position = position;
        this.fields.add(target);
        this.cost = cost;
    }

    public MovePathBasic(MovePath movePathBeforeTarget, Field target, Resource cost) {
        this.position = movePathBeforeTarget.getPosition();
        this.fields.addAll(movePathBeforeTarget.getPathFields());
        this.fields.add(target);
        this.cost = cost;
    }

    public MovePathBasic(MovePath movePathBeforeTarget, Field target) {
        this.position = movePathBeforeTarget.getPosition();
        this.fields.addAll(movePathBeforeTarget.getPathFields());
        this.fields.add(target);
        collectMovePoints();
    }

    public MovePathBasic(Field position, Field target) {
        this.position = position;
        this.fields.add(target);
        collectMovePoints();
    }

    public MovePathBasic(Field position, Field... fields) {
        this.position = position;
        for (Field field : fields) {
            this.fields.add(field);
        }
        collectMovePoints();
    }

    @Override
    public Field getPosition() {
        return position;
    }

    @Override
    public Field getTarget() {
        if (fields.size() > 0) {
            return fields.get(fields.size() - 1);
        } else {
            return position;
        }
    }

    @Override
    public List<Field> getPathFields() {
        if (position.equals(getTarget())) {
            return Collections.unmodifiableList(new ArrayList<>());
        }
        return Collections.unmodifiableList(fields);
    }

    @Override
    public Resource cost() {
        return cost;
    }

    @Override
    public boolean contains(Field field) {
        return fields.contains(field);
    }

    @Override
    public void update(MovePath movePath) {
        if (contains(movePath.getTarget())) {
            // create a copy of the current fields
            List<Field> copyOfFields = new ArrayList<>(fields);
            // clear current fields and add all fields from the new path
            fields.clear();
            fields.addAll(movePath.getPathFields());
            // add all fields missing to reach the target
            boolean addField = false;
            for (Field field : copyOfFields) {
                if (addField) {
                    fields.add(field);
                } else if (field.equals(movePath.getTarget())) {
                    addField = true;
                }
            }
            collectMovePoints();
        }
    }

    private void collectMovePoints() {
        List<Field> fieldsWithPosition = getFieldsWithPosition();
        int newCost = 0;
        for (int i = 0; i < fieldsWithPosition.size() - 1; i++) {
            newCost += movePointCollector.collect(fieldsWithPosition.get(i), fieldsWithPosition.get(i + 1)).getAmount();
        }
        cost = new MovePoint(newCost);
    }

    private List<Field> getFieldsWithPosition() {
        List<Field> fieldsWithPosition = new ArrayList<>();
        fieldsWithPosition.add(position);
        fieldsWithPosition.addAll(fields);
        return fieldsWithPosition;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("movePath:");
        sb.append(position.getId());
        sb.append("-");
        fields.stream().forEach(f -> {
            sb.append(f.getId());
            sb.append("-");
        });
        sb.append(",");
        sb.append(cost.getAmount());
        return sb.toString();
    }
}
