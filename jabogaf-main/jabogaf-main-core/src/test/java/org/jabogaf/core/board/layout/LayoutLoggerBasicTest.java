package org.jabogaf.core.board.layout;

import org.jabogaf.api.board.layout.Layout;
import org.jabogaf.api.board.layout.log.LayoutLoggerManager;
import org.jabogaf.api.board.layout.log.LayoutLoggerParameter;
import org.jabogaf.test.gamecontext.ArquillianGameContext;
import org.jabogaf.test.gamecontext.ArquillianGameContextTest;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(ArquillianGameContext.class)
public class LayoutLoggerBasicTest extends ArquillianGameContextTest {

    @Inject
    private LayoutLoggerManager layoutLoggerManager;

    @Test
    public void testToString() throws Exception {
        Layout nullLayout = new DummyLayout() {
        };
        LayoutLoggerParameter nullLayoutLoggerParameter = new LayoutLoggerParameter() {
        };
        Layout dummyLayout = new DummyLayout();
        LayoutLoggerParameter dummyLayoutParameter = new DummyLayoutParameter();

        assertEquals("no LayoutLogger for " + nullLayout.getClass() + " available", layoutLoggerManager.toString(nullLayout, nullLayoutLoggerParameter));
        assertEquals("String of DummyLayout", layoutLoggerManager.toString(dummyLayout, dummyLayoutParameter));
    }
}