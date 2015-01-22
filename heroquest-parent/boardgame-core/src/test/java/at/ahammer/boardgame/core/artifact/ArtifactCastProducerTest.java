package at.ahammer.boardgame.core.artifact;

import at.ahammer.boardgame.api.artifact.Artifact;
import at.ahammer.boardgame.api.artifact.ArtifactCast;
import at.ahammer.boardgame.api.artifact.shield.Shield;
import at.ahammer.boardgame.api.artifact.shield.ShieldType;
import at.ahammer.boardgame.api.artifact.weapon.Weapon;
import at.ahammer.boardgame.api.artifact.weapon.WeaponType;
import at.ahammer.boardgame.core.artifact.shield.ShieldBasic;
import at.ahammer.boardgame.core.artifact.shield.ShieldNull;
import at.ahammer.boardgame.core.artifact.weapon.NullWeapon;
import at.ahammer.boardgame.core.artifact.weapon.WeaponBasic;
import at.ahammer.boardgame.core.test.ArquillianGameContext;
import at.ahammer.boardgame.core.test.ArquillianGameContextTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

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
        Assert.assertEquals(ShieldNull.class, shieldArtifactCast.cast(new MyArtifact()).getClass());
    }

    private static class MyWeapon extends WeaponBasic {

        public MyWeapon() {
            super("myWeapon", WeaponType.ONE_HAND_ATTACK);
        }
    }

    private static class MyShield extends ShieldBasic {

        public MyShield() {
            super("myShield", ShieldType.ONE_HAND_DEFENSE);
        }
    }

    private static class MyArtifact extends ArtifactBasic {

        protected MyArtifact() {
            super("myArtifact");
        }
    }
}