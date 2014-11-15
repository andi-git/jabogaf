package at.ahammer.boardgame.common.action.move;

import at.ahammer.boardgame.api.action.ActionNotPossibleException;
import at.ahammer.boardgame.api.action.GameAction;
import at.ahammer.boardgame.api.action.GameActionLifecycle;
import at.ahammer.boardgame.api.action.GameActionPreferences;
import at.ahammer.boardgame.api.controller.PlayerController;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.subject.GameSubject;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * A standard move-action.
 *
 * @see AfterMoveActionEvent
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
     * Perform a move on the assigned {@link at.ahammer.boardgame.api.subject.GameSubject} to the assigned {@link
     * at.ahammer.boardgame.api.board.field.Field} via {@link at.ahammer.boardgame.api.subject.GameSubject#move(at.ahammer.boardgame.api.board.field.Field)}
     * <p/>
     * Prerequisites: {@link at.ahammer.boardgame.api.subject.GameSubject} is the current player
     * <p/>
     * Action: Move {@link at.ahammer.boardgame.api.subject.GameSubject} to a {@link at.ahammer.boardgame.api.board.field.Field}
     * <p/>
     * Events: {@link BeforeMoveActionEvent}, {@link
     * AfterMoveActionEvent}
     *
     * @param moveActionParameter the {@link MoveActionParameter} for the {@link
     *                            MoveAction}
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
