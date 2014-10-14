package at.ahammer.boardgame.action.move;

import at.ahammer.boardgame.action.ActionNotPossibleException;
import at.ahammer.boardgame.action.GameAction;
import at.ahammer.boardgame.action.GameActionPreferences;
import at.ahammer.boardgame.controller.PlayerController;
import at.ahammer.boardgame.object.field.Field;
import at.ahammer.boardgame.subject.GameSubject;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Created by andreas on 08.10.14.
 */
@ApplicationScoped
public class MoveAction extends GameAction {

    @Inject
    private PlayerController playerController;

    @Inject
    private Logger log;

    public void move(GameSubject gameSubject, Field target) throws Exception {
        log.info("running MoveAction with subject {} and position {}", gameSubject, target);
        super.perform(//
                GameActionPreferences.newInstance().newInstance().actionPrerequisite(() -> {
                    if (!playerController.isCurrentPlayer(gameSubject)) {
                        log.warn("{} is not the current player ({})", gameSubject, playerController.getCurrentPlayer());
                        throw new ActionNotPossibleException(gameSubject + " is not the current player (" + playerController.getCurrentPlayer() + ")");
                    }
                }).actionPerform(() -> { //
                    log.info("move {} to {}", gameSubject, target);
                    gameSubject.move(target); //
                }).afterActionEventCreation(() -> { //
                    log.info("fire AfterMoveActionEvent");
                    return new AfterMoveActionEvent(gameSubject, target); //
                }));
    }
}
