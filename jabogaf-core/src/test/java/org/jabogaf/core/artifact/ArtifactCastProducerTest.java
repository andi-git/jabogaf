package org.jabogaf.core.artifact;

import org.jabogaf.api.artifact.ArtifactCast;
import org.jabogaf.api.artifact.shield.Shield;
import org.jabogaf.api.artifact.shield.ShieldType;
import org.jabogaf.api.artifact.weapon.Weapon;
import org.jabogaf.api.artifact.weapon.WeaponType;
import org.jabogaf.core.artifact.shield.ShieldBasic;
import org.jabogaf.core.artifact.shield.ShieldNull;
import org.jabogaf.core.artifact.weapon.NullWeapon;
import org.jabogaf.core.artifact.weapon.WeaponBasic;
import org.jabogaf.core.test.ArquillianGameContext;
import org.jabogaf.core.test.ArquillianGameContextTest;
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