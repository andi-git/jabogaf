package at.ahammer.boardgame.common.subject.artifact.hand;

import at.ahammer.boardgame.api.subject.artifact.NullArtifact;
import at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingException;
import at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingStrategy;
import at.ahammer.boardgame.api.subject.hand.Hand;
import at.ahammer.boardgame.common.artifact.shield.SimpleShield;
import at.ahammer.boardgame.common.artifact.weapon.OneHandedSword;
import at.ahammer.boardgame.core.test.ArquillianGameContext;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.*;

@RunWith(ArquillianGameContext.class)
public class OneHandedWeaponAndShieldTest extends ArtifactHandTest {

    @Inject
    private OneHandedWeaponAndShield oneHandedWeaponAndShield;

    @Test
    public void testOneHandedWeaponAndShieldCanHandle() {
        assertTrue(oneHandedWeaponAndShield.canHandle(getOneHandedSword(), Hand.Type.MAIN));
        assertTrue(oneHandedWeaponAndShield.canHandle(getOneHandedAxe(), Hand.Type.MAIN));
        assertFalse(oneHandedWeaponAndShield.canHandle(getOneHandedSword(), Hand.Type.OFF));
        assertFalse(oneHandedWeaponAndShield.canHandle(getTwoHandedSword(), Hand.Type.MAIN));
        assertFalse(oneHandedWeaponAndShield.canHandle(getTwoHandedAxe(), Hand.Type.MAIN));
        assertFalse(oneHandedWeaponAndShield.canHandle(getTwoHandedSword(), Hand.Type.OFF));
        assertTrue(oneHandedWeaponAndShield.canHandle(getSimpleShield(), Hand.Type.OFF));
        assertFalse(oneHandedWeaponAndShield.canHandle(getSimpleShield(), Hand.Type.MAIN));
    }

    @Test
    public void testOneHandedWeaponAndShieldAddArtifact() throws ArtifactHandlingException {
        oneHandedWeaponAndShield.addArtifactToHand(getContext(getOneHandedSword(), Hand.Type.MAIN));
        assertEquals(OneHandedSword.class, getGameSubject().getMainHandArtifact().getClass());
        assertEquals(NullArtifact.class, getGameSubject().getOffHandArtifact().getClass());

        oneHandedWeaponAndShield.addArtifactToHand(getContext(getSimpleShield(), Hand.Type.OFF));
        assertEquals(OneHandedSword.class, getGameSubject().getMainHandArtifact().getClass());
        assertEquals(SimpleShield.class, getGameSubject().getOffHandArtifact().getClass());
    }

    @Test(expected = ArtifactHandlingException.class)
    public void testOneHandedWeaponAndShieldAddArtifactExceptionTwoHanded() throws ArtifactHandlingException {
        oneHandedWeaponAndShield.addArtifactToHand(getContext(getTwoHandedSword(), Hand.Type.MAIN));
    }

    @Test(expected = ArtifactHandlingException.class)
    public void testOneHandedWeaponAndShieldAddArtifactExceptionOneHandedOff() throws ArtifactHandlingException {
        oneHandedWeaponAndShield.addArtifactToHand(getContext(getOneHandedSword(), Hand.Type.OFF));
    }

    @Test(expected = ArtifactHandlingException.class)
    public void testOneHandedWeaponAndShieldAddArtifactExceptionShieldMain() throws ArtifactHandlingException {
        oneHandedWeaponAndShield.addArtifactToHand(getContext(getSimpleShield(), Hand.Type.MAIN));
    }

    @Test
    public void testOneHandedWeaponAndShieldViaGameSubject() throws ArtifactHandlingException {
        getGameSubject().addArtifact(getOneHandedSword());
        getGameSubject().addArtifact(getSimpleShield(), Hand.Type.OFF);
        assertEquals(OneHandedSword.class, getGameSubject().getMainHandArtifact().getClass());
        assertEquals(SimpleShield.class, getGameSubject().getOffHandArtifact().getClass());
    }

    @Test
    public void testOneHandedWeaponAndShieldViaGameSubjectCanHandle() {
        assertTrue(getGameSubject().canHandle(getOneHandedSword()));
    }

    @Test
    public void testOneHandedWeaponAndShieldViaGameSubjectCantHandleTwoHanded() {
        assertFalse(getGameSubject().canHandle(getTwoHandedSword()));
    }

    @Test
    public void testOneHandedWeaponAndShieldViaGameSubjectCantHandleWeaponWrongHand() {
        assertFalse(getGameSubject().canHandle(getOneHandedSword(), Hand.Type.OFF));
    }

    @Test
    public void testOneHandedWeaponAndShieldViaGameSubjectCantHandleShieldWrongHand() {
        assertFalse(getGameSubject().canHandle(getSimpleShield(), Hand.Type.MAIN));
    }

    @Override
    protected ArtifactHandlingStrategy getArtifactHandlingStrategy() {
        return oneHandedWeaponAndShield;
    }
}