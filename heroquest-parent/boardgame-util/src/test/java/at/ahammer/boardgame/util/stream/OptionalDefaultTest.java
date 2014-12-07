package at.ahammer.boardgame.util.stream;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

@RunWith(Arquillian.class)
public class OptionalDefaultTest {

    @Inject
    private OptionalDefault optionalDefault;

    @Test
    public void testOptionalDefault() {
        Assert.assertNotNull(optionalDefault);
        List<String> list = Arrays.asList("one", "two", "three");
        Assert.assertEquals("one", optionalDefault.defaultGet(list.stream().findFirst(), null));
        Assert.assertEquals("four", optionalDefault.defaultGet(Arrays.asList().stream().findFirst(), "four"));
    }
}
