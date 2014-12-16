package at.ahammer.boardgame.core.subject.artifact.hand;

import at.ahammer.boardgame.api.artifact.Artifact;
import at.ahammer.boardgame.api.artifact.ArtifactCast;
import at.ahammer.boardgame.api.artifact.weapon.Weapon;
import at.ahammer.boardgame.api.artifact.weapon.WeaponType;
import at.ahammer.boardgame.api.behavior.look.LookBehavior;
import at.ahammer.boardgame.api.behavior.move.MoveBehavior;
import at.ahammer.boardgame.api.subject.GameSubject;
import at.ahammer.boardgame.api.subject.artifact.hand.*;
import at.ahammer.boardgame.api.subject.hand.Hand;
import at.ahammer.boardgame.core.board.field.FieldNull;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class Helper {

    public AddArtifactToHandStrategyContext getDefaultContext() {
        Artifact artifact = new MyArtifact();
        MyGameSubject gameSubject = new MyGameSubject();
        return new AddArtifactToHandStrategyContext().setArtifact(artifact).
                setHandType(Hand.Type.MAIN).
                setGameSubject(gameSubject).
                setSetterOfArtifactsForHands(gameSubject.getSetterOfArtifactsForHands()).
                setCanHandleArtifactStrategy(getCanHandleArtifactStrategyTrue()).
                setAddArtifactToHandStrategyConcrete(getAddArtifactToHandStrategyConcrete());
    }

    public CanHandleArtifactStrategy getCanHandleArtifactStrategyTrue() {
        return (artifact, handType) -> {
            return true;
        };
    }

    public CanHandleArtifactStrategy getCanHandleArtifactStrategyFalse() {
        return (artifact, handType) -> {
            return false;
        };
    }

    public AddArtifactToHandStrategyConcrete getAddArtifactToHandStrategyConcrete() {
        return (addArtifactToHandStrategyContext) -> {
            addArtifactToHandStrategyContext.addArtifactToMainHand();
        };
    }

    public static class MyArtifact extends Artifact {

        public MyArtifact() {
            super("myArtifact");
        }
    }

    public static class MyTwoHandedWeapon extends Weapon {

        public MyTwoHandedWeapon() {
            super("myTwoHandedWeapon", WeaponType.TWO_HAND_ATTACK);
        }
    }

    public static class MyOneHandedWeapon extends Weapon {

        public MyOneHandedWeapon() {
            super("myOneHandedWeapon", WeaponType.ONE_HAND_ATTACK);
        }
    }

    public static class MyGameSubject extends GameSubject {

        public MyGameSubject() {
            super("myGameSubject", new FieldNull());
        }

        @Override
        public MoveBehavior getMoveBehavior() {
            return null;
        }

        @Override
        public LookBehavior getLookBehavior() {
            return null;
        }

        @Override
        protected void changeMoveBehavior(MoveBehavior moveBehavior) {

        }

        @Override
        protected void changeLookBehavior(LookBehavior lookBehavior) {

        }

        public SetterOfArtifactsForHands getSetterOfArtifactsForHands() {
            return (main, off) -> {
                getMainHand().setArtifact(main);
                getOffHand().setArtifact(off);
            };
        }

        public void setMain(Artifact artifact) {
            getMainHand().setArtifact(artifact);
        }

        public void setOff(Artifact artifact) {
            getOffHand().setArtifact(artifact);
        }

        @Override
        public void addArtifactHandlingStrategy(ArtifactHandlingStrategy artifactHandlingStrategy) {
            super.addArtifactHandlingStrategy(artifactHandlingStrategy);
        }

        @Override
        public List<ArtifactHandlingStrategy> getArtifactHandlingStrategies() {
            return super.getArtifactHandlingStrategies();
        }

        @Override
        public void clearArtifactHandlingStrategies() {
            super.clearArtifactHandlingStrategies();
        }

        @Override
        public void removeArtifactHandlingStrategy(ArtifactHandlingStrategy artifactHandlingStrategy) {
            super.removeArtifactHandlingStrategy(artifactHandlingStrategy);
        }
    }

    @ApplicationScoped
    public static class ArtifactHandlingStrategyCanHandleOneHandWeapon extends ArtifactHandlingStrategy {

        @Inject
        private ArtifactCast<Weapon> weaponArtifactCast;

        @Override
        protected AddArtifactToHandStrategyConcrete getAddArtifactToHandStrategyConcrete() {
            return (addArtifactToHandStrategyContext) -> {
                addArtifactToHandStrategyContext.addArtifactToMainHand();
            };
        }

        @Override
        protected CanHandleArtifactStrategy getCanHandleArtifactStrategy() {
            return (artifact, handType) -> {
                return weaponArtifactCast.cast(artifact).getWeaponType() == WeaponType.ONE_HAND_ATTACK;
            };
        }
    }

    @ApplicationScoped
    public static class ArtifactHandlingStrategyCanHandleTwoHandWeapon extends ArtifactHandlingStrategy {

        @Inject
        private ArtifactCast<Weapon> weaponArtifactCast;

        @Override
        protected AddArtifactToHandStrategyConcrete getAddArtifactToHandStrategyConcrete() {
            return (addArtifactToHandStrategyContext) -> {
                addArtifactToHandStrategyContext.addArtifactToMainHand();
            };
        }

        @Override
        protected CanHandleArtifactStrategy getCanHandleArtifactStrategy() {
            return (artifact, handType) -> {
                return weaponArtifactCast.cast(artifact).getWeaponType() == WeaponType.TWO_HAND_ATTACK;
            };
        }
    }
}
