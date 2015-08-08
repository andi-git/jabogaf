package org.jabogaf.core.state;

import org.jabogaf.api.cdi.GameScoped;
import org.jabogaf.api.event.GameStateChanged;
import org.jabogaf.test.cdi.ArquillianGameContext;
import org.jabogaf.test.cdi.ArquillianGameContextTest;
import org.jabogaf.util.log.SLF4J;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.time.Instant;
import java.util.ArrayList;
import java.util.function.Function;

import static org.junit.Assert.*;

@RunWith(ArquillianGameContext.class)
public class CachedValueInputParamTest extends ArquillianGameContextTest {

    @Inject
    private CachedValueInputParam1 cachedValueInputParam1;

    @Inject
    private Event<GameStateChanged> gameStateChangedEvent;

    @Test
    public void testGet() throws Exception {
        // FIXME there are test-failures: actual is 42000
        assertFalse(cachedValueInputParam1.isValid());
        int before = cachedValueInputParam1.getLastEventChange().getNano();
        assertEquals("hello bob", cachedValueInputParam1.get(new CachedValueInputParam1.Parameter("bob")));
        int after = cachedValueInputParam1.getLastEventChange().getNano();
        assertTrue(cachedValueInputParam1.isValid());
        assertEquals(before, after);
        assertEquals("hello alice", cachedValueInputParam1.get(new CachedValueInputParam1.Parameter("alice")));
        after = cachedValueInputParam1.getLastEventChange().getNano();
        assertTrue(cachedValueInputParam1.isValid());
        assertEquals(before, after);

        gameStateChangedEvent.fire(new GameStateChanged(Instant.now(), null, new ArrayList<>()));

        assertFalse(cachedValueInputParam1.isValid());
        assertEquals("hello bob", cachedValueInputParam1.get(new CachedValueInputParam1.Parameter("bob")));
        after = cachedValueInputParam1.getLastEventChange().getNano();
        assertTrue(cachedValueInputParam1.isValid());
        assertNotEquals(before, after);
        before = after;
        assertEquals("hello bob", cachedValueInputParam1.get(new CachedValueInputParam1.Parameter("bob")));
        after = cachedValueInputParam1.getLastEventChange().getNano();
        assertTrue(cachedValueInputParam1.isValid());
        assertEquals(before, after);
    }

    @GameScoped
    public static class CachedValueInputParam1 extends CachedValueInputParam<String, CachedValueInputParam1.Parameter> {

        @Inject
        @SLF4J
        private Logger log;

        @Override
        protected Logger log() {
            return log;
        }

        @Override
        protected Function<Parameter, String> create() {
            return (parameter) -> "hello " + parameter.getString();
        }

        public static class Parameter {

            private String string;

            public Parameter(String string) {
                this.string = string;
            }

            public String getString() {
                return string;
            }

            public void setString(String string) {
                this.string = string;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                Parameter parameter = (Parameter) o;

                return !(string != null ? !string.equals(parameter.string) : parameter.string != null);

            }

            @Override
            public int hashCode() {
                return string != null ? string.hashCode() : 0;
            }
        }
    }
}