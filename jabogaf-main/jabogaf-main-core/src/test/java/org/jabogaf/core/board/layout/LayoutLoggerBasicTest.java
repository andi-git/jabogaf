package org.jabogaf.core.board.layout;

import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.api.board.layout.Layout;
import org.jabogaf.api.board.layout.log.LayoutLoggerManager;
import org.jabogaf.api.board.layout.log.LayoutLoggerParameter;
import org.jabogaf.test.gamecontext.ArquillianGameContext;
import org.jabogaf.test.gamecontext.ArquillianGameContextTest;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@RunWith(ArquillianGameContext.class)
public class LayoutLoggerBasicTest extends ArquillianGameContextTest {

    @Inject
    private LayoutLoggerManager layoutLoggerManager;

    @Test
    public void testToString() throws Exception {
        Layout nullLayout = new LayoutBasic("nullLayout" + System.nanoTime() + new Random().nextInt(), new HashSet<>(), new HashSet<>(), new HashSet<>()) {
            @Override
            public Stream<Field> getFieldsAsStream() {
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
        LayoutLoggerParameter nullLayoutLoggerParameter = new LayoutLoggerParameter() {
        };
        Layout dummyLayout = new DummyLayout();
        LayoutLoggerParameter dummyLayoutParater = new DummyLayoutParameter();

        assertEquals("no LayoutLogger for " + nullLayout.getClass() + " available", layoutLoggerManager.toString(nullLayout, nullLayoutLoggerParameter));
        assertEquals("String of DummyLayout", layoutLoggerManager.toString(dummyLayout, dummyLayoutParater));
    }
}