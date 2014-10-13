package at.ahammer.heroquest.action.discover;

import at.ahammer.boardgame.action.ActionNotPossibleException;
import at.ahammer.boardgame.action.GameAction;
import at.ahammer.boardgame.action.GameActionPreferences;
import at.ahammer.boardgame.action.move.AfterMoveActionEvent;
import at.ahammer.boardgame.board.BoardAccess;
import at.ahammer.boardgame.controller.PlayerController;
import at.ahammer.boardgame.object.field.Field;
import at.ahammer.boardgame.subject.GameSubject;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 * Created by andreas on 13.10.14.
 */
@ApplicationScoped
public class DiscoverAction extends GameAction {

    @Inject
    private PlayerController playerController;

    @Inject
    private BoardAccess boardAccess;

    public void discover(GameSubject subject, Field field) throws ActionNotPossibleException {
        super.perform(GameActionPreferences.newInstance().newInstance().actionPrerequisite(() -> {
            if (!playerController.isCurrentPlayer(subject)) {
                throw new ActionNotPossibleException(subject + " is not the current player (" + playerController.getCurrentPlayer() + ")");
            }
        }).actionPerform(() -> {
            boardAccess.getGameObjects(field).stream().forEach((f) -> f.setVisible(true));
        }));
    }

    private void afterMove(@Observes AfterMoveActionEvent afterMoveActionEvent) throws ActionNotPossibleException {
        discover(afterMoveActionEvent.getGameSubject(), afterMoveActionEvent.getTarget());
    }
}
