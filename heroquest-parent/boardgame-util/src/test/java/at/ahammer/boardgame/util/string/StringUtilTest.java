package at.ahammer.boardgame.util.string;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class StringUtilTest {

    @Inject
    private StringUtil stringUtil;

    @Test
    public void testRepeatedString() throws Exception {
        assertEquals("aaaaa", stringUtil.repeatedString('a', 5));
    }

    @Test
    public void testPadLeft() throws Exception {
        assertEquals("     ", stringUtil.padLeft("", 5));
        assertEquals("     ", stringUtil.padLeft(null, 5));
        assertEquals("aa   ", stringUtil.padLeft("aa", 5));
        assertEquals("123456", stringUtil.padLeft("123456", 5));
    }

    @Test
    public void testPadLeftFixSize() throws Exception {
        assertEquals("     ", stringUtil.padLeft("", 5));
        assertEquals("     ", stringUtil.padLeft(null, 5));
        assertEquals("aa   ", stringUtil.padLeftFixSize("aa", 5));
        assertEquals("12345", stringUtil.padLeftFixSize("123456", 5));
    }

    @Test
    public void testPadRight() throws Exception {
        assertEquals("     ", stringUtil.padRight("", 5));
        assertEquals("     ", stringUtil.padRight(null, 5));
        assertEquals("   aa", stringUtil.padRight("aa", 5));
        assertEquals("123456", stringUtil.padRight("123456", 5));
    }

    @Test
    public void testPadRightFixSize() throws Exception {
        assertEquals("     ", stringUtil.padRight("", 5));
        assertEquals("     ", stringUtil.padRight(null, 5));
        assertEquals("   aa", stringUtil.padRightFixSize("aa", 5));
        assertEquals("12345", stringUtil.padRightFixSize("123456", 5));
    }

}