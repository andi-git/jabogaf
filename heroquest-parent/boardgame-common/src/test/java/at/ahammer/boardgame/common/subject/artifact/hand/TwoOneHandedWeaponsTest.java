package at.ahammer.boardgame.common.subject.artifact.hand;

import at.ahammer.boardgame.api.subject.artifact.NullArtifact;
import at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingException;
import at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingStrategy;
import at.ahammer.boardgame.api.subject.hand.Hand;
import at.ahammer.boardgame.common.artifact.weapon.OneHandedSword;
import at.ahammer.boardgame.core.test.ArquillianGameContext;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.*;

@RunWith(ArquillianGameContext.class)
public class TwoOneHandedWeaponsTest extends ArtifactHandTest {

    @Inject
    private TwoOneHandedWeapons twoOneHandedWeapons;

    @Test
    public void testTwoOneHandedWeaponsCanHandle() {
        assertTrue(twoOneHandedWeapons.canHandle(getOneHandedSword(), Hand.Type.MAIN));
        assertTrue(twoOneHandedWeapons.canHandle(getOneHandedAxe(), Hand.Type.MAIN));
        assertTrue(twoOneHandedWeapons.canHandle(getOneHandedSword(), Hand.Type.OFF));
        assertFalse(twoOneHandedWeapons.canHandle(getTwoHandedSword(), Hand.Type.MAIN));
        assertFalse(twoOneHandedWeapons.canHandle(getTwoHandedAxe(), Hand.Type.MAIN));
        assertFalse(twoOneHandedWeapons.canHandle(getTwoHandedSword(), Hand.Type.OFF));
        assertFalse(twoOneHandedWeapons.canHandle(getSimpleShield(), Hand.Type.OFF));
        assertFalse(twoOneHandedWeapons.canHandle(getSimpleShield(), Hand.Type.MAIN));
    }

    @Test
    public void testTwoOneHandedWeaponsAddArtifact() throws ArtifactHandlingException {
        twoOneHandedWeapons.addArtifactToHand(getContext(getOneHandedSword(), Hand.Type.MAIN));
        assertEquals(OneHandedSword.class, getGameSubject().getMainHandArtifact().getClass());
        assertEquals(NullArtifact.class, getGameSubject().getOffHandArtifact().getClass());

        getGameSubject().resetHands();
        twoOneHandedWeapons.addArtifactToHand(getContext(getOneHandedSword(), Hand.Type.OFF));
        assertEquals(NullArtifact.class, getGameSubject().getMainHandArtifact().getClass());
        assertEquals(OneHandedSword.class, getGameSubject().getOffHandArtifact().getClass());

    }

    @Test(expected = ArtifactHandlingException.class)
    public void testTwoOneHandedWeaponsAddArtifactExceptionTwoHanded() throws ArtifactHandlingException {
        twoOneHandedWeapons.addArtifactToHand(getContext(getTwoHandedSword(), Hand.Type.MAIN));
    }

    @Test
    public void testTwoOneHandedWeaponsViaGameSubject() throws ArtifactHandlingException {
        getGameSubject().addArtifact(getOneHandedSword());
        assertEquals(OneHandedSword.class, getGameSubject().getMainHandArtifact().getClass());
        assertEquals(NullArtifact.class, getGameSubject().getOffHandArtifact().getClass());

        getGameSubject().addArtifact(getOneHandedSword(), Hand.Type.OFF);
        assertEquals(OneHandedSword.class, getGameSubject().getMainHandArtifact().getClass());
        assertEquals(OneHandedSword.class, getGameSubject().getOffHandArtifact().getClass());
    }

    @Test
    public void testTwoOneHandedWeaponsViaGameSubjectCanHandle() {
        assertTrue(getGameSubject().canHandle(getOneHandedSword()));
        assertTrue(getGameSubject().canHandle(getOneHandedSword(), Hand.Type.OFF));
    }

    @Test
    public void testTwoOneHandedWeaponsViaGameSubjectCantHandleTwoHanded() {
        assertFalse(getGameSubject().canHandle(getTwoHandedSword()));
    }

    @Override
    protected ArtifactHandlingStrategy getArtifactHandlingStrategy() {
        return twoOneHandedWeapons;
    }
}