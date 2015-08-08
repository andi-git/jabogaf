package org.jabogaf.common.subject.artifact.hand;

import org.jabogaf.api.subject.artifact.hand.ArtifactHandlingException;
import org.jabogaf.api.subject.artifact.hand.ArtifactHandlingStrategy;
import org.jabogaf.api.subject.hand.Hand;
import org.jabogaf.common.artifact.shield.SimpleShield;
import org.jabogaf.common.artifact.weapon.OneHandedSword;
import org.jabogaf.core.artifact.ArtifactNull;
import org.jabogaf.test.cdi.ArquillianGameContext;
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
        addArtifactToHand(oneHandedWeaponAndShield, getOneHandedSword(), Hand.Type.MAIN);
        assertEquals(OneHandedSword.class, getArtifactHolder().getMainHandArtifact().getClass());
        assertEquals(ArtifactNull.class, getArtifactHolder().getOffHandArtifact().getClass());

        addArtifactToHand(oneHandedWeaponAndShield, getSimpleShield(), Hand.Type.OFF);
        assertEquals(OneHandedSword.class, getArtifactHolder().getMainHandArtifact().getClass());
        assertEquals(SimpleShield.class, getArtifactHolder().getOffHandArtifact().getClass());
    }

    @Test(expected = ArtifactHandlingException.class)
    public void testOneHandedWeaponAndShieldAddArtifactExceptionTwoHanded() throws ArtifactHandlingException {
        addArtifactToHand(oneHandedWeaponAndShield, getTwoHandedSword(), Hand.Type.MAIN);
    }

    @Test(expected = ArtifactHandlingException.class)
    public void testOneHandedWeaponAndShieldAddArtifactExceptionOneHandedOff() throws ArtifactHandlingException {
        addArtifactToHand(oneHandedWeaponAndShield, getOneHandedSword(), Hand.Type.OFF);
    }

    @Test(expected = ArtifactHandlingException.class)
    public void testOneHandedWeaponAndShieldAddArtifactExceptionShieldMain() throws ArtifactHandlingException {
        addArtifactToHand(oneHandedWeaponAndShield, getSimpleShield(), Hand.Type.MAIN);
    }

    @Test
    public void testOneHandedWeaponAndShieldViaGameSubject() throws ArtifactHandlingException {
        getArtifactHolder().addArtifact(getOneHandedSword());
        getArtifactHolder().addArtifact(getSimpleShield(), Hand.Type.OFF);
        assertEquals(OneHandedSword.class, getArtifactHolder().getMainHandArtifact().getClass());
        assertEquals(SimpleShield.class, getArtifactHolder().getOffHandArtifact().getClass());
    }

    @Test
    public void testOneHandedWeaponAndShieldViaGameSubjectCanHandle() {
        assertTrue(getArtifactHolder().canHandle(getOneHandedSword()));
    }

    @Test
    public void testOneHandedWeaponAndShieldViaGameSubjectCantHandleTwoHanded() {
        assertFalse(getArtifactHolder().canHandle(getTwoHandedSword()));
    }

    @Test
    public void testOneHandedWeaponAndShieldViaGameSubjectCantHandleWeaponWrongHand() {
        assertFalse(getArtifactHolder().canHandle(getOneHandedSword(), Hand.Type.OFF));
    }

    @Test
    public void testOneHandedWeaponAndShieldViaGameSubjectCantHandleShieldWrongHand() {
        assertFalse(getArtifactHolder().canHandle(getSimpleShield(), Hand.Type.MAIN));
    }

    @Override
    protected ArtifactHandlingStrategy getArtifactHandlingStrategy() {
        return oneHandedWeaponAndShield;
    }
}