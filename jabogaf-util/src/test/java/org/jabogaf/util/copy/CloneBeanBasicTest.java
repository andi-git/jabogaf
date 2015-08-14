package org.jabogaf.util.copy;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class CloneBeanBasicTest {

    @Inject
    private CloneBean cloneBean;

    @Test
    public void testClone() throws Exception {
        Bean bean = new Bean("foo", 1);
        Optional<Bean> optional = cloneBean.clone(bean);
        assertTrue(optional.isPresent());
        Bean clone = optional.get();
        assertEquals("foo", clone.getS());
        assertEquals(1, clone.getI());
    }

    @Test
    public void testCloneNotPossible() {
        Optional<BeanWithoutDefaultConstructor> clone = cloneBean.clone(new BeanWithoutDefaultConstructor(true));
        assertFalse(clone.isPresent());
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

    public static class BeanWithoutDefaultConstructor {

        public BeanWithoutDefaultConstructor(@SuppressWarnings("UnusedParameters") boolean b) {
        }
    }

}