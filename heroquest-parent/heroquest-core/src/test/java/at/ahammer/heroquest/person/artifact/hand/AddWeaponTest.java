package at.ahammer.heroquest.person.artifact.hand;

import at.ahammer.heroquest.entity.artifact.Artifact;
import at.ahammer.heroquest.entity.artifact.ArtifactException;
import at.ahammer.heroquest.entity.artifact.weapon.OneHandedAxe;
import at.ahammer.heroquest.entity.artifact.weapon.OneHandedSword;
import at.ahammer.heroquest.entity.artifact.weapon.TwoHandedSword;
import at.ahammer.heroquest.entity.person.Barbarian;
import at.ahammer.heroquest.entity.person.Mage;
import at.ahammer.heroquest.entity.person.GameSubject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

/**
 * Created by andreas on 26.07.14.
 */
@RunWith(Arquillian.class)
public class AddWeaponTest {

    @Inject
    private Barbarian barbarian;
    @Inject
    private Mage mage;
    @Inject
    private OneHandedSword oneHandedSword;
    @Inject
    private OneHandedAxe oneHandedAxe;
    @Inject
    private TwoHandedSword twoHandedSword;

    @Deployment
    public static JavaArchive createDeployment() {
        JavaArchive archive = ShrinkWrap.create(JavaArchive.class, AddWeaponTest.class.getSimpleName() + ".jar").//
                addPackages(true, Artifact.class.getPackage()).//
                addPackages(true, GameSubject.class.getPackage()).//
                addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        System.out.println(archive.toString(true));
        return archive;
    }

    @Before
    public void setUp() {
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
