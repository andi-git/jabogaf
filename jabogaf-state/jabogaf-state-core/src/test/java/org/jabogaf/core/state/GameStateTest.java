package org.jabogaf.core.state;

import com.google.gson.GsonBuilder;
import org.jabogaf.api.state.GameState;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(Arquillian.class)
public class GameStateTest {

    @Inject
    private MyBean myBean;

    @Test
    public void testSerialize() throws Exception {
        assertNull(myBean.getState().getS());
        assertEquals(0, myBean.getState().getI());

        myBean.getState().setS("foo");
        myBean.getState().setI(1);

        assertEquals("{\n" +
                "  \"s\": \"foo\",\n" +
                "  \"i\": 1\n" +
                "}", myBean.getState().serialize());
    }

    @Test
    public void testDeserialize() throws Exception {
        myBean.getState().deserialize("{\n" +
                "  \"s\": \"foo\",\n" +
                "  \"i\": 1\n" +
                "}");
        assertEquals("foo", myBean.getState().getS());
        assertEquals(1, myBean.getState().getI());
    }

    @Test
    public void testClassOfContainingBean() throws Exception {
        assertEquals(MyBean.class, myBean.getState().classOfContainingBean());
    }

    public static class MyBean {

        @Inject
        private MyGameState state;

        private String x;

        public MyGameState getState() {
            return state;
        }

        public static class MyGameState extends GameState<MyBean> {

            private String s;

            private int i;

            @Override
            public Class<MyBean> classOfContainingBean() {
                return MyBean.class;
            }

            public int getI() {
                return i;
            }

            public void setI(int i) {
                this.i = i;
            }

            public String getS() {
                return s;
            }

            public void setS(String s) {
                this.s = s;
            }
        }
    }
}