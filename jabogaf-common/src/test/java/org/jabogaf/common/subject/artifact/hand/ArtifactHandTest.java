package org.jabogaf.common.subject.artifact.hand;

import org.jabogaf.api.artifact.Artifact;
import org.jabogaf.api.subject.artifact.hand.ArtifactHandlingException;
import org.jabogaf.api.subject.artifact.hand.ArtifactHandlingStrategy;
import org.jabogaf.api.subject.hand.Hand;
import org.jabogaf.common.artifact.shield.SimpleShield;
import org.jabogaf.common.artifact.weapon.OneHandedAxe;
import org.jabogaf.common.artifact.weapon.OneHandedSword;
import org.jabogaf.common.artifact.weapon.TwoHandedAxe;
import org.jabogaf.common.artifact.weapon.TwoHandedSword;
import org.jabogaf.core.test.ArquillianGameContextTest;
import org.jabogaf.core.test.BeforeInGameContext;

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
