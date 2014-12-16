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
public class OneHandedWeaponTest extends ArtifactHandTest {

    @Inject
    private OneHandedWeapon oneHandedWeapon;

    @Test
    public void testOneHandedWeaponCanHandle() {
        assertTrue(oneHandedWeapon.canHandle(getOneHandedSword(), Hand.Type.MAIN));
        assertTrue(oneHandedWeapon.canHandle(getOneHandedAxe(), Hand.Type.MAIN));
        assertFalse(oneHandedWeapon.canHandle(getOneHandedSword(), Hand.Type.OFF));
        assertFalse(oneHandedWeapon.canHandle(getTwoHandedSword(), Hand.Type.MAIN));
        assertFalse(oneHandedWeapon.canHandle(getTwoHandedAxe(), Hand.Type.MAIN));
        assertFalse(oneHandedWeapon.canHandle(getTwoHandedSword(), Hand.Type.OFF));
        assertFalse(oneHandedWeapon.canHandle(getSimpleShield(), Hand.Type.OFF));
        assertFalse(oneHandedWeapon.canHandle(getSimpleShield(), Hand.Type.MAIN));
    }

    @Test
    public void testOneHandedWeaponAddArtifact() throws ArtifactHandlingException {
        oneHandedWeapon.addArtifactToHand(getContext(getOneHandedSword(), Hand.Type.MAIN));
        assertEquals(OneHandedSword.class, getGameSubject().getMainHandArtifact().getClass());
        assertEquals(NullArtifact.class, getGameSubject().getOffHandArtifact().getClass());
    }

    @Test(expected = ArtifactHandlingException.class)
    public void testOneHandedWeaponAddArtifactExceptionTwoHanded() throws ArtifactHandlingException {
        oneHandedWeapon.addArtifactToHand(getContext(getTwoHandedSword(), Hand.Type.MAIN));
    }

    @Test(expected = ArtifactHandlingException.class)
    public void testOneHandedWeaponOneShieldAddArtifactExceptionOneHandedOff() throws ArtifactHandlingException {
        oneHandedWeapon.addArtifactToHand(getContext(getOneHandedSword(), Hand.Type.OFF));
    }

    @Test
    public void testOneHandedWeaponViaGameSubject() throws ArtifactHandlingException {
        getGameSubject().addArtifact(getOneHandedSword());
        assertEquals(OneHandedSword.class, getGameSubject().getMainHandArtifact().getClass());
    }

    @Test
    public void testOneHandedWeaponViaGameSubjectCanHandle() {
        assertTrue(getGameSubject().canHandle(getOneHandedSword()));
    }

    @Test
    public void testOneHandedWeaponViaGameSubjectCantHandleTwoHanded() {
        assertFalse(getGameSubject().canHandle(getTwoHandedSword()));
    }

    @Test
    public void testOneHandedWeaponViaGameSubjectCantHandleWrongHand() {
        assertFalse(getGameSubject().canHandle(getOneHandedSword(), Hand.Type.OFF));
    }

    @Override
    protected ArtifactHandlingStrategy getArtifactHandlingStrategy() {
        return oneHandedWeapon;
    }
}