package at.ahammer.boardgame.board;

import at.ahammer.boardgame.object.field.Field;
import at.ahammer.boardgame.object.field.FieldConnection;
import at.ahammer.boardgame.object.field.FieldConnectionObject;
import at.ahammer.boardgame.object.field.FieldGroup;
import at.ahammer.heroquest.object.field.Door;
import at.ahammer.heroquest.object.field.Wall;

import java.util.Set;

/**
 * Created by andreas on 8/24/14.
 */
public class GridLayoutCreationDummy extends GridLayoutCreation {

    public GridLayoutCreationDummy() {
        super(5, 5);
    }

    @Override
    protected void createFieldConnectionObjects() {
        fieldConnections.get(FieldConnection.class.getSimpleName() + ":3,0-4,0").addObjectOnConnection(new Wall("Wall:3,0-4,0"));
        fieldConnections.get(FieldConnection.class.getSimpleName() + ":3,1-4,1").addObjectOnConnection(new Door("Door:3,1-4,1"));
    }

    @Override
    protected Field createDefaultField(String id) {
        return new DummyField(id);
    }

    @Override
    protected FieldConnection createDefaultFieldConnection(String id, Field field1, Field field2) {
        return new DummyFieldConnection(id, field1, field2);
    }

    @Override
    protected void createFieldGroups() {
        String id = FieldGroup.class.getSimpleName() + ":1";
        fieldGroups.put(id, new DummyFieldGroup(id, getAllFieldsInRectangle(1, 1, 2, 3)));
    }

    private static class DummyField extends Field {

        protected DummyField(String id) {
            super(id);
        }
    }

    private static class DummyFieldConnection extends FieldConnection {

        public DummyFieldConnection(String id, Field leftHand, Field rightHand) {
            super(id, leftHand, rightHand);
        }
    }

    private static class DummyFieldGroup extends FieldGroup {

        protected DummyFieldGroup(String id, Set<Field> fields) {
            super(id, fields);
        }
    }
}
