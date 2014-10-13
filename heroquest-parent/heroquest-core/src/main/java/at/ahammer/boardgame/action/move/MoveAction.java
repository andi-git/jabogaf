package at.ahammer.boardgame.action.move;

import at.ahammer.boardgame.action.ActionNotPossibleException;
import at.ahammer.boardgame.action.GameAction;
import at.ahammer.boardgame.action.GameActionPreferences;
import at.ahammer.boardgame.controller.PlayerController;
import at.ahammer.boardgame.object.field.Field;
import at.ahammer.boardgame.subject.GameSubject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Created by andreas on 08.10.14.
 */
@ApplicationScoped
public class MoveAction extends GameAction {

    @Inject
    private PlayerController playerController;

    public void move(GameSubject gameSubject, Field target) throws Exception {
        super.perform(//
                GameActionPreferences.newInstance().newInstance().actionPrerequisite(() -> {
                    if (!playerController.isCurrentPlayer(gameSubject)) {
                        throw new ActionNotPossibleException(gameSubject + " is not the current player (" + playerController.getCurrentPlayer() + ")");
                    }
                }).actionPerform(() -> { //
                    gameSubject.move(target); //
                }).afterActionEventCreation(() -> { //
                    return new AfterMoveActionEvent(gameSubject, target); //
                }));
    }
}
