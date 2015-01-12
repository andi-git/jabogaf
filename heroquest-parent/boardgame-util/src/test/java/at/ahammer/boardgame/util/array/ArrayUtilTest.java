package at.ahammer.boardgame.util.array;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(Arquillian.class)
public class ArrayUtilTest {

    @Inject
    private ArrayUtil arrayUtil;

    @Test
    public void testConvertTwoDimensionalArray() throws Exception {
        Integer[][] intArray = new Integer[][]{
                {
                        1, 2
                },
                {
                        3, 4
                },
                {
                        5, 6
                }
        };
        String[][] result = arrayUtil.convertTwoDimensionalArray(intArray, String.class, (source) -> {
            return String.valueOf(source);
        });
        assertEquals(3, result.length);
        assertEquals(2, result[0].length);
        assertEquals("1", result[0][0]);
        assertEquals("6", result[2][1]);
    }

    @Test
    public void testInitArray() throws Exception {
        String[] array = arrayUtil.initArray(String.class, 3);
        assertEquals(3, array.length);
        assertNull(array[0]);
    }

    @Test
    public void testInitTwoDimensionalArray() throws Exception {
        String[][] array = arrayUtil.initTwoDimensionalArray(String.class, 2, 3);
        assertEquals(2, array.length);
        assertEquals(3, array[0].length);
    }
}