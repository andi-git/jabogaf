package at.ahammer.boardgame.core.subject.artifact.hand;

import at.ahammer.boardgame.api.subject.artifact.ArtifactHolder;
import at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingException;
import at.ahammer.boardgame.core.subject.artifact.ArtifactHolderBasic;
import at.ahammer.boardgame.core.test.ArquillianGameContext;
import at.ahammer.boardgame.core.test.ArquillianGameContextTest;
import at.ahammer.boardgame.core.test.BeforeInGameContext;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.*;

@RunWith(ArquillianGameContext.class)
public class ArtifactHolderBasicTest extends ArquillianGameContextTest {

    private ArtifactHolder artifactHolder;

    @Inject
    private ArtifactHandlingStrategyCanHandleOneHandWeapon artifactHandlingStrategyCanHandleOneHandWeapon;

    @Inject
    private ArtifactHandlingStrategyCanHandleTwoHandWeapon artifactHandlingStrategyCanHandleTwoHandWeapon;

    @BeforeInGameContext
    public void before() {
        artifactHolder = new ArtifactHolderBasic();
        artifactHolder.addArtifactHandlingStrategy(artifactHandlingStrategyCanHandleOneHandWeapon);
    }

    @Test
    public void testAddArtifact() throws Exception {
        // default: can handle one-handed weapon
        MyOneHandedWeapon oneHandedWeapon = new MyOneHandedWeapon();
        artifactHolder.addArtifact(oneHandedWeapon);
        assertEquals(MyOneHandedWeapon.class, artifactHolder.getMainHandArtifact().getClass());

        // add the strategy to handle two-handed weapons
        MyTwoHandedWeapon twoHandedWeapon = new MyTwoHandedWeapon();
        artifactHolder.addArtifactHandlingStrategy(artifactHandlingStrategyCanHandleTwoHandWeapon);
        artifactHolder.addArtifact(twoHandedWeapon);
        assertEquals(MyTwoHandedWeapon.class, artifactHolder.getMainHandArtifact().getClass());
        artifactHolder.addArtifact(oneHandedWeapon);
        assertEquals(MyOneHandedWeapon.class, artifactHolder.getMainHandArtifact().getClass());

    }

    @Test(expected = ArtifactHandlingException.class)
    public void testAddArtifactCantHandle() throws Exception {
        // default: unable to handle two-handed weapon
        MyTwoHandedWeapon twoHandedWeapon = new MyTwoHandedWeapon();
        artifactHolder.addArtifact(twoHandedWeapon);
    }

    @Test
    public void testCanHandleOk() throws Exception {
        // default: can handle one-handed weapon
        MyOneHandedWeapon oneHandedWeapon = new MyOneHandedWeapon();
        assertTrue(artifactHolder.canHandle(oneHandedWeapon));

        // default: unable to handle two-handed weapon
        MyTwoHandedWeapon twoHandedWeapon = new MyTwoHandedWeapon();
        assertFalse(artifactHolder.canHandle(twoHandedWeapon));

        // add the strategy to handle two-handed weapons
        artifactHolder.addArtifactHandlingStrategy(artifactHandlingStrategyCanHandleTwoHandWeapon);
        assertTrue(artifactHolder.canHandle(oneHandedWeapon));
        assertTrue(artifactHolder.canHandle(twoHandedWeapon));
    }

    @Test
    public void testCanHandleNoStrategy() throws Exception {
        artifactHolder.clearArtifactHandlingStrategies();
        assertFalse(artifactHolder.canHandle(artifactHandlingStrategyCanHandleOneHandWeapon));
    }
}