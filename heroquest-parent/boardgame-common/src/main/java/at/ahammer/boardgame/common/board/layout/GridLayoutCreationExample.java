package at.ahammer.boardgame.common.board.layout;

import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.field.FieldConnection;
import at.ahammer.boardgame.api.board.field.FieldGroup;
import at.ahammer.boardgame.common.object.field.*;

import java.util.Set;

/**
 * A simple example of a {@link GridLayoutCreationStrategy} to build {@link GridLayout} 5x5 with fields, door, walls,...
 * <p/>
 * This example can be used for tests.
 */
public class GridLayoutCreationExample extends GridLayoutCreationStrategy {

    public GridLayoutCreationExample() {
        super(5, 5);
    }

    @Override
    protected void createFieldConnectionObjects() {
        // room1
        fieldConnections.get(FieldConnection.class.getSimpleName() + ":1,3-1,4").addObjectOnConnection(new Door("Door:1,3-1,4"));
        fieldConnections.get(FieldConnection.class.getSimpleName() + ":0,3-0,4").addObjectOnConnection(new Wall("Wall:0,3-0,4"));
        fieldConnections.get(FieldConnection.class.getSimpleName() + ":1,3-2,3").addObjectOnConnection(new Wall("Wall:1,3-2,3"));
        fieldConnections.get(FieldConnection.class.getSimpleName() + ":1,2-2,2").addObjectOnConnection(new Wall("Wall:1,2-2,2"));
        fieldConnections.get(FieldConnection.class.getSimpleName() + ":0,0-0,1").addObjectOnConnection(new Wall("Wall:0,0-0,1"));
        fieldConnections.get(FieldConnection.class.getSimpleName() + ":1,0-1,1").addObjectOnConnection(new Wall("Wall:1,0-1,1"));
        fieldConnections.get(FieldConnection.class.getSimpleName() + ":1,1-2,1").addObjectOnConnection(new SecretDoor("SecretDoor:1,1-2,1"));
        // room2
        fieldConnections.get(FieldConnection.class.getSimpleName() + ":3,2-3,3").addObjectOnConnection(new Door("Door:3,2-3,3"));
        fieldConnections.get(FieldConnection.class.getSimpleName() + ":2,4-3,4").addObjectOnConnection(new Wall("Wall:2,4-3,4"));
        fieldConnections.get(FieldConnection.class.getSimpleName() + ":2,3-3,3").addObjectOnConnection(new Wall("Wall:2,3-3,3"));
        fieldConnections.get(FieldConnection.class.getSimpleName() + ":4,2-4,3").addObjectOnConnection(new Wall("Wall:4,2-4,3"));
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
        int count = 0;
        fieldGroups.put(getFieldGroupId(++count), new Room(getFieldGroupId(count), getAllFieldsInRectangle(0, 1, 1, 3)));
        fieldGroups.put(getFieldGroupId(++count), new Room(getFieldGroupId(count), getAllFieldsInRectangle(3, 3, 4, 4)));
        fieldGroups.put(getFieldGroupId(++count), new Floor(getFieldGroupId(count), getAllFieldsInRectangle(0, 0, 1, 1)));
        fieldGroups.put(getFieldGroupId(++count), new Floor(getFieldGroupId(count), getAllFieldsInRectangle(2, 0, 4, 2)));
        fieldGroups.put(getFieldGroupId(++count), new Floor(getFieldGroupId(count), getAllFieldsInRectangle(0, 4, 2, 4)));
        fieldGroups.put(getFieldGroupId(++count), new Floor(getFieldGroupId(count), getAllFieldsInRectangle(2, 3, 2, 4)));
    }

    private String getFieldGroupId(int count) {
        return FieldGroup.class.getSimpleName() + ":" + count;
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
