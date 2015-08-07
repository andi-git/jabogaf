package org.jabogaf.common.board.layout.grid;

import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.api.board.field.FieldGroup;
import org.jabogaf.common.TestWithExampleGridLayoutBoard;
import org.jabogaf.test.cdi.ArquillianGameContext;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Set;

import static org.junit.Assert.*;

@RunWith(ArquillianGameContext.class)
public class GridLayoutTest extends TestWithExampleGridLayoutBoard {

    @Test
    public void testFieldsAsStream() {
        assertEquals(24, getLayout().getFieldsAsStream().count());
        assertEquals("Field:0,0", getLayout().getFieldsAsStream().findFirst().get().getId());
    }

    @Test
    public void testField() {
        Field field11 = getField(1, 1);
        assertNotNull(field11);
        assertEquals("Field:1,1", field11.getId());
    }

    @Test
    public void testFieldConnection() {
        Field field11 = getField(1, 1);

        assertTrue(field11.isConnected(getField(0, 1)));
        assertTrue(field11.isConnected(getField(1, 0)));
        assertTrue(field11.isConnected(getField(1, 2)));
        assertTrue(field11.isConnected(getField(2, 1)));

        assertFalse(field11.isConnected(getField(1, 1)));
        assertFalse(field11.isConnected(getField(0, 0)));
        assertFalse(field11.isConnected(getField(0, 2)));
        assertFalse(field11.isConnected(getField(2, 0)));
        assertFalse(field11.isConnected(getField(2, 2)));
        assertFalse(field11.isConnected(getField(3, 3)));
    }

    @Test
    public void testFieldGroup() {
        Field field00 = getField(0, 0);
        Field field11 = getField(1, 1);
        Field field33 = getField(3, 3);
        FieldGroup fieldGroup = getById(FieldGroup.class, "FGroup:room0");
        assertEquals(4, fieldGroup.getFields().size());
        assertTrue(fieldGroup.contains(field00));
        assertTrue(fieldGroup.contains(field11));
        assertFalse(fieldGroup.contains(field33));
    }

    @Test
    public void testLook() {
        Set<FieldConnection> fieldConnectionsForLook = getLayout().getLookConnections(getField(1, 0), getField(2, 2));
        assertEquals(3, fieldConnectionsForLook.size());
        assertTrue(fieldConnectionsForLook.contains(getById("FieldConnection:1,0-1,1")));
        assertTrue(fieldConnectionsForLook.contains(getById("FieldConnection:1,1-2,1")));
        assertTrue(fieldConnectionsForLook.contains(getById("FieldConnection:2,1-2,2")));
    }
}
