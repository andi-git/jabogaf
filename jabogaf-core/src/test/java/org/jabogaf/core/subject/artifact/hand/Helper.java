package org.jabogaf.core.subject.artifact.hand;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Helper {

    /*
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

    public static class MyGameSubject extends GameSubjectNull {

        public MyGameSubject() {
            super("myGameSubject", new FieldNull());
        }

        public SetterOfArtifactsForHands getSetterOfArtifactsForHands() {
            return (main, off) -> {
                getMainHand().setArtifact(main);
                getOffHand().setArtifact(off);
            };
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
    */
}
