package at.ahammer.boardgame.core.subject.artifact;

import at.ahammer.boardgame.api.artifact.Artifact;
import at.ahammer.boardgame.api.subject.SetterOfArtifactsForHands;
import at.ahammer.boardgame.api.subject.artifact.ArtifactHolder;
import at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingException;
import at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingStrategy;
import at.ahammer.boardgame.api.subject.hand.Hand;
import at.ahammer.boardgame.core.artifact.ArtifactNull;
import at.ahammer.boardgame.core.subject.hand.HandBasic;

import javax.enterprise.inject.Typed;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

@Typed
public class ArtifactHolderBasic implements ArtifactHolder {

    private final List<ArtifactHandlingStrategy> artifactHandlingStrategies = new ArrayList<>();

    private final Hand mainHand;

    private final Hand offHand;

    public ArtifactHolderBasic() {
        mainHand = new HandBasic(Hand.Type.MAIN);
        offHand = new HandBasic(Hand.Type.OFF);
    }

    @Override
    public Artifact getMainHandArtifact() {
        return mainHand.getArtifact();
    }

    @Override
    public Artifact getOffHandArtifact() {
        return offHand.getArtifact();
    }

    @Override
    public List<ArtifactHandlingStrategy> getArtifactHandlingStrategies() {
        return Collections.unmodifiableList(artifactHandlingStrategies);
    }

    @Override
    public boolean canHandle(ArtifactHandlingStrategy artifactHandlingStrategy) {
        return artifactHandlingStrategies.contains(artifactHandlingStrategy);
    }

    @Override
    public boolean canHandle(Artifact artifact) {
        return canHandle(artifact, Hand.Type.MAIN);
    }

    @Override
    public boolean canHandle(Artifact artifact, Hand.Type handType) {
        return strategiesStream().anyMatch(ahs -> ahs.canHandle(artifact, handType));
    }

    @Override
    public void addArtifact(Artifact artifact) throws ArtifactHandlingException {
        addArtifact(artifact, Hand.Type.MAIN);
    }

    @Override
    public void addArtifact(Artifact artifact, Hand.Type handType) throws ArtifactHandlingException {
        try {
            strategiesStream().
                    filter(ahs -> ahs.canHandle(artifact, handType)).
                    findFirst().get().
                    addArtifactToHand(artifact, handType, this, createSetterOfArtifactsForHands());
        } catch (NoSuchElementException e) {
            throw new ArtifactHandlingException("no ArtifactHandlingStrategy available to handle artifact " + artifact, e, artifact, handType, this);
        }
    }

    @Override
    public void addArtifactHandlingStrategy(ArtifactHandlingStrategy artifactHandlingStrategy) {
        artifactHandlingStrategies.add(artifactHandlingStrategy);
    }

    @Override
    public void removeArtifactHandlingStrategy(ArtifactHandlingStrategy artifactHandlingStrategy) {
        artifactHandlingStrategies.remove(artifactHandlingStrategy);
    }

    @Override
    public void clearArtifactHandlingStrategies() {
        artifactHandlingStrategies.clear();
    }

    @Override
    public void resetHands() {
        mainHand.setArtifact(new ArtifactNull());
        offHand.setArtifact(new ArtifactNull());
    }

    protected SetterOfArtifactsForHands createSetterOfArtifactsForHands() {
        return (artifactForMainHand, artifactForOffHand) -> {
            mainHand.setArtifact(artifactForMainHand);
            offHand.setArtifact(artifactForOffHand);
        };
    }

    private Stream<ArtifactHandlingStrategy> strategiesStream() {
        return artifactHandlingStrategies.stream();
    }
}
