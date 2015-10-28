package org.jabogaf.common.board.layout.grid;

import org.jabogaf.common.TestWithExampleGridLayoutBoard;
import org.jabogaf.test.gamecontext.ArquillianGameContext;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(ArquillianGameContext.class)
public class BoardTest extends TestWithExampleGridLayoutBoard {

    @Test
    public void testBoard() {
        assertNotNull(getLayout());
        assertEquals(24, getLayout().getFields().size());
    }
}
