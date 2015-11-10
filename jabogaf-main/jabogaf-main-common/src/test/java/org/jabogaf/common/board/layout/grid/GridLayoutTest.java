package org.jabogaf.common.board.layout.grid;

import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldGroup;
import org.jabogaf.api.board.layout.LayoutActionImpact;
import org.jabogaf.common.TestWithExampleGridLayoutBoard;
import org.jabogaf.test.gamecontext.ArquillianGameContext;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

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
    public void testGetAllLayoutActionImpacts() {
        assertEquals(0, getLayout().getAllLayoutActionImpacts(getField(0, 0), getField(0, 0)).size());
        assertEquals(0, getLayout().getAllLayoutActionImpacts(getField(0, 0), getField(1, 1)).size());

        List<LayoutActionImpact<?, ?>> allLayoutActionImpacts = getLayout().getAllLayoutActionImpacts(getField(0, 0), getField(5, 0));
        assertEquals(2, allLayoutActionImpacts.size());
        assertEquals(getById("Wall:1,0-2,0"), allLayoutActionImpacts.get(0));
        assertEquals(getById("player3"), allLayoutActionImpacts.get(1));

        allLayoutActionImpacts = getLayout().getAllLayoutActionImpacts(getField(0, 0), getField(5, 3));
        assertEquals(4, allLayoutActionImpacts.size());
        assertEquals(getById("player1"), allLayoutActionImpacts.get(0));
        assertEquals(getById("Door:1,1-2,1"), allLayoutActionImpacts.get(1));
        assertEquals(getById("player2"), allLayoutActionImpacts.get(2));
        assertEquals(getById("Wall:3,2-4,4"), allLayoutActionImpacts.get(3));
    }

    @Test
    public void testGetLookPath() {
        assertEquals(2, getLayout().getLookPath(getField(0, 0), getField(1, 0)).get().getFields().size());
    }
}
