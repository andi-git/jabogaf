package at.ahammer.boardgame.api.subject.artifact.hand;

import at.ahammer.boardgame.api.artifact.Artifact;
import at.ahammer.boardgame.api.artifact.HandCount;
import at.ahammer.boardgame.api.subject.GameSubject;
import at.ahammer.boardgame.api.subject.hand.Hand;

import javax.inject.Inject;

/**
 * The strategy that allows a {@link at.ahammer.boardgame.api.subject.GameSubject} to handle a concrete {@link at.ahammer.boardgame.api.artifact.Artifact}.
 */
@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public abstract class ArtifactHandlingStrategy {

    @Inject
    private AddArtifactToHandStrategyGeneral addArtifactToHandStrategyGeneral;

    /**
     * Check if the concrete {@link at.ahammer.boardgame.api.artifact.Artifact} can be handled by the current {@link at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingStrategy}.
     *
     * @param artifact the {@link at.ahammer.boardgame.api.artifact.Artifact} to check
     * @param handType the {@link at.ahammer.boardgame.api.subject.hand.Hand.Type} to add the {@link at.ahammer.boardgame.api.artifact.Artifact} to
     * @return {@code true} if the the concrete {@link at.ahammer.boardgame.api.artifact.Artifact} can be handled by the current {@link at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingStrategy}
     */
    public boolean canHandle(Artifact artifact, Hand.Type handType) {
        return getCanHandleArtifactStrategy().check(artifact, handType);
    }

    /**
     * This method encapsulates the strategy to add a concrete {@link at.ahammer.boardgame.api.artifact.Artifact} to a {@link at.ahammer.boardgame.api.subject.GameSubject}.
     *
     * @param addArtifactToHandStrategyContext the context of the functionality to add an {@link at.ahammer.boardgame.api.artifact.Artifact} to a {@link at.ahammer.boardgame.api.subject.GameSubject}
     * @throws at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingException if the handling is not possible -> check via {@link #canHandle(at.ahammer.boardgame.api.artifact.Artifact, at.ahammer.boardgame.api.subject.hand.Hand.Type)} first!
     */
    public void addArtifactToHand(AddArtifactToHandStrategyContext addArtifactToHandStrategyContext) throws ArtifactHandlingException {
        addArtifactToHandStrategyGeneral.addArtifactToHand(enrichContext(addArtifactToHandStrategyContext));
    }

    private AddArtifactToHandStrategyContext enrichContext(AddArtifactToHandStrategyContext addArtifactToHandStrategyContext) {
        return addArtifactToHandStrategyContext.setAddArtifactToHandStrategyConcrete(getAddArtifactToHandStrategyConcrete()).
                setCanHandleArtifactStrategy(getCanHandleArtifactStrategy());
    }

    protected abstract AddArtifactToHandStrategyConcrete getAddArtifactToHandStrategyConcrete();

    protected abstract CanHandleArtifactStrategy getCanHandleArtifactStrategy();
}
