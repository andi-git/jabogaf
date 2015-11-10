package org.jabogaf.common.board.layout.grid;

import org.jabogaf.api.behavior.look.LookPath;
import org.jabogaf.api.board.field.ContainsGameObjects;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.api.board.field.FieldGroup;
import org.jabogaf.api.board.layout.LayoutActionImpact;
import org.jabogaf.api.gamecontext.GameContextBean;
import org.jabogaf.api.object.GameObject;
import org.jabogaf.api.resource.Resource;
import org.jabogaf.api.subject.GameSubject;
import org.jabogaf.core.gamecontext.GameContextBeanBasic;
import org.jabogaf.test.gamecontext.ArquillianGameContext;
import org.jabogaf.test.gamecontext.ArquillianGameContextTest;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(ArquillianGameContext.class)
public class GridLayoutCreationStrategyTest extends ArquillianGameContextTest {

    @Test
    public void testCreation() {
        GridLayoutCreationStrategy gridLayoutCreationStrategy = new GridLayoutCreationStrategy(3, 3,
                (x, y) -> new MyField(Field.class.getSimpleName() + ":" + x + "," + y),
                (field1, field2) -> new MyFieldConnection(FieldConnection.class.getSimpleName() + ":" + field1.getId() + "-" + field2.getId(), field1, field2),
                MyLookPath::new
        );
        assertEquals(9, gridLayoutCreationStrategy.getFields().size());
        assertEquals(12, gridLayoutCreationStrategy.getFieldConnections().size());
        assertTrue(gridLayoutCreationStrategy.getFieldGroups().isEmpty());
        assertEquals(3, gridLayoutCreationStrategy.getFieldsArray().length);
        assertEquals(3, gridLayoutCreationStrategy.getFieldsArray()[0].length);
        assertEquals(72, gridLayoutCreationStrategy.getLookPaths().size());
        assertEquals(4, gridLayoutCreationStrategy.getAllFieldsInRectangle(0, 0, 1, 1).size());
        assertEquals("Field:0,0", gridLayoutCreationStrategy.getFieldsArray()[0][0].getId());
        assertEquals("FieldConnection:Field:0,0-Field:0,1", gridLayoutCreationStrategy.getFieldConnections().stream().sorted().findFirst().get().getId());
    }

    private static class MyField extends GameContextBeanBasic<Field> implements Field {

        public MyField(String id) {
            super(id);
        }

        @Override
        public boolean isConnected(Field target) {
            return false;
        }

        @Override
        public FieldConnection getConnectionTo(Field target) {
            return null;
        }

        @Override
        public Set<FieldConnection> getFieldConnections() {
            return null;
        }

        @Override
        public List<FieldGroup> getFieldsGroups() {
            return null;
        }

        @Override
        public Set<Field> getConnectedFields() {
            return null;
        }

        @Override
        public Resource movementCost() {
            return null;
        }

        @Override
        public void setMovementCost(Resource resource) {

        }

        @Override
        public int compareTo(GameContextBean o) {
            return 0;
        }

        @Override
        public List<GameObject<? extends ContainsGameObjects>> getGameObjects() {
            return null;
        }

        @Override
        public List<GameSubject> getGameSubjects() {
            return null;
        }
    }

    private static class MyFieldConnection extends GameContextBeanBasic<FieldConnection> implements FieldConnection {

        public MyFieldConnection(String id, Field field1, Field field2) {
            super(id);
        }

        @Override
        public boolean connects(Field field1, Field field2) {
            return false;
        }

        @Override
        public void addObjectOnConnection(GameObject<? extends ContainsGameObjects> fieldConnectionObject) {

        }

        @Override
        public void clearObjectsOnConnection() {

        }

        @Override
        public Field getRightHand() {
            return null;
        }

        @Override
        public Field getLeftHand() {
            return null;
        }

        @Override
        public boolean contains(Field field) {
            return false;
        }

        @Override
        public boolean containsAny(Collection<Field> fields) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<Field> fields) {
            return false;
        }

        @Override
        public List<GameObject<? extends ContainsGameObjects>> getGameObjects() {
            return null;
        }
    }

    private static class MyLookPath extends GameContextBeanBasic<LookPath> implements LookPath {

        public MyLookPath(List<Field> fields) {

        }

        @Override
        public Field getPosition() {
            return null;
        }

        @Override
        public Field getTarget() {
            return null;
        }

        @Override
        public List<LayoutActionImpact<?, ?>> getLayoutActionImpacts() {
            return null;
        }

        @Override
        public Resource cost() {
            return null;
        }

        @Override
        public List<Field> getFields() {
            return null;
        }
    }
}