package at.ahammer.boardgame.common.board.layout.grid;

import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.field.FieldConnection;
import at.ahammer.boardgame.api.board.field.FieldGroup;
import at.ahammer.boardgame.common.object.field.*;
import at.ahammer.boardgame.core.board.field.FieldBasic;
import at.ahammer.boardgame.core.board.field.FieldConnectionBasic;
import at.ahammer.boardgame.core.board.field.FieldGroupBasic;

import java.util.Set;

/**
 * A simple example of a {@link GridLayoutCreationStrategy} to build {@link GridLayout} 5x5 with fields, door, walls,...
 * <p/>
 * This example can be used for tests.
 * FIXME: fix connections and groups (comment in)
 */
public class GridLayoutCreationExample extends GridLayoutCreationStrategy {

    public GridLayoutCreationExample() {
        super(6, 4);
    }

    @Override
    protected void createFieldConnectionObjects() {
        // room1
        fieldConnections.get(FieldConnection.class.getSimpleName() + ":1,0-2,0").addObjectOnConnection(new Wall("Wall:1,0-2,0"));
        fieldConnections.get(FieldConnection.class.getSimpleName() + ":1,1-2,1").addObjectOnConnection(new Door("Door:1,1-2,1"));
        fieldConnections.get(FieldConnection.class.getSimpleName() + ":1,1-1,2").addObjectOnConnection(new SecretDoor("SecDoor:1,1-1,2"));
        fieldConnections.get(FieldConnection.class.getSimpleName() + ":0,1-0,2").addObjectOnConnection(new Wall("Wall:0,1-0,2"));
        // room2
        Door openedDoor = new Door("Door:2,4-3,4");
        openedDoor.open();
        fieldConnections.get(FieldConnection.class.getSimpleName() + ":5,0-5,1").addObjectOnConnection(new Wall("Wall:5,0-5,1"));
        fieldConnections.get(FieldConnection.class.getSimpleName() + ":4,0-4,1").addObjectOnConnection(openedDoor);
        fieldConnections.get(FieldConnection.class.getSimpleName() + ":3,1-4,1").addObjectOnConnection(new Wall("Wall:2,3-3,3"));
        fieldConnections.get(FieldConnection.class.getSimpleName() + ":3,2-4,2").addObjectOnConnection(new Wall("Wall:3,2-4,4"));
        fieldConnections.get(FieldConnection.class.getSimpleName() + ":3,3-4,3").addObjectOnConnection(new Door("Door:3,3-4,3"));
        // room3
        fieldConnections.get(FieldConnection.class.getSimpleName() + ":0,2-0,3").addObjectOnConnection(new Wall("Wall:0,2-0,3"));
        fieldConnections.get(FieldConnection.class.getSimpleName() + ":1,2-1,3").addObjectOnConnection(new Door("Door:1,2-1,3"));
        fieldConnections.get(FieldConnection.class.getSimpleName() + ":2,2-2,3").addObjectOnConnection(new Wall("Wall:2,2-2,3"));
        fieldConnections.get(FieldConnection.class.getSimpleName() + ":3,2-3,3").addObjectOnConnection(new Wall("Wall:3,2-3,3"));
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
        fieldGroups.put(getFieldGroupId("room" + count), new Room(getFieldGroupId("room" + count), getAllFieldsInRectangle(0, 0, 1, 1)));
        count++;
        fieldGroups.put(getFieldGroupId("room" + count), new Room(getFieldGroupId("room" + count), getAllFieldsInRectangle(4, 1, 5, 3)));
        count++;
        fieldGroups.put(getFieldGroupId("room" + count), new Room(getFieldGroupId("room" + count), getAllFieldsInRectangle(0, 3, 3, 3)));
        count++;
        fieldGroups.put(getFieldGroupId("floor" + count), new Floor(getFieldGroupId("floor" + count), getAllFieldsInRectangle(0, 2, 3, 2)));
        count++;
        fieldGroups.put(getFieldGroupId("floor" + count), new Floor(getFieldGroupId("floor" + count), getAllFieldsInRectangle(2, 0, 3, 2)));
        count++;
        fieldGroups.put(getFieldGroupId("floor" + count), new Floor(getFieldGroupId("floor" + count), getAllFieldsInRectangle(2, 0, 5, 0)));
    }

    private String getFieldGroupId(String id) {
        return "FGroup:" + id;
    }

    private static class DummyField extends FieldBasic {

        protected DummyField(String id) {
            super(id);
        }
    }

    private static class DummyFieldConnection extends FieldConnectionBasic {

        public DummyFieldConnection(String id, Field leftHand, Field rightHand) {
            super(id, leftHand, rightHand);
        }
    }

    private static class DummyFieldGroup extends FieldGroupBasic {

        protected DummyFieldGroup(String id, Set<Field> fields) {
            super(id, fields);
        }
    }
}
