package at.ahammer.boardgame.core.subject.artifact.hand;

import at.ahammer.boardgame.api.subject.artifact.hand.AddArtifactToHandStrategyContext;
import at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandManager;
import at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingException;
import at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingStrategy;

import javax.enterprise.context.ApplicationScoped;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

@ApplicationScoped
public class ArtifactHandManagerBasic implements ArtifactHandManager {

    @Override
    public void addArtifact(AddArtifactToHandStrategyContext addArtifactToHandStrategyContext) throws ArtifactHandlingException {
        Predicate<ArtifactHandlingStrategy> canHandle = ahs -> ahs.canHandle(addArtifactToHandStrategyContext.getArtifact(), addArtifactToHandStrategyContext.getHandType());
        try {
            addArtifactToHandStrategyContext.getArtifactHandlingStrategies().stream().
                    filter(canHandle).
                    findFirst().get().
                    addArtifactToHand(addArtifactToHandStrategyContext);
        } catch (NoSuchElementException e) {
            throw new ArtifactHandlingException("no ArtifactHandlingStrategy avaible to handle artifact " + addArtifactToHandStrategyContext.getArtifact(), e, addArtifactToHandStrategyContext);
        }
    }

    @Override
    public boolean canHandle(AddArtifactToHandStrategyContext addArtifactToHandStrategyContext) {
        return addArtifactToHandStrategyContext.getArtifactHandlingStrategies().stream().
                anyMatch(ahs -> ahs.canHandle(addArtifactToHandStrategyContext.getArtifact(), addArtifactToHandStrategyContext.getHandType()));
    }
}
