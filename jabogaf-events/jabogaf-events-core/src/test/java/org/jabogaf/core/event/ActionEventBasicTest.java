package org.jabogaf.core.event;

import org.jabogaf.api.action.GameActionParameter;
import org.junit.Test;

import static org.junit.Assert.*;

public class ActionEventBasicTest {

    @Test
    public void testGetActionParameter() throws Exception {
        ActionEventBasic before = new MyBeforeActionEventBasic(new MyActionEventParameter());
        assertEquals(MyActionEventParameter.class, before.getActionParameter().getClass());
        ActionEventBasic after = new MyAfterActionEventBasic(new MyActionEventParameter());
        assertEquals(MyActionEventParameter.class, after.getActionParameter().getClass());
    }

    private static class MyAfterActionEventBasic extends AfterActionEventBasic<MyActionEventParameter> {

        public MyAfterActionEventBasic(MyActionEventParameter actionParameter) {
            super(actionParameter);
        }
    }

    private static class MyBeforeActionEventBasic extends BeforeActionEventBasic<MyActionEventParameter> {

        public MyBeforeActionEventBasic(MyActionEventParameter actionParameter) {
            super(actionParameter);
        }
    }

    private static class MyActionEventParameter implements GameActionParameter {

    }
}