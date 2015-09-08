package org.jabogaf.core.subject.artifact.hand;

import org.jabogaf.api.subject.artifact.hand.ArtifactHandlingException;
import org.jabogaf.api.subject.hand.Hand;
import org.jabogaf.core.artifact.ArtifactNull;
import org.jabogaf.test.gamecontext.ArquillianGameContext;
import org.jabogaf.test.gamecontext.ArquillianGameContextTest;
import org.jabogaf.test.gamecontext.BeforeInGameContext;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(ArquillianGameContext.class)
public class AddArtifactToHandStrategyGeneralBasicTest extends ArquillianGameContextTest {

    @Inject
    private AddArtifactToHandStrategyGeneralBasic addArtifactToHandStrategyGeneralBasic;

    @Inject
    private Helper helper;

    private AddArtifactToHandStrategyContext context;

    @BeforeInGameContext
    public void before() {
        context = getDefaultContext();
    }

    @Test
    public void testAddArtifactToHandOk() throws Exception {
        assertEquals(ArtifactNull.class, context.getMainHandArtifact().getClass());
        assertEquals(ArtifactNull.class, context.getOffHandArtifact().getClass());
        addArtifactToHandStrategyGeneralBasic.addArtifactToHand(context);
        assertEquals(MyArtifact.class, context.getMainHandArtifact().getClass());
        assertEquals(ArtifactNull.class, context.getOffHandArtifact().getClass());
    }

    @Test(expected = ArtifactHandlingException.class)
    public void testAddArtifactToHandUnableToHandle() throws Exception {
        context.setCanHandleArtifactStrategy((a, h) -> false);
        assertEquals(ArtifactNull.class, context.getMainHandArtifact().getClass());
        assertEquals(ArtifactNull.class, context.getOffHandArtifact().getClass());
        addArtifactToHandStrategyGeneralBasic.addArtifactToHand(context);
    }

    @Test
    public void testAddArtifactToHandTwoHandedWeapon() throws Exception {
        MyTwoHandedWeapon myTwoHandedWeapon = new MyTwoHandedWeapon();
        context.addArtifactsToHands(myTwoHandedWeapon, myTwoHandedWeapon);
        assertEquals(MyTwoHandedWeapon.class, context.getMainHandArtifact().getClass());
        assertEquals(MyTwoHandedWeapon.class, context.getOffHandArtifact().getClass());
        addArtifactToHandStrategyGeneralBasic.addArtifactToHand(context);
        assertEquals(MyArtifact.class, context.getMainHandArtifact().getClass());
        assertEquals(ArtifactNull.class, context.getOffHandArtifact().getClass());
    }


    private AddArtifactToHandStrategyContext getDefaultContext() {
        MyArtifactHolder artifactHolder = new MyArtifactHolder();
        return new AddArtifactToHandStrategyContext().
                setArtifact(new MyArtifact()).
                setHandType(Hand.Type.MAIN).
                setArtifactHolder(artifactHolder).
                setSetterOfArtifactsForHands(artifactHolder.createSetterOfArtifactsForHands()).
                setCanHandleArtifactStrategy((a, h) -> true).
                setAddArtifactToHandStrategyConcrete((ctx) -> ctx.addArtifactToMainHand());
    }
}