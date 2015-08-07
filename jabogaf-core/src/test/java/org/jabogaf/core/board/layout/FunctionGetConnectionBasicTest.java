package org.jabogaf.core.board.layout;

import org.jabogaf.api.board.layout.FunctionGetConnection;
import org.jabogaf.core.board.AbstractBoardTest;
import org.jabogaf.core.board.field.FieldConnectionNull;
import org.jabogaf.test.cdi.ArquillianGameContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(ArquillianGameContext.class)
public class FunctionGetConnectionBasicTest extends AbstractBoardTest {

    @Inject
    private FunctionGetConnection functionGetConnection;

    @Test
    public void testFunctionGetConnectionBasicNullOrEmpty() {
        Assert.assertNotNull(functionGetConnection);
        Assert.assertEquals(FieldConnectionNull.class, functionGetConnection.getConnection(null, field1, field2).getClass());
        Assert.assertEquals(FieldConnectionNull.class, functionGetConnection.getConnection(fieldConnections, null, null).getClass());
    }

    @Test
    public void testFunctionGetConnectionBasic() {
        Assert.assertEquals(fieldConnection12, functionGetConnection.getConnection(fieldConnections, field1, field2));
        Assert.assertEquals(fieldConnection23, functionGetConnection.getConnection(fieldConnections, field3, field2));
        Assert.assertEquals(FieldConnectionNull.class, functionGetConnection.getConnection(fieldConnections, field1, field3).getClass());
    }
}
