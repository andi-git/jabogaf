package org.jabogaf.common.board.layout;

import org.jabogaf.api.board.field.ContainsGameObjects;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.api.board.field.FieldGroup;
import org.jabogaf.api.board.layout.KeyTwoFields;
import org.jabogaf.api.gamecontext.GameContextBean;
import org.jabogaf.api.object.GameObject;
import org.jabogaf.api.resource.Resource;
import org.jabogaf.api.subject.GameSubject;
import org.jabogaf.core.gamecontext.GameContextBeanBasic;
import org.jabogaf.test.gamecontext.ArquillianGameContext;
import org.jabogaf.test.gamecontext.ArquillianGameContextTest;
import org.jabogaf.test.gamecontext.BeforeInGameContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(ArquillianGameContext.class)
public class KeyTwoFieldsBasicTest extends ArquillianGameContextTest {

    private Field field1, field2, field3;

    private KeyTwoFields key12, key13, key21, key23, key31, key32;

    @BeforeInGameContext
    public void before() {
        field1 = new MyField("field1");
        field2 = new MyField("field2");
        field3 = new MyField("field3");
        key12 = new KeyTwoFieldsBasic(field1, field2);
        key13 = new KeyTwoFieldsBasic(field1, field3);
        key21 = new KeyTwoFieldsBasic(field2, field1);
        key23 = new KeyTwoFieldsBasic(field2, field3);
        key31 = new KeyTwoFieldsBasic(field3, field1);
        key32 = new KeyTwoFieldsBasic(field3, field2);
    }

    @Test
    public void testGetter() {
        assertTrue(key12.getFrom().equals(field1));
        assertTrue(key12.getTo().equals(field2));
        assertEquals("KeyTwoFieldsBasic{from=GameContextBean{id='field1'}, to=GameContextBean{id='field2'}}", key12.toString());
    }

    @Test
    public void testEquals() throws Exception {
        assertTrue(field1.equals(field1));
        assertTrue(field2.equals(field2));
        assertTrue(field3.equals(field3));

        assertFalse(field1.equals(field2));
        assertFalse(field1.equals(field3));
        assertFalse(field2.equals(field1));
        assertFalse(field2.equals(field3));
        assertFalse(field3.equals(field1));
        assertFalse(field3.equals(field2));

        assertTrue(key12.equals(key12));
        assertFalse(key12.equals(key13));
        assertFalse(key12.equals(key21));
        assertFalse(key12.equals(key23));
        assertFalse(key12.equals(key31));
        assertFalse(key12.equals(key32));

        assertTrue(key21.equals(key21));
        assertFalse(key21.equals(key12));
        assertFalse(key21.equals(key31));
    }

    @Test
    public void testHashCode() throws Exception {
        assertEquals(field1.hashCode(), field1.hashCode());
        assertEquals(field2.hashCode(), field2.hashCode());
        assertEquals(field3.hashCode(), field3.hashCode());

        assertNotEquals(field1.hashCode(), field2.hashCode());
        assertNotEquals(field1.hashCode(), field3.hashCode());
        assertNotEquals(field2.hashCode(), field1.hashCode());
        assertNotEquals(field2.hashCode(), field3.hashCode());
        assertNotEquals(field3.hashCode(), field1.hashCode());
        assertNotEquals(field3.hashCode(), field2.hashCode());

        assertEquals(key12.hashCode(), key12.hashCode());
        assertNotEquals(key12.hashCode(), key13.hashCode());
        assertNotEquals(key12.hashCode(), key21.hashCode());
        assertNotEquals(key12.hashCode(), key23.hashCode());
        assertNotEquals(key12.hashCode(), key31.hashCode());
        assertNotEquals(key12.hashCode(), key32.hashCode());


        assertEquals(key21.hashCode(), key21.hashCode());
        assertNotEquals(key21.hashCode(), key12.hashCode());
        assertNotEquals(key21.hashCode(), key31.hashCode());
    }

    @Test
    public void testCollections() {
        Set<KeyTwoFields> set = new HashSet<>();
        set.add(key12);
        set.add(key12);
        set.add(key12);
        set.add(key21);
        set.add(key21);
        set.add(key32);
        assertEquals(3, set.size());

        List<KeyTwoFields> list = new ArrayList<>();
        list.add(key12);
        list.add(key12);
        list.add(key12);
        list.add(key21);
        list.add(key21);
        list.add(key32);
        assertEquals(6, list.size());

        Map<KeyTwoFields, String> map = new HashMap<>();
        map.put(key12, "");
        map.put(key12, "");
        map.put(key12, "");
        map.put(key21, "");
        map.put(key21, "");
        map.put(key32, "");
        assertEquals(3, map.size());
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
}