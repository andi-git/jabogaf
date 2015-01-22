package at.ahammer.boardgame.core.subject.artifact.hand;

import at.ahammer.boardgame.api.artifact.Artifact;
import at.ahammer.boardgame.api.subject.SetterOfArtifactsForHands;
import at.ahammer.boardgame.api.subject.artifact.ArtifactHolder;
import at.ahammer.boardgame.api.subject.artifact.hand.*;
import at.ahammer.boardgame.api.subject.hand.Hand;

import javax.inject.Inject;

/**
 * The strategy that allows a {@link at.ahammer.boardgame.api.subject.GameSubject} to handle a concrete {@link
 * at.ahammer.boardgame.api.artifact.Artifact}.
 */
@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public abstract class ArtifactHandlingStrategyBasic implements ArtifactHandlingStrategy {

    @Inject
    private AddArtifactToHandStrategyGeneral addArtifactToHandStrategyGeneral;

    @Override
    public boolean canHandle(Artifact artifact, Hand.Type handType) {
        return getCanHandleArtifactStrategy().check(artifact, handType);
    }

    @Override
    public void addArtifactToHand(Artifact artifact, Hand.Type handType, ArtifactHolder artifactHolder, SetterOfArtifactsForHands setterOfArtifactsForHands) throws ArtifactHandlingException {
        addArtifactToHandStrategyGeneral.addArtifactToHand(createAddArtifactToHandStrategyContext(artifact, handType, artifactHolder, setterOfArtifactsForHands));
    }

    private AddArtifactToHandStrategyContext createAddArtifactToHandStrategyContext(Artifact artifact, Hand.Type handType, ArtifactHolder artifactHolder, SetterOfArtifactsForHands setterOfArtifactsForHands) {
        return new AddArtifactToHandStrategyContext().
                setArtifact(artifact).
                setHandType(handType).
                setArtifactHolder(artifactHolder).
                setSetterOfArtifactsForHands(setterOfArtifactsForHands).
                setAddArtifactToHandStrategyConcrete(getAddArtifactToHandStrategyConcrete()).
                setCanHandleArtifactStrategy(getCanHandleArtifactStrategy());
    }


    protected abstract AddArtifactToHandStrategyConcrete getAddArtifactToHandStrategyConcrete();

    protected abstract CanHandleArtifactStrategy getCanHandleArtifactStrategy();
}
