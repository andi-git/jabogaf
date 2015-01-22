package at.ahammer.boardgame.core.subject.artifact.hand;

import at.ahammer.boardgame.api.artifact.HandCount;
import at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingException;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AddArtifactToHandStrategyGeneralBasic implements AddArtifactToHandStrategyGeneral {

    @Override
    public void addArtifactToHand(AddArtifactToHandStrategyContext addArtifactToHandStrategyContext) throws ArtifactHandlingException {
        // check if the artifact can be handled
        if (addArtifactToHandStrategyContext.canHandleArtifact()) {
            // check if there is a two-handed-weapon...
            if (addArtifactToHandStrategyContext.getMainHandHandCount() == HandCount.TWO) {
                // ... if yes, reset both hands
                addArtifactToHandStrategyContext.resetHands();
            }
            // add the artifact
            addArtifactToHandStrategyContext.addArtifactToHand();
        } else {
            // if it can't be handled, throw an exception
            throw new ArtifactHandlingException("unable to handle artifact " + addArtifactToHandStrategyContext.getArtifact(),
                    addArtifactToHandStrategyContext.getArtifact(),
                    addArtifactToHandStrategyContext.getHandType(),
                    addArtifactToHandStrategyContext.getArtifactHolder());
        }
    }
}
