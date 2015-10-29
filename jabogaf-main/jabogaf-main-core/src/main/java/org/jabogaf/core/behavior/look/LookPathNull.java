package org.jabogaf.core.behavior.look;

import org.jabogaf.api.behavior.look.LookPath;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.api.board.layout.LayoutActionImpact;
import org.jabogaf.api.resource.Resource;
import org.jabogaf.core.board.field.FieldNull;
import org.jabogaf.core.gamecontext.GameContextBeanBasic;

import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class LookPathNull extends GameContextBeanBasic<LookPathNull> implements LookPath {

    @Override
    public Field getPosition() {
        return new FieldNull();
    }

    @Override
    public Field getTarget() {
        return new FieldNull();
    }

    @Override
    public List<LayoutActionImpact<?, ?>> getLayoutActionImpacts() {
        return new ArrayList<>();
    }

    @Override
    public Resource cost() {
        return null;
    }

    @Override
    public List<Field> getFields() {
        return new ArrayList<>();
    }
}
