package at.ahammer.boardgame.core.resource;

import at.ahammer.boardgame.api.resource.NotEnoughResourceException;
import at.ahammer.boardgame.api.resource.NotSameResourceException;
import at.ahammer.boardgame.api.resource.Resource;
import at.ahammer.boardgame.core.test.ArquillianGameContext;
import at.ahammer.boardgame.core.test.ArquillianGameContextTest;
import at.ahammer.boardgame.core.test.BeforeInGameContext;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(ArquillianGameContext.class)
public class ResourceTest extends ArquillianGameContextTest {

    private Resource resource;

    @BeforeInGameContext
    public void before() {
        resource = new ResourceStone(2);
    }

    @Test(expected = IllegalStateException.class)
    public void testInit() {
        resource = new ResourceStone();
        assertEquals(0, resource.getAmount());
        resource = new ResourceStone(2);
        assertEquals(2, resource.getAmount());
        resource = new ResourceStone(-1);
    }

    @Test
    public void testAdd() throws Exception {
        assertEquals(2, resource.getAmount());
        resource = resource.add(2);
        assertEquals(4, resource.getAmount());
        resource = resource.add(new ResourceStone(2));
        assertEquals(6, resource.getAmount());
        resource = resource.add(new ResourceWood(2));
        assertEquals(6, resource.getAmount());
    }

    @Test
    public void testRemove() throws Exception {
        resource = resource.setAmount(4);
        assertEquals(4, resource.getAmount());
        resource = resource.remove(1);
        assertEquals(3, resource.getAmount());
        resource = resource.remove(new ResourceStone(1));
        assertEquals(2, resource.getAmount());
        try {
            resource = resource.remove(3);
            fail("should throw exception");
        } catch (NotEnoughResourceException e) {
            assertEquals(ResourceStone.class, e.getResource());
            assertEquals(2, e.getAmountAvailable());
            assertEquals(3, e.getAmountNeeded());
        }
        resource = resource.remove(new ResourceWood(2));
        assertEquals(2, resource.getAmount());
    }

    @Test
    public void testSetAmount() throws Exception {
        resource = resource.setAmount(4);
        assertEquals(4, resource.getAmount());
        resource = resource.setAmount(new ResourceStone(6));
        assertEquals(6, resource.getAmount());
        try {
            resource = resource.setAmount(new ResourceWood(8));
            fail("should throw exception");
        } catch (NotSameResourceException e) {
            assertEquals(ResourceWood.class, e.getResourceAvailable().getClass());
            assertEquals(ResourceStone.class, e.getResourceExpected().getClass());
        }
    }

    @Test
    public void testCanPay() throws Exception {
        assertTrue(resource.canPay(2));
        assertFalse(resource.canPay(3));
        assertTrue(resource.canPay(new ResourceStone(2)));
        assertFalse(resource.canPay(new ResourceStone(3)));
        assertFalse(resource.canPay(new ResourceWood(2)));
    }

    @Test
    public void testToString() throws Exception {
        assertEquals(resource.toString(), resource.getClass().getSimpleName());
    }

    @Test
    public void testIsResourceType() throws Exception {
        assertTrue(resource.isResourceType(resource));
        assertTrue(resource.isResourceType(new ResourceStone()));
        assertFalse(resource.isResourceType(new ResourceWood()));
        assertTrue(resource.isResourceType(ResourceStone.class));
        assertFalse(resource.isResourceType(ResourceWood.class));
    }

    @Test
    public void testClone() throws Exception {
        assertEquals(2, resource.getAmount());
        Resource clone = resource.clone();
        assertEquals(2, clone.getAmount());
        assertFalse(resource.equals(clone));
    }

    @Test
    public void testAsPayment() throws Exception {
        assertEquals(2, resource.asPayment().getResource().getAmount());
        assertEquals(2, resource.asPayment().getAmount());
        assertFalse(resource.equals(resource.asPayment().getResource()));
    }
}
