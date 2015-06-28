package org.jabogaf.core.board.layout;

import org.jabogaf.api.board.layout.FunctionGetAllGameObjectsOf;
import org.jabogaf.core.board.AbstractBoardTest;
import org.jabogaf.core.test.ArquillianGameContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(ArquillianGameContext.class)
public class FunctionGetAllGameObjectsOfBasicTest extends AbstractBoardTest {

    @Inject
    private FunctionGetAllGameObjectsOf functionGetAllGameObjectsOf;

    @Test
    public void testFunctionGetAllGameObjectsOfBasicNullOrEmpty() {
        Assert.assertNotNull(functionGetAllGameObjectsOf);
        Assert.assertTrue(functionGetAllGameObjectsOf.getAllGameObjectsOf(null, field1, field2).isEmpty());
        Assert.assertTrue(functionGetAllGameObjectsOf.getAllGameObjectsOf(fieldConnections, null, null).isEmpty());
    }

    @Test
    public void testFunctionGetAllGameObjectsOfBasic() {
        Assert.assertEquals(2, functionGetAllGameObjectsOf.getAllGameObjectsOf(fieldConnections, field1, field2).size());
        Assert.assertTrue(functionGetAllGameObjectsOf.getAllGameObjectsOf(fieldConnections, field1, field3).isEmpty());
        Assert.assertTrue(functionGetAllGameObjectsOf.getAllGameObjectsOf(fieldConnections, field2, field3).isEmpty());
    }
}
