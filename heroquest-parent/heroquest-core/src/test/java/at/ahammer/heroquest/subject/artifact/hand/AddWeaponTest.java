package at.ahammer.heroquest.subject.artifact.hand;

import at.ahammer.boardgame.cdi.ArquillianGameContext;
import at.ahammer.boardgame.cdi.BeanManagerProducer;
import at.ahammer.boardgame.entity.artifact.ArtifactException;
import at.ahammer.heroquest.artifact.weapon.OneHandedAxe;
import at.ahammer.heroquest.artifact.weapon.OneHandedSword;
import at.ahammer.heroquest.artifact.weapon.TwoHandedSword;
import at.ahammer.heroquest.subject.Barbarian;
import at.ahammer.heroquest.subject.Mage;
import org.jboss.arquillian.junit.Arquillian;
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
public class AddWeaponTest extends BeanManagerProducer {

    @Inject
    private BeanManager beanManager;
    private Barbarian barbarian;
    private Mage mage;
    private OneHandedSword oneHandedSword;
    private OneHandedAxe oneHandedAxe;
    private TwoHandedSword twoHandedSword;

    @Before
    public void setUp() {
        // FIXME create own setup
//        this.barbarian = new Barbarian(beanManager);
//        this.mage = new Mage(beanManager);
//        this.oneHandedSword = new OneHandedSword(beanManager);
//        this.oneHandedAxe = new OneHandedAxe(beanManager);
//        this.twoHandedSword = new TwoHandedSword(beanManager);
    }

    @Test
    public void addWeapon() throws ArtifactException {
        this.barbarian = new Barbarian(beanManager);
        this.oneHandedSword = new OneHandedSword(beanManager);
        this.oneHandedAxe = new OneHandedAxe(beanManager);
        this.twoHandedSword = new TwoHandedSword(beanManager);

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
        this.mage = new Mage(beanManager);
        this.oneHandedAxe = new OneHandedAxe(beanManager);
        this.twoHandedSword = new TwoHandedSword(beanManager);

        Assert.assertNull(mage.getLeftHand());
        Assert.assertNull(mage.getRightHand());

        mage.addArtifactForHand(oneHandedAxe);
        Assert.assertNotNull(mage.getRightHand());
        Assert.assertEquals("One Handed Axe", mage.getRightHand().getName());
        Assert.assertNull(mage.getLeftHand());

        mage.addArtifactForHand(twoHandedSword);
    }
}
