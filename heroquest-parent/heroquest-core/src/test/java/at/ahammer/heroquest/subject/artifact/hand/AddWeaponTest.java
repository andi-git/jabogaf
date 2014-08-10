package at.ahammer.heroquest.subject.artifact.hand;

import at.ahammer.boardgame.test.util.ArquillianGameContext;
import at.ahammer.boardgame.test.util.ArquillianGameContextTest;
import at.ahammer.boardgame.entity.artifact.ArtifactException;
import at.ahammer.boardgame.test.util.BeforeInGameContext;
import at.ahammer.boardgame.test.util.RunAllMethodsInGameContext;
import at.ahammer.heroquest.artifact.weapon.OneHandedAxe;
import at.ahammer.heroquest.artifact.weapon.OneHandedSword;
import at.ahammer.heroquest.artifact.weapon.TwoHandedSword;
import at.ahammer.heroquest.subject.Barbarian;
import at.ahammer.heroquest.subject.Mage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

/**
 * Created by andreas on 26.07.14.
 */
@RunWith(ArquillianGameContext.class)
public class AddWeaponTest extends ArquillianGameContextTest implements RunAllMethodsInGameContext {

    private Barbarian barbarian;
    private Mage mage;
    private OneHandedSword oneHandedSword;
    private OneHandedAxe oneHandedAxe;
    private TwoHandedSword twoHandedSword;

    @BeforeInGameContext
    public void setUp() {
        this.barbarian = new Barbarian();
        this.oneHandedSword = new OneHandedSword();
        this.oneHandedAxe = new OneHandedAxe();
        this.twoHandedSword = new TwoHandedSword();
        this.mage = new Mage();
    }

    @Test
    public void addWeapon() throws ArtifactException {
        Assert.assertNull(barbarian.getLeftHand());
        Assert.assertNull(barbarian.getRightHand());

        barbarian.addArtifactForHand(oneHandedAxe);
        Assert.assertNotNull(barbarian.getRightHand());
        Assert.assertEquals("One Handed Axe", barbarian.getRightHand().getName());
        Assert.assertNull(barbarian.getLeftHand());

        barbarian.addArtifactForHand(oneHandedSword);
        Assert.assertNotNull(barbarian.getRightHand());
        Assert.assertEquals("One Handed Sword", barbarian.getRightHand().getName());
        Assert.assertNotNull(barbarian.getLeftHand());
        Assert.assertEquals("One Handed Axe", barbarian.getLeftHand().getName());

        barbarian.addArtifactForHand(twoHandedSword);
        Assert.assertNotNull(barbarian.getRightHand());
        Assert.assertEquals("Two Handed Sword", barbarian.getRightHand().getName());
        Assert.assertNotNull(barbarian.getLeftHand());
        Assert.assertEquals("Two Handed Sword", barbarian.getLeftHand().getName());

        barbarian.addArtifactForHand(oneHandedAxe);
        Assert.assertNotNull(barbarian.getRightHand());
        Assert.assertEquals("One Handed Axe", barbarian.getRightHand().getName());
        Assert.assertNull(barbarian.getLeftHand());

    }

    @Test(expected = ArtifactException.class)
    public void addWeaponException() throws ArtifactException {
        Assert.assertNull(mage.getLeftHand());
        Assert.assertNull(mage.getRightHand());

        mage.addArtifactForHand(oneHandedAxe);
        Assert.assertNotNull(mage.getRightHand());
        Assert.assertEquals("One Handed Axe", mage.getRightHand().getName());
        Assert.assertNull(mage.getLeftHand());

        mage.addArtifactForHand(twoHandedSword);
    }

}
