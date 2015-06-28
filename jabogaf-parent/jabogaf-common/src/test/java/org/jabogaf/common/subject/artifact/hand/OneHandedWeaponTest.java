package org.jabogaf.common.subject.artifact.hand;

import org.jabogaf.api.subject.artifact.hand.ArtifactHandlingException;
import org.jabogaf.api.subject.artifact.hand.ArtifactHandlingStrategy;
import org.jabogaf.api.subject.hand.Hand;
import org.jabogaf.common.artifact.weapon.OneHandedSword;
import org.jabogaf.core.artifact.ArtifactNull;
import org.jabogaf.core.test.ArquillianGameContext;
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
        addArtifactToHand(oneHandedWeapon, getOneHandedSword(), Hand.Type.MAIN);
        assertEquals(OneHandedSword.class, getArtifactHolder().getMainHandArtifact().getClass());
        assertEquals(ArtifactNull.class, getArtifactHolder().getOffHandArtifact().getClass());
    }

    @Test(expected = ArtifactHandlingException.class)
    public void testOneHandedWeaponAddArtifactExceptionTwoHanded() throws ArtifactHandlingException {
        addArtifactToHand(oneHandedWeapon, getTwoHandedSword(), Hand.Type.MAIN);
    }

    @Test(expected = ArtifactHandlingException.class)
    public void testOneHandedWeaponOneShieldAddArtifactExceptionOneHandedOff() throws ArtifactHandlingException {
        addArtifactToHand(oneHandedWeapon, getOneHandedSword(), Hand.Type.OFF);
    }

    @Test
    public void testOneHandedWeaponViaGameSubject() throws ArtifactHandlingException {
        getArtifactHolder().addArtifact(getOneHandedSword());
        assertEquals(OneHandedSword.class, getArtifactHolder().getMainHandArtifact().getClass());
    }

    @Test
    public void testOneHandedWeaponViaGameSubjectCanHandle() {
        assertTrue(getArtifactHolder().canHandle(getOneHandedSword()));
    }

    @Test
    public void testOneHandedWeaponViaGameSubjectCantHandleTwoHanded() {
        assertFalse(getArtifactHolder().canHandle(getTwoHandedSword()));
    }

    @Test
    public void testOneHandedWeaponViaGameSubjectCantHandleWrongHand() {
        assertFalse(getArtifactHolder().canHandle(getOneHandedSword(), Hand.Type.OFF));
    }

    @Override
    protected ArtifactHandlingStrategy getArtifactHandlingStrategy() {
        return oneHandedWeapon;
    }
}