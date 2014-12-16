package at.ahammer.boardgame.common.subject.artifact.hand;

import at.ahammer.boardgame.api.subject.artifact.NullArtifact;
import at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingException;
import at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingStrategy;
import at.ahammer.boardgame.api.subject.hand.Hand;
import at.ahammer.boardgame.common.artifact.weapon.OneHandedSword;
import at.ahammer.boardgame.common.artifact.weapon.TwoHandedSword;
import at.ahammer.boardgame.core.test.ArquillianGameContext;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.*;

@RunWith(ArquillianGameContext.class)
public class TwoHandedWeaponTest extends ArtifactHandTest {

    @Inject
    private TwoHandedWeapon twoHandedWeapon;

    @Test
    public void testTwoHandedWeaponCanHandle() {
        assertFalse(twoHandedWeapon.canHandle(getOneHandedSword(), Hand.Type.MAIN));
        assertFalse(twoHandedWeapon.canHandle(getOneHandedAxe(), Hand.Type.MAIN));
        assertFalse(twoHandedWeapon.canHandle(getOneHandedSword(), Hand.Type.OFF));
        assertTrue(twoHandedWeapon.canHandle(getTwoHandedSword(), Hand.Type.MAIN));
        assertTrue(twoHandedWeapon.canHandle(getTwoHandedAxe(), Hand.Type.MAIN));
        assertTrue(twoHandedWeapon.canHandle(getTwoHandedSword(), Hand.Type.OFF));
        assertFalse(twoHandedWeapon.canHandle(getSimpleShield(), Hand.Type.OFF));
        assertFalse(twoHandedWeapon.canHandle(getSimpleShield(), Hand.Type.MAIN));
    }

    @Test
    public void testTwoHandedWeaponAddArtifact() throws ArtifactHandlingException {
        twoHandedWeapon.addArtifactToHand(getContext(getTwoHandedSword(), Hand.Type.MAIN));
        assertEquals(TwoHandedSword.class, getGameSubject().getMainHandArtifact().getClass());
        assertEquals(TwoHandedSword.class, getGameSubject().getOffHandArtifact().getClass());

        getGameSubject().resetHands();
        twoHandedWeapon.addArtifactToHand(getContext(getTwoHandedSword(), Hand.Type.OFF));
        assertEquals(TwoHandedSword.class, getGameSubject().getMainHandArtifact().getClass());
        assertEquals(TwoHandedSword.class, getGameSubject().getOffHandArtifact().getClass());
    }

    @Test(expected = ArtifactHandlingException.class)
    public void testTwoHandedWeaponAddArtifactExceptionOneHanded() throws ArtifactHandlingException {
        twoHandedWeapon.addArtifactToHand(getContext(getOneHandedSword(), Hand.Type.MAIN));
    }

    @Test(expected = ArtifactHandlingException.class)
    public void testTwoHandedWeaponAddArtifactExceptionOneHandedOff() throws ArtifactHandlingException {
        twoHandedWeapon.addArtifactToHand(getContext(getOneHandedSword(), Hand.Type.OFF));
    }

    @Test
    public void testTwoHandedWeaponViaGameSubject() throws ArtifactHandlingException {
        getGameSubject().addArtifact(getTwoHandedSword());
        assertEquals(TwoHandedSword.class, getGameSubject().getMainHandArtifact().getClass());
        assertEquals(TwoHandedSword.class, getGameSubject().getOffHandArtifact().getClass());
    }

    @Test
    public void testTwoHandedWeaponViaGameSubjectCanHandle() {
        assertTrue(getGameSubject().canHandle(getTwoHandedSword()));
    }

    @Test
    public void testTwoHandedWeaponViaGameSubjectCanHandleOneHanded() {
        assertFalse(getGameSubject().canHandle(getOneHandedSword()));
    }

    @Override
    protected ArtifactHandlingStrategy getArtifactHandlingStrategy() {
        return twoHandedWeapon;
    }
}