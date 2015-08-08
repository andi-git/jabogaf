package org.jabogaf.core.state;

import org.jabogaf.api.state.ChangesGameState;
import org.jabogaf.test.cdi.ArquillianGameContext;
import org.jabogaf.test.cdi.ArquillianGameContextTest;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.*;

@RunWith(ArquillianGameContext.class)
public class MutableMethodsTest extends ArquillianGameContextTest {

    @Inject
    private MutableMethods mutableMethods;

    @Test
    public void testValues() throws Exception {
        assertEquals(4, mutableMethods.getMutableMethodPrefixes().size());
        assertTrue(mutableMethods.getMutableMethodPrefixes().contains("set"));
        assertTrue(mutableMethods.getMutableMethodPrefixes().contains("add"));
        assertTrue(mutableMethods.getMutableMethodPrefixes().contains("remove"));
        assertTrue(mutableMethods.getMutableMethodPrefixes().contains("clear"));

        mutableMethods.add("mock");
        assertEquals(5, mutableMethods.getMutableMethodPrefixes().size());
        assertTrue(mutableMethods.getMutableMethodPrefixes().contains("mock"));

        mutableMethods.clear();
        assertTrue(mutableMethods.getMutableMethodPrefixes().isEmpty());
        mutableMethods.addDefaults();
    }

    @Test
    public void testIsMutableMethod() throws Exception {
        assertTrue(mutableMethods.isMutableMethod(Bean.class.getDeclaredMethod("setXXX")));
        assertTrue(mutableMethods.isMutableMethod(Bean.class.getDeclaredMethod("addXXX")));
        assertTrue(mutableMethods.isMutableMethod(Bean.class.getDeclaredMethod("removeXXX")));
        assertTrue(mutableMethods.isMutableMethod(Bean.class.getDeclaredMethod("clearXXX")));
        assertTrue(mutableMethods.isMutableMethod(Bean.class.getDeclaredMethod("bar")));
        assertTrue(mutableMethods.isMutableMethod(Bean.class.getDeclaredMethod("bar")));
        assertFalse(mutableMethods.isMutableMethod(Bean.class.getDeclaredMethod("getXXX")));
        assertFalse(mutableMethods.isMutableMethod(Bean.class.getDeclaredMethod("foo")));
        assertFalse(mutableMethods.isMutableMethod(Bean.class.getDeclaredMethod("foo")));
    }

    public static class Bean {

        public void setXXX() {
        }

        public void getXXX() {
        }

        public void addXXX() {
        }

        public void removeXXX() {
        }

        public void clearXXX() {
        }

        public void foo() {
        }

        @ChangesGameState
        public void bar() {
        }
    }
}