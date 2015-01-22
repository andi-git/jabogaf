package at.ahammer.boardgame.common.subject.artifact.hand;

import at.ahammer.boardgame.api.artifact.Artifact;
import at.ahammer.boardgame.api.subject.artifact.ArtifactHolder;
import at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingException;
import at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingStrategy;
import at.ahammer.boardgame.api.subject.hand.Hand;
import at.ahammer.boardgame.common.artifact.shield.SimpleShield;
import at.ahammer.boardgame.common.artifact.weapon.OneHandedAxe;
import at.ahammer.boardgame.common.artifact.weapon.OneHandedSword;
import at.ahammer.boardgame.common.artifact.weapon.TwoHandedAxe;
import at.ahammer.boardgame.common.artifact.weapon.TwoHandedSword;
import at.ahammer.boardgame.core.test.ArquillianGameContextTest;
import at.ahammer.boardgame.core.test.BeforeInGameContext;

@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public abstract class ArtifactHandTest extends ArquillianGameContextTest {

    private MyArtifactHolder artifactHolder;

    private Artifact oneHandedSword;

    private Artifact oneHandedAxe;

    private Artifact twoHandedSword;

    private Artifact twoHandedAxe;

    private Artifact simpleShield;

    @BeforeInGameContext
    public void before() {
        artifactHolder = new MyArtifactHolder();
        artifactHolder.addArtifactHandlingStrategy(getArtifactHandlingStrategy());
        artifactHolder.resetHands();
        oneHandedSword = new OneHandedSword();
        oneHandedAxe = new OneHandedAxe();
        twoHandedSword = new TwoHandedSword();
        twoHandedAxe = new TwoHandedAxe();
        simpleShield = new SimpleShield();
    }

    protected abstract ArtifactHandlingStrategy getArtifactHandlingStrategy();

    protected MyArtifactHolder getArtifactHolder() {
        return artifactHolder;
    }

    protected Artifact getOneHandedSword() {
        return oneHandedSword;
    }

    protected Artifact getOneHandedAxe() {
        return oneHandedAxe;
    }

    protected Artifact getTwoHandedSword() {
        return twoHandedSword;
    }

    protected Artifact getTwoHandedAxe() {
        return twoHandedAxe;
    }

    protected Artifact getSimpleShield() {
        return simpleShield;
    }

    protected void addArtifactToHand(ArtifactHandlingStrategy artifactHandlingStrategy, Artifact artifact, Hand.Type hand) throws ArtifactHandlingException {
        artifactHandlingStrategy.addArtifactToHand(artifact, hand, getArtifactHolder(), getArtifactHolder().createSetterOfArtifactsForHands());
    }
}
