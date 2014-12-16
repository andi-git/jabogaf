package at.ahammer.boardgame.core.subject.artifact.hand;

import at.ahammer.boardgame.api.subject.artifact.hand.AddArtifactToHandStrategyContext;
import at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandManager;
import at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingException;
import at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingStrategy;
import at.ahammer.boardgame.core.cdi.GameContextTest;
import at.ahammer.boardgame.core.test.ArquillianGameContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(ArquillianGameContext.class)
public class ArtifactHandManagerBasicTest extends GameContextTest {

    @Inject
    private ArtifactHandManager artifactHandManager;

    @Inject
    private Helper helper;

    @Inject
    private Helper.ArtifactHandlingStrategyCanHandleOneHandWeapon artifactHandlingStrategyCanHandleOneHandWeapon;

    @Inject
    private Helper.ArtifactHandlingStrategyCanHandleTwoHandWeapon artifactHandlingStrategyCanHandleTwoHandWeapon;

    @Test
    public void testAddArtifact() throws Exception {
        // default: can handle one-handed weapon
        Helper.MyOneHandedWeapon oneHandedWeapon = new Helper.MyOneHandedWeapon();
        AddArtifactToHandStrategyContext context = getDefaultContextWithOneHandedWeapon(oneHandedWeapon);
        artifactHandManager.addArtifact(context);
        Assert.assertEquals(Helper.MyOneHandedWeapon.class, context.getMainHandArtifact().getClass());

        // add the strategy to handle two-handed weapons
        Helper.MyTwoHandedWeapon twoHandedWeapon = new Helper.MyTwoHandedWeapon();
        addAddArtifactHandlingStrategy(context, artifactHandlingStrategyCanHandleTwoHandWeapon);
        context.setArtifact(twoHandedWeapon);
        artifactHandManager.addArtifact(context);
        Assert.assertEquals(Helper.MyTwoHandedWeapon.class, context.getMainHandArtifact().getClass());
        context.setArtifact(oneHandedWeapon);
        artifactHandManager.addArtifact(context);
        Assert.assertEquals(Helper.MyOneHandedWeapon.class, context.getMainHandArtifact().getClass());

    }

    @Test(expected = ArtifactHandlingException.class)
    public void testAddArtifactCantHandle() throws Exception {
        // default: unable to handle two-handed weapon
        Helper.MyTwoHandedWeapon twoHandedWeapon = new Helper.MyTwoHandedWeapon();
        AddArtifactToHandStrategyContext context = getDefaultContext();
        context.setArtifact(twoHandedWeapon);
        artifactHandManager.addArtifact(context);
    }

    @Test
    public void testCanHandleOk() throws Exception {
        // default: can handle one-handed weapon
        Helper.MyOneHandedWeapon oneHandedWeapon = new Helper.MyOneHandedWeapon();
        AddArtifactToHandStrategyContext context = getDefaultContextWithOneHandedWeapon(oneHandedWeapon);
        Assert.assertTrue(artifactHandManager.canHandle(context));

        // default: unable to handle two-handed weapon
        Helper.MyTwoHandedWeapon twoHandedWeapon = new Helper.MyTwoHandedWeapon();
        context.setArtifact(twoHandedWeapon);
        Assert.assertFalse(artifactHandManager.canHandle(context));

        // add the strategy to handle two-handed weapons
        addAddArtifactHandlingStrategy(context, artifactHandlingStrategyCanHandleTwoHandWeapon);
        context.setArtifact(oneHandedWeapon);
        Assert.assertTrue(artifactHandManager.canHandle(context));
        context.setArtifact(twoHandedWeapon);
        Assert.assertTrue(artifactHandManager.canHandle(context));
    }

    private AddArtifactToHandStrategyContext getDefaultContext() {
        AddArtifactToHandStrategyContext context = helper.getDefaultContext();
        addAddArtifactHandlingStrategy(context, artifactHandlingStrategyCanHandleOneHandWeapon);
        return context;
    }

    private AddArtifactToHandStrategyContext getDefaultContextWithOneHandedWeapon(Helper.MyOneHandedWeapon oneHandedWeapon) {
        AddArtifactToHandStrategyContext context = getDefaultContext();
        context.setArtifact(oneHandedWeapon);
        return context;
    }

    @Test
    public void testCanHandleNoStrategy() throws Exception {
        Assert.assertFalse(artifactHandManager.canHandle(helper.getDefaultContext()));
    }

    public void addAddArtifactHandlingStrategy(AddArtifactToHandStrategyContext context, ArtifactHandlingStrategy artifactHandlingStrategy) {
        ((Helper.MyGameSubject) context.getGameSubject()).addArtifactHandlingStrategy(artifactHandlingStrategy);
    }
}