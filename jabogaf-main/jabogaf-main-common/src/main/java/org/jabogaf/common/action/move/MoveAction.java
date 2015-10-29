package org.jabogaf.common.action.move;

import org.jabogaf.api.action.ActionNotPossibleException;
import org.jabogaf.api.action.GameAction;
import org.jabogaf.api.action.GameActionLifecycle;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.controller.PlayerController;
import org.jabogaf.api.subject.GameSubject;
import org.jabogaf.common.event.move.AfterMoveActionEvent;
import org.jabogaf.common.event.move.BeforeMoveActionEvent;
import org.jabogaf.common.event.move.MoveActionParameter;
import org.jabogaf.core.action.GameActionPreferencesBasic;
import org.jabogaf.util.log.LogWrapper;
import org.jabogaf.util.log.SLF4J;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Arrays;

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
    @SLF4J
    private LogWrapper log;

    /**
     * Perform a move on the assigned {@link GameSubject} to the assigned {@link
     * Field} via {@link GameSubject#move(Field)}
     * <p>
     * Prerequisites: {@link GameSubject} is the current player
     * <p>
     * Action: Move {@link GameSubject} to a {@link Field}
     * <p>
     * Events: {@link BeforeMoveActionEvent}, {@link
     * AfterMoveActionEvent}
     *
     * @param moveActionParameter the {@link MoveActionParameter} for the {@link
     *                            MoveAction}
     * @throws Exception if the move was not successful
     */
    @Override
    public void perform(MoveActionParameter moveActionParameter) throws Exception {
        log.debug("running MoveAction: {}", () -> moveActionParameter);
        GameSubject gameSubject = moveActionParameter.getGameSubject();
        Field target = moveActionParameter.getTarget();
        gameActionLifecycle.perform(//
                GameActionPreferencesBasic.newInstance().addPrerequisite(() -> {
                    if (!playerController.isCurrentPlayer(gameSubject)) {
                        log.warn("{} is not the current player ({})", gameSubject, playerController.getCurrentPlayer());
                        throw new ActionNotPossibleException(gameSubject + " is not the current player (" + playerController.getCurrentPlayer() + ")");
                    }
                }).addPerform(() -> {
                    log.debug("move {}  from {} to {}", Arrays.asList(() -> moveActionParameter.getMoveable(), () -> moveActionParameter.getMoveable().getPosition(), () -> target));
                    gameSubject.move(target);
                }).addBeforeActionEventCreation(() -> {
                    log.debug("fire {}", () -> BeforeMoveActionEvent.class);
                    return new BeforeMoveActionEvent(moveActionParameter);
                }).addAfterActionEventCreation(() -> {
                    log.debug("fire {}", () -> AfterMoveActionEvent.class);
                    return new AfterMoveActionEvent(moveActionParameter);
                }));
    }
}
