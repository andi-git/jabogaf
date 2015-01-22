package at.ahammer.boardgame.core.board.layout;

import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.field.FieldConnection;
import at.ahammer.boardgame.api.board.layout.Layout;
import at.ahammer.boardgame.api.board.layout.log.LayoutLoggerManager;
import at.ahammer.boardgame.core.test.ArquillianGameContext;
import at.ahammer.boardgame.core.test.ArquillianGameContextTest;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.Assert.*;

@RunWith(ArquillianGameContext.class)
public class LayoutLoggerBasicTest extends ArquillianGameContextTest {

    @Inject
    private LayoutLoggerManager layoutLoggerManager;

    @Test
    public void testToString() throws Exception {
        Layout nullLayout = new LayoutBasic("nullLayout" + System.nanoTime(), new HashSet<>(), new HashSet<>(), new HashSet<>()) {

            @Override
            public Stream getFieldsAsStream() {
                return null;
            }

            @Override
            public Set<FieldConnection> getLookConnections(Field position, Field target) {
                return null;
            }

            @Override
            public String toString() {
                return "String of NullLayout";
            }
        };
        Layout dummyLayout = new DummyLayout();

        assertEquals("no LayoutLogger for " + nullLayout.getClass() + " available", layoutLoggerManager.toString(nullLayout));
        assertEquals("String of DummyLayout", layoutLoggerManager.toString(dummyLayout));
    }
}