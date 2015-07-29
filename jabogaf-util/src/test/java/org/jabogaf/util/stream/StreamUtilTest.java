package org.jabogaf.util.stream;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.stream.Stream;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class StreamUtilTest {

    @Inject
    private StreamUtil streamUtil;

    @Test
    public void testGetArrayAsStream() throws Exception {
        Integer[] array = new Integer[]{0, 1, 2, 3};
        assertEquals(4, streamUtil.getArrayAsStream(array).count());
        assertEquals(6, streamUtil.getArrayAsStream(array).mapToInt(i -> i).sum());
    }

    @Test
    public void testGetTwoDimensionalArrayAsStream() throws Exception {
        Integer[][] array = new Integer[][]{{0, 0}, {1, 1}, {2, 2}, {3, 3}};
        assertEquals(8, streamUtil.getTwoDimensionalArrayAsStream(array).count());
        assertEquals(12, streamUtil.getTwoDimensionalArrayAsStream(array).mapToInt(i -> i).sum());
    }
}