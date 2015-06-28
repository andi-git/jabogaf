package org.jabogaf.common.action.move;

import org.jabogaf.api.action.ActionNotPossibleException;
import org.jabogaf.api.action.GameAction;
import org.jabogaf.api.action.GameActionLifecycle;
import org.jabogaf.api.controller.PlayerController;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.subject.GameSubject;
import org.jabogaf.core.action.GameActionPreferencesBasic;
import org.jabogaf.util.log.SLF4J;
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
    @SLF4J
    private Logger log;

    /**
     * Perform a move on the assigned {@link org.jabogaf.api.subject.GameSubject} to the assigned {@link
     * org.jabogaf.api.board.field.Field} via {@link org.jabogaf.api.subject.GameSubject#move(org.jabogaf.api.board.field.Field)}
     * <p/>
     * Prerequisites: {@link org.jabogaf.api.subject.GameSubject} is the current player
     * <p/>
     * Action: Move {@link org.jabogaf.api.subject.GameSubject} to a {@link org.jabogaf.api.board.field.Field}
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
                GameActionPreferencesBasic.newInstance().addPrerequisite(() -> {
                    if (!playerController.isCurrentPlayer(gameSubject)) {
                        log.warn("{} is not the current player ({})", gameSubject, playerController.getCurrentPlayer());
                        throw new ActionNotPossibleException(gameSubject + " is not the current player (" + playerController.getCurrentPlayer() + ")");
                    }
                }).addPerform(() -> {
                    log.info("move " + moveActionParameter.getMoveable() + " from "  + moveActionParameter.getMoveable().getPosition() + " to " + target);
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
