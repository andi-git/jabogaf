package org.jabogaf.core.board.layout;

import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.common.board.layout.grid.GridLayoutCreationStrategy;
import org.jabogaf.core.behavior.look.LookPathBasic;
import org.jabogaf.core.board.field.FieldBasic;
import org.jabogaf.core.board.field.FieldConnectionBasic;

public class DummyLayout extends LayoutBasic {

    protected DummyLayout() {
        super("DummyLayout" + randomId(), new GridLayoutCreationStrategy(2, 2,
                (x, y) -> new FieldBasic(Field.class.getSimpleName() + randomId() + ":" + x + "," + y),
                (field1, field2) -> new FieldConnectionBasic(FieldConnection.class.getSimpleName() + randomId() + ":" + field1.getId() + "-" + field2.getId(), field1, field2),
                LookPathBasic::new
        ));
    }
}
