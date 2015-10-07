package org.jabogaf.core.resource;

import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.resource.GameStateResources;
import org.jabogaf.api.resource.NotEnoughResourceException;
import org.jabogaf.api.resource.Resource;
import org.jabogaf.api.resource.ResourceManager;
import org.jabogaf.api.state.GameState;
import org.jabogaf.api.subject.GameSubject;
import org.jabogaf.core.subject.GameSubjectNull;
import org.jabogaf.test.gamecontext.ArquillianGameContext;
import org.jabogaf.test.gamecontext.ArquillianGameContextTest;
import org.jabogaf.test.gamecontext.BeforeInGameContext;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(ArquillianGameContext.class)
public class ResourceManagerBasicTest extends ArquillianGameContextTest {

    @Inject
    private ResourceManager resourceManager;

    @Inject
    private State state;

    private GameSubject gameSubject;

    @BeforeInGameContext
    public void before() {
        gameSubject = new GameSubjectNull("myGameSubject");
        state.clearResource();
    }

    @Test
    public void testAdd() throws Exception {
        assertEquals(0, resourceManager.amountOf(ResourceWood.class, state));
        resourceManager.add(new ResourceWood(2), state);
        assertEquals(2, resourceManager.amountOf(ResourceWood.class, state));
        resourceManager.add(new ResourceWood(2), state);
        assertEquals(4, resourceManager.amountOf(ResourceWood.class, state));
        resourceManager.add(new ResourceWood(2), state);
        assertEquals(6, resourceManager.amountOf(ResourceWood.class, state));

        resourceManager.add(null, state);

        resourceManager.add(new ResourceStone(), state);
        assertFalse(resourceManager.has(ResourceStone.class, state));
    }

    @Test
    public void testRemove() throws Exception {
        resourceManager.add(new ResourceStone(6), state);
        assertEquals(6, resourceManager.amountOf(ResourceStone.class, state));
        resourceManager.remove(new ResourceStone(2), state);
        assertEquals(4, resourceManager.amountOf(ResourceStone.class, state));
        resourceManager.remove(new ResourceStone(2), state);
        assertEquals(2, resourceManager.amountOf(ResourceStone.class, state));
        resourceManager.remove(new ResourceStone(2), state);
        assertEquals(0, resourceManager.amountOf(ResourceStone.class, state));

        resourceManager.remove(null, state);

        resourceManager.add(new ResourceStone(2), state);
        try {
            resourceManager.remove(new ResourceStone(3), state);
        } catch (NotEnoughResourceException e) {
            assertEquals(ResourceStone.class, e.getResource());
            assertEquals(3, e.getAmountNeeded());
            assertEquals(2, e.getAmountAvailable());
        }

        try {
            resourceManager.remove(new ResourceWood(1), state);
        } catch (NotEnoughResourceException e) {
            assertEquals(ResourceWood.class, e.getResource());
            assertEquals(1, e.getAmountNeeded());
            assertEquals(0, e.getAmountAvailable());
        }
    }

    @Test
    public void testHas() throws Exception {
        assertFalse(resourceManager.has(ResourceWood.class, state));
        assertFalse(resourceManager.has(new ResourceStone(), state));
        resourceManager.add(new ResourceWood(2), state);
        resourceManager.add(new ResourceStone(4), state);
        assertTrue(resourceManager.has(ResourceWood.class, state));
        assertTrue(resourceManager.has(new ResourceStone(), state));
    }

    @Test
    public void testCanPay() throws Exception {
        assertFalse(resourceManager.canPay(new PaymentBasic(new ResourceStone(2)), state));
        assertFalse(resourceManager.canPay(new PaymentBasic(new ResourceWood(0)), state));
        resourceManager.add(new ResourceStone(3), state);
        assertTrue(resourceManager.canPay(new PaymentBasic(new ResourceStone(2)), state));
    }

    @Test
    public void testPay() throws Exception {
        resourceManager.add(new ResourceWood(3), state);
        assertEquals(3, resourceManager.amountOf(ResourceWood.class, state));
        resourceManager.pay(new ResourceWood(2).asPayment(), state);
        assertEquals(1, resourceManager.amountOf(ResourceWood.class, state));
        try {
            resourceManager.pay(new ResourceStone(2).asPayment(), state);
        } catch (NotEnoughResourceException e) {
            assertEquals(ResourceStone.class, e.getResource());
            assertEquals(2, e.getAmountNeeded());
            assertEquals(0, e.getAmountAvailable());
        }
    }

    @Test
    public void testEarn() throws Exception {
        assertEquals(0, resourceManager.amountOf(ResourceWood.class, state));
        resourceManager.earn(new PaymentBasic(new ResourceWood(2)), state);
        assertEquals(2, resourceManager.amountOf(ResourceWood.class, state));
        resourceManager.earn(new PaymentBasic(new ResourceWood(2)), state);
        assertEquals(4, resourceManager.amountOf(ResourceWood.class, state));
        resourceManager.earn(new PaymentBasic(new ResourceWood(2)), state);
        assertEquals(6, resourceManager.amountOf(ResourceWood.class, state));

        resourceManager.earn(null, state);
        resourceManager.earn(new PaymentBasic(new ResourceStone()), state);
        assertFalse(resourceManager.has(ResourceStone.class, state));
    }

    @Test
    public void testGetResources() {
        resourceManager.add(new ResourceStone(2), state);
        resourceManager.add(new ResourceWood(3), state);
        assertEquals(2, resourceManager.getResourceManager(state).size());
    }

    @Test
    public void testGetSortedResources() {
        resourceManager.add(new ResourceStone(2), state);
        resourceManager.add(new ResourceWood(3), state);

        List<Resource> sortedResources = resourceManager.getSortedResources(state);
        assertEquals(ResourceStone.class, sortedResources.get(0).getClass());
        assertEquals(ResourceWood.class, sortedResources.get(1).getClass());

        sortedResources = resourceManager.getSortedResources((r1, r2) -> r2.toString().compareTo(r1.toString()), state);
        assertEquals(ResourceWood.class, sortedResources.get(0).getClass());
        assertEquals(ResourceStone.class, sortedResources.get(1).getClass());
    }

    @Test
    public void testResourcesViaGameSubject() {
        gameSubject.earn(new ResourceStone(2).asPayment());
    }

    @Dependent
    public static class State extends GameState<GameSubject> implements GameStateResources {

        private final Set<Resource> resources = new HashSet<>();

        @Override
        public Class<GameSubject> classOfContainingBean() {
            return GameSubject.class;
        }

        @Override
        public Set<Resource> getResources() {
            return resources;
        }

        @Override
        public void addResource(Resource resource) {
            resources.add(resource);
        }

        @Override
        public void addResources(Set<Resource> resources) {
            resources.addAll(resources);
        }

        @Override
        public void clearResource() {
            resources.clear();
        }

        @Override
        public void removeResource(Resource resource) {
            resources.remove(resource);
        }
    }

}
