package org.jabogaf.util.serialize;

import org.jabogaf.util.copy.CopyProperties;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class SerializerJSONTest {

    @Inject
    @JSON
    private Serializer serializer;

    @Inject
    private CopyProperties copyProperties;

    @Test
    public void testSerialize() throws Exception {
        Bean bean = new Bean("foo", 1);
        assertEquals("{\n" +
                "  \"s\": \"foo\",\n" +
                "  \"i\": 1\n" +
                "}", serializer.serialize(bean));
    }

    @Test
    public void testDeserialize() throws Exception {
        Bean bean = serializer.deserialize("{\"s\":\"foo\",\"i\":1}", Bean.class);
        assertEquals("foo", bean.getS());
        assertEquals(1, bean.getI());

        Bean beanToCopyTo = new Bean("bar", 2);
        copyProperties.copy(bean, beanToCopyTo);
        assertEquals("foo", beanToCopyTo.getS());
        assertEquals(1, beanToCopyTo.getI());
    }

    @Test(expected = NullPointerException.class)
    public void testSetGson() {
        ((SerializerJSON) serializer).setGson(null);
        serializer.serialize(new Object());
    }

    public static class Bean {

        private String s;

        private int i;

        public Bean(String s, int i) {
            this.s = s;
            this.i = i;
        }

        public String getS() {
            return s;
        }

        public void setS(String s) {
            this.s = s;
        }

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }
    }
}