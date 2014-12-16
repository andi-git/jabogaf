package at.ahammer.boardgame.core.subject.artifact.hand;

import at.ahammer.boardgame.api.subject.artifact.NullArtifact;
import at.ahammer.boardgame.api.subject.artifact.hand.*;
import at.ahammer.boardgame.core.test.ArquillianGameContext;
import at.ahammer.boardgame.core.test.ArquillianGameContextTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(ArquillianGameContext.class)
public class AddArtifactToHandStrategyGeneralBasicTest extends ArquillianGameContextTest {

    @Inject
    private AddArtifactToHandStrategyGeneralBasic addArtifactToHandStrategyGeneralBasic;

    @Inject
    private Helper helper;

    @Test
    public void testAddArtifactToHandOk() throws Exception {
        AddArtifactToHandStrategyContext context = helper.getDefaultContext();
        Assert.assertEquals(NullArtifact.class, context.getMainHandArtifact().getClass());
        Assert.assertEquals(NullArtifact.class, context.getOffHandArtifact().getClass());
        addArtifactToHandStrategyGeneralBasic.addArtifactToHand(context);
        Assert.assertEquals(Helper.MyArtifact.class, context.getMainHandArtifact().getClass());
        Assert.assertEquals(NullArtifact.class, context.getOffHandArtifact().getClass());
    }

    @Test(expected = ArtifactHandlingException.class)
    public void testAddArtifactToHandUnableToHandle() throws Exception {
        AddArtifactToHandStrategyContext context = helper.getDefaultContext();
        context.setCanHandleArtifactStrategy(helper.getCanHandleArtifactStrategyFalse());
        Assert.assertEquals(NullArtifact.class, context.getMainHandArtifact().getClass());
        Assert.assertEquals(NullArtifact.class, context.getOffHandArtifact().getClass());
        addArtifactToHandStrategyGeneralBasic.addArtifactToHand(context);
    }

    @Test
    public void testAddArtifactToHandTwoHandedWeapon() throws Exception {
        Helper.MyTwoHandedWeapon myTwoHandedWeapon = new Helper.MyTwoHandedWeapon();
        AddArtifactToHandStrategyContext context = helper.getDefaultContext();
        context.addArtifactsToHands(myTwoHandedWeapon, myTwoHandedWeapon);
        Assert.assertEquals(Helper.MyTwoHandedWeapon.class, context.getMainHandArtifact().getClass());
        Assert.assertEquals(Helper.MyTwoHandedWeapon.class, context.getOffHandArtifact().getClass());
        addArtifactToHandStrategyGeneralBasic.addArtifactToHand(context);
        Assert.assertEquals(Helper.MyArtifact.class, context.getMainHandArtifact().getClass());
        Assert.assertEquals(NullArtifact.class, context.getOffHandArtifact().getClass());
    }
}