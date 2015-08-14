package org.jabogaf.util.copy;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class CopyPropertiesBasicTest {

    @Inject
    private CopyProperties copyProperties;

    @Test
    public void testCopyProperties() throws Exception {
        Bean beanA = new Bean("foo", 1);
        Bean beanB = new Bean();
        copyProperties.copy(beanA, beanB);
        assertEquals("foo", beanB.getS());
        assertEquals(1, beanB.getI());
    }

    @Test(expected = RuntimeException.class)
    public void testCopyPropertiesNotPossible() throws Exception {
        BeanWithExceptionOnGetter beanA = new BeanWithExceptionOnGetter(true);
        BeanWithExceptionOnGetter beanB = new BeanWithExceptionOnGetter(false);
        copyProperties.copy(beanA, beanB);
    }

    public static class Bean {

        private String s;

        private int i;

        public Bean() {
        }

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

    public static class BeanWithExceptionOnGetter {

        private boolean b;

        public BeanWithExceptionOnGetter(boolean b) {
        }

        public boolean isB() {
            throw new RuntimeException();
        }

        public void setB(boolean b) {
            this.b = b;
        }
    }
}