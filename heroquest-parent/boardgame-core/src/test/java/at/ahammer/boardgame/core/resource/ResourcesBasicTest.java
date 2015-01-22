package at.ahammer.boardgame.core.resource;

import at.ahammer.boardgame.api.resource.NotEnoughResourceException;
import at.ahammer.boardgame.api.resource.Payment;
import at.ahammer.boardgame.api.resource.Resource;
import at.ahammer.boardgame.api.resource.Resources;
import at.ahammer.boardgame.api.subject.GameSubject;
import at.ahammer.boardgame.core.subject.GameSubjectNull;
import at.ahammer.boardgame.core.test.ArquillianGameContext;
import at.ahammer.boardgame.core.test.ArquillianGameContextTest;
import at.ahammer.boardgame.core.test.BeforeInGameContext;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(ArquillianGameContext.class)
public class ResourcesBasicTest extends ArquillianGameContextTest {

    private Resources resources;

    private GameSubject gameSubject;

    @BeforeInGameContext
    public void before() {
        resources = new ResourcesBasic();
        gameSubject = new GameSubjectNull("myGameSubject");
    }

    @Test
    public void testAdd() throws Exception {
        assertEquals(0, resources.amountOf(ResourceWood.class));
        resources.add(new ResourceWood(2));
        assertEquals(2, resources.amountOf(ResourceWood.class));
        resources.add(new ResourceWood(2));
        assertEquals(4, resources.amountOf(ResourceWood.class));
        resources.add(new ResourceWood(2));
        assertEquals(6, resources.amountOf(ResourceWood.class));

        resources.add(null);

        resources.add(new ResourceStone());
        assertFalse(resources.has(ResourceStone.class));
    }

    @Test
    public void testRemove() throws Exception {
        resources.add(new ResourceStone(6));
        assertEquals(6, resources.amountOf(ResourceStone.class));
        resources.remove(new ResourceStone(2));
        assertEquals(4, resources.amountOf(ResourceStone.class));
        resources.remove(new ResourceStone(2));
        assertEquals(2, resources.amountOf(ResourceStone.class));
        resources.remove(new ResourceStone(2));
        assertEquals(0, resources.amountOf(ResourceStone.class));

        resources.remove(null);

        resources.add(new ResourceStone(2));
        try {
            resources.remove(new ResourceStone(3));
        } catch (NotEnoughResourceException e) {
            assertEquals(ResourceStone.class, e.getResource().getClass());
            assertEquals(3, e.getAmountNeeded());
            assertEquals(2, e.getAmountAvailable());
        }

        try {
            resources.remove(new ResourceWood(1));
        } catch (NotEnoughResourceException e) {
            assertEquals(ResourceWood.class, e.getResource().getClass());
            assertEquals(1, e.getAmountNeeded());
            assertEquals(0, e.getAmountAvailable());
        }
    }

    @Test
    public void testHas() throws Exception {
        assertFalse(resources.has(ResourceWood.class));
        assertFalse(resources.has(new ResourceStone()));
        resources.add(new ResourceWood((2)));
        resources.add(new ResourceStone((4)));
        assertTrue(resources.has(ResourceWood.class));
        assertTrue(resources.has(new ResourceStone()));
    }

    @Test
    public void testCanPay() throws Exception {
        assertFalse(resources.canPay(new PaymentBasic(new ResourceStone(2))));
        assertFalse(resources.canPay(new PaymentBasic(new ResourceWood(0))));
        resources.add(new ResourceStone(3));
        assertTrue(resources.canPay(new PaymentBasic(new ResourceStone(2))));
    }

    @Test
    public void testPay() throws Exception {
        resources.add(new ResourceWood(3));
        assertEquals(3, resources.amountOf(ResourceWood.class));
        resources.pay(new ResourceWood(2).asPayment());
        assertEquals(1, resources.amountOf(ResourceWood.class));
        try {
            resources.pay(new ResourceStone(2).asPayment());
        } catch (NotEnoughResourceException e) {
            assertEquals(ResourceStone.class, e.getResource().getClass());
            assertEquals(2, e.getAmountNeeded());
            assertEquals(0, e.getAmountAvailable());
        }
    }

    @Test
    public void testEarn() throws Exception {
        assertEquals(0, resources.amountOf(ResourceWood.class));
        resources.earn(new PaymentBasic(new ResourceWood(2)));
        assertEquals(2, resources.amountOf(ResourceWood.class));
        resources.earn(new PaymentBasic(new ResourceWood(2)));
        assertEquals(4, resources.amountOf(ResourceWood.class));
        resources.earn(new PaymentBasic(new ResourceWood(2)));
        assertEquals(6, resources.amountOf(ResourceWood.class));

        resources.earn(null);
        resources.earn(new PaymentBasic(new ResourceStone()));
        assertFalse(resources.has(ResourceStone.class));
    }

    @Test
    public void testGetResources() {
        resources.add(new ResourceStone(2));
        resources.add(new ResourceWood(3));
        assertEquals(2, resources.getResources().size());
    }

    @Test
    public void testGetSortedResources() {
        resources.add(new ResourceStone(2));
        resources.add(new ResourceWood(3));

        List<Resource> sortedResources = resources.getSortedResources();
        assertEquals(ResourceStone.class, sortedResources.get(0).getClass());
        assertEquals(ResourceWood.class, sortedResources.get(1).getClass());

        sortedResources = resources.getSortedResources((r1, r2) -> r2.toString().compareTo(r1.toString()));
        assertEquals(ResourceWood.class, sortedResources.get(0).getClass());
        assertEquals(ResourceStone.class, sortedResources.get(1).getClass());
    }

    @Test
    public void testResourcesViaGameSubject() {
        gameSubject.earn(new ResourceStone(2).asPayment());
    }
}
