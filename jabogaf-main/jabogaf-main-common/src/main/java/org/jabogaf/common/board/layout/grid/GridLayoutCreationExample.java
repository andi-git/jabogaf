package org.jabogaf.common.board.layout.grid;

import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.common.object.field.*;
import org.jabogaf.core.behavior.look.LookPathBasic;
import org.jabogaf.core.board.field.FieldBasic;
import org.jabogaf.core.board.field.FieldConnectionBasic;

/**
 * A simple example of a {@link GridLayoutCreationStrategy} to build {@link GridLayout} 5x5 with fields, door,
 * walls,...
 * <p>
 * This example can be used for tests.
 */
public class GridLayoutCreationExample extends GridLayoutCreationStrategy {

    public GridLayoutCreationExample() {
        super(6, 4,
                (x, y) -> new FieldBasic(Field.class.getSimpleName() + ":" + x + "," + y),
                (field1, field2) -> new FieldConnectionBasic(FieldConnection.class.getSimpleName() + ":" + field1.getId() + "-" + field2.getId(), field1, field2),
                LookPathBasic::new
        );
    }

    @Override
    protected void createFieldConnectionObjects() {
        // room1
        fieldConnections.get(FieldConnection.class.getSimpleName() + ":Field:1,0-Field:2,0").addObjectOnConnection(new Wall("Wall:1,0-2,0"));
        fieldConnections.get(FieldConnection.class.getSimpleName() + ":Field:1,1-Field:2,1").addObjectOnConnection(new Door("Door:1,1-2,1"));
        fieldConnections.get(FieldConnection.class.getSimpleName() + ":Field:1,1-Field:1,2").addObjectOnConnection(new SecretDoor("SecDoor:1,1-1,2"));
        fieldConnections.get(FieldConnection.class.getSimpleName() + ":Field:0,1-Field:0,2").addObjectOnConnection(new Wall("Wall:0,1-0,2"));
        // room2
        Door openedDoor = new Door("Door:2,4-3,4");
        openedDoor.open();
        fieldConnections.get(FieldConnection.class.getSimpleName() + ":Field:5,0-Field:5,1").addObjectOnConnection(new Wall("Wall:5,0-5,1"));
        fieldConnections.get(FieldConnection.class.getSimpleName() + ":Field:4,0-Field:4,1").addObjectOnConnection(openedDoor);
        fieldConnections.get(FieldConnection.class.getSimpleName() + ":Field:3,1-Field:4,1").addObjectOnConnection(new Wall("Wall:2,3-3,3"));
        fieldConnections.get(FieldConnection.class.getSimpleName() + ":Field:3,2-Field:4,2").addObjectOnConnection(new Wall("Wall:3,2-4,4"));
        fieldConnections.get(FieldConnection.class.getSimpleName() + ":Field:3,3-Field:4,3").addObjectOnConnection(new Door("Door:3,3-4,3"));
        // room3
        fieldConnections.get(FieldConnection.class.getSimpleName() + ":Field:0,2-Field:0,3").addObjectOnConnection(new Wall("Wall:0,2-0,3"));
        fieldConnections.get(FieldConnection.class.getSimpleName() + ":Field:1,2-Field:1,3").addObjectOnConnection(new Door("Door:1,2-1,3"));
        fieldConnections.get(FieldConnection.class.getSimpleName() + ":Field:2,2-Field:2,3").addObjectOnConnection(new Wall("Wall:2,2-2,3"));
        fieldConnections.get(FieldConnection.class.getSimpleName() + ":Field:3,2-Field:3,3").addObjectOnConnection(new Wall("Wall:3,2-3,3"));
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
}
