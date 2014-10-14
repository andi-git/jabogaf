package at.ahammer.heroquest.action.discover;

import at.ahammer.boardgame.action.ActionNotPossibleException;
import at.ahammer.boardgame.action.GameAction;
import at.ahammer.boardgame.action.GameActionPreferences;
import at.ahammer.boardgame.action.move.AfterMoveActionEvent;
import at.ahammer.boardgame.board.BoardAccess;
import at.ahammer.boardgame.controller.PlayerController;
import at.ahammer.boardgame.object.field.Field;
import at.ahammer.heroquest.subject.Hero;
import org.slf4j.Logger;

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

    @Inject
    private Logger log;

    public void discover(Hero subject, Field position) throws ActionNotPossibleException {
        log.info("running DiscoverAction with subject {} and position {}", subject, position);
        super.perform(GameActionPreferences.newInstance().newInstance().actionPrerequisite(() -> {
            if (!playerController.isCurrentPlayer(subject)) {
                log.warn("{} is not the current player ({})", subject, playerController.getCurrentPlayer());
                throw new ActionNotPossibleException(subject + " is not the current player (" + playerController.getCurrentPlayer() + ")");
            }
        }).actionPerform(() -> {
            boardAccess.getFields().stream().forEach((field) -> {
                if (subject.canLook(field)) {
                    log.info("field {} is visible -> set all GameObjects to visible", field);
                    boardAccess.getGameObjects(position).stream().filter((gameObject) -> {
                        return !gameObject.isVisible();
                    }).forEach((gameObject) -> {
                        log.info("  {} is now visible", gameObject);
                        gameObject.setVisible(true);
                    });
                }
            });
        }));
    }

    private void afterMove(@Observes AfterMoveActionEvent afterMoveActionEvent) throws ActionNotPossibleException {
        log.info("observe a " + afterMoveActionEvent);
        if (afterMoveActionEvent.getGameSubject() instanceof Hero) {
            discover((Hero) afterMoveActionEvent.getGameSubject(), afterMoveActionEvent.getTarget());
        }
    }
}
