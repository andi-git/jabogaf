package at.ahammer.boardgame.action.move;

import at.ahammer.boardgame.action.ActionNotPossibleException;
import at.ahammer.boardgame.action.GameAction;
import at.ahammer.boardgame.action.GameActionLifecycle;
import at.ahammer.boardgame.action.GameActionPreferences;
import at.ahammer.boardgame.controller.PlayerController;
import at.ahammer.boardgame.object.field.Field;
import at.ahammer.boardgame.subject.GameSubject;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * A standard move-action.
 *
 * @see at.ahammer.boardgame.action.move.AfterMoveActionEvent
 */
@ApplicationScoped
public class MoveAction implements GameAction<MoveActionParameter> {

    @Inject
    private GameActionLifecycle gameActionLifecycle;

    @Inject
    private PlayerController playerController;

    @Inject
    private Logger log;

    /**
     * Perform a move on the assigned {@link at.ahammer.boardgame.subject.GameSubject} to the assigned {@link
     * at.ahammer.boardgame.object.field.Field} via {@link at.ahammer.boardgame.subject.GameSubject#move(at.ahammer.boardgame.object.field.Field)}
     * <p/>
     * Prerequisites: {@link at.ahammer.boardgame.subject.GameSubject} is the current player
     * <p/>
     * Action: Move {@link at.ahammer.boardgame.subject.GameSubject} to a {@link at.ahammer.boardgame.object.field.Field}
     * <p/>
     * Events: {@link at.ahammer.boardgame.action.move.BeforeMoveActionEvent}, {@link
     * at.ahammer.boardgame.action.move.AfterMoveActionEvent}
     *
     * @param moveActionParameter the {@link at.ahammer.boardgame.action.move.MoveActionParameter} for the {@link
     *                            at.ahammer.boardgame.action.move.MoveAction}
     * @throws Exception if the move was not successful
     */
    @Override
    public void perform(MoveActionParameter moveActionParameter) throws Exception {
        log.info("running MoveAction: {}", moveActionParameter);
        GameSubject gameSubject = moveActionParameter.getGameSubject();
        Field target = moveActionParameter.getTarget();
        gameActionLifecycle.perform(//
                GameActionPreferences.newInstance().newInstance().addPrerequisite(() -> {
                    if (!playerController.isCurrentPlayer(gameSubject)) {
                        log.warn("{} is not the current player ({})", gameSubject, playerController.getCurrentPlayer());
                        throw new ActionNotPossibleException(gameSubject + " is not the current player (" + playerController.getCurrentPlayer() + ")");
                    }
                }).addPerform(() -> {
                    log.info("move {} to {}", gameSubject, target);
                    gameSubject.move(target);
                }).addBeforeActionEventCreation(() -> {
                    log.info("fire {}", BeforeMoveActionEvent.class);
                    return new BeforeMoveActionEvent(moveActionParameter);
                }).addAfterActionEventCreation(() -> {
                    log.info("fire {}", AfterMoveActionEvent.class);
                    return new AfterMoveActionEvent(moveActionParameter);
                }));
    }
}
