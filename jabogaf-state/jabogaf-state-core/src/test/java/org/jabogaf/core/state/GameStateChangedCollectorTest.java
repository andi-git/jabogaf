package org.jabogaf.core.state;

import org.jabogaf.api.cdi.GameScoped;
import org.jabogaf.api.event.GameStateChanged;
import org.jabogaf.api.state.ChangesGameState;
import org.jabogaf.api.state.GameStateChangedCollector;
import org.jabogaf.api.state.SetterFiresGameStateChanged;
import org.jabogaf.test.cdi.ArquillianGameContext;
import org.jabogaf.test.cdi.ArquillianGameContextTest;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.*;

@RunWith(ArquillianGameContext.class)
public class GameStateChangedCollectorTest extends ArquillianGameContextTest {

    @Inject
    private Bean bean;

    @Inject
    private GameStateChangedCollector gameStateChangedCollector;

    @Test
    public void testGameStateChangedCollector() throws Exception {
        bean.setXXX("xxx");
        assertEquals(1, gameStateChangedCollector.getGameStateChanges().size());
        GameStateChanged gameStateChanged = gameStateChangedCollector.getGameStateChanges().get(0);
        assertNotNull(gameStateChanged.getTime());
        assertEquals("setXXX", gameStateChanged.getMethod().getName());
        assertEquals("xxx", gameStateChanged.getValues().get(0));

        bean.addXXX(5);
        bean.getXXX();
        bean.foo();
        bean.bar(true, "true");
        bean.bar(false, "false");
        assertEquals(4, gameStateChangedCollector.getGameStateChanges().size());
    }

    @GameScoped
    @SetterFiresGameStateChanged
    public static class Bean {

        public void setXXX(String xxx) {
        }

        public void getXXX() {
        }

        public void addXXX(int i) {
        }

        public void removeXXX() {
        }

        public void clearXXX() {
        }

        public void foo() {
        }

        @ChangesGameState
        public void bar(boolean b, String s) {
        }
    }
}