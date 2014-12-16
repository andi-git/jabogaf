package at.ahammer.boardgame.core.artifact;

import at.ahammer.boardgame.api.artifact.Artifact;
import at.ahammer.boardgame.api.artifact.ArtifactCast;
import at.ahammer.boardgame.api.artifact.shield.Shield;
import at.ahammer.boardgame.api.artifact.shield.ShieldType;
import at.ahammer.boardgame.api.artifact.weapon.Weapon;
import at.ahammer.boardgame.api.artifact.weapon.WeaponType;
import at.ahammer.boardgame.core.artifact.shield.NullShield;
import at.ahammer.boardgame.core.artifact.weapon.NullWeapon;
import at.ahammer.boardgame.core.test.ArquillianGameContext;
import at.ahammer.boardgame.core.test.ArquillianGameContextTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.*;

@RunWith(ArquillianGameContext.class)
public class ArtifactCastProducerTest extends ArquillianGameContextTest {

    @Inject
    private ArtifactCast<Weapon> weaponArtifactCast;

    @Inject
    private ArtifactCast<Shield> shieldArtifactCast;

    @Test
    public void testGetWeaponCast() throws Exception {
        Assert.assertEquals(MyWeapon.class, weaponArtifactCast.cast(new MyWeapon()).getClass());
        Assert.assertEquals(NullWeapon.class, weaponArtifactCast.cast(new MyArtifact()).getClass());
    }
    @Test
    public void testGetShieldCast() throws Exception {
        Assert.assertEquals(MyShield.class, shieldArtifactCast.cast(new MyShield()).getClass());
        Assert.assertEquals(NullShield.class, shieldArtifactCast.cast(new MyArtifact()).getClass());
    }

    private static class MyWeapon extends Weapon {

        public MyWeapon() {
            super("myWeapon", WeaponType.ONE_HAND_ATTACK);
        }
    }

    private static class MyShield extends Shield {

        public MyShield() {
            super("myShield", ShieldType.ONE_HAND_DEFENSE);
        }
    }

    private static class MyArtifact extends Artifact {

        protected MyArtifact() {
            super("myArtifact");
        }
    }
}