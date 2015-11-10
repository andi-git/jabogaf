package org.jabogaf.core.behavior.look;

import org.jabogaf.api.behavior.look.LookPath;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.api.board.layout.LayoutActionImpact;
import org.jabogaf.api.resource.Resource;
import org.jabogaf.core.gamecontext.GameContextBeanBasic;

import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class LookPathBasic extends GameContextBeanBasic<LookPath> implements LookPath {

    private final List<Field> fields = new ArrayList<>();

    public LookPathBasic(List<Field> fields) {
        checkNotNull(fields, "input-fields must not be null");
        checkArgument(fields.size() >= 2, "input-fields must have a minimum size of 2 but are {}", fields.size());
        this.fields.addAll(fields);
    }

    @Override
    public Field getPosition() {
        return fields.get(0);
    }

    @Override
    public Field getTarget() {
        return fields.get(fields.size() - 1);
    }

    @Override
    public List<LayoutActionImpact<?, ?>> getLayoutActionImpacts() {
        Set<LayoutActionImpact<?, ?>> layoutActionImpacts = new LinkedHashSet<>(); // for unique and sorted collection
        for (int i = 0; i < fields.size() - 1; i++) {
            Field field = fields.get(i);
            layoutActionImpacts.addAll(field.getGameSubjects());
            layoutActionImpacts.addAll(field.getGameObjects());
            for (FieldConnection fieldConnection : field.getFieldConnections()) {
                if (fieldConnection.containsAll(fields)) {
                    layoutActionImpacts.addAll(fieldConnection.getGameObjects());
                }
            }
        }
        return Collections.unmodifiableList(new ArrayList<>(layoutActionImpacts));
    }

    @Override
    public Resource cost() {
        return null;
    }

    @Override
    public List<Field> getFields() {
        return Collections.unmodifiableList(fields);
    }
}
