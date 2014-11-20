package at.ahammer.boardgame.api.action;

import at.ahammer.boardgame.api.action.event.ActionEventCreation;
import at.ahammer.boardgame.api.action.event.AfterActionEvent;
import at.ahammer.boardgame.api.action.event.BeforeActionEvent;

/**
 * All data or functions to perform a {@link GameAction}. An instance of this class
 * can be created via {@link #newInstance()} and add data or functions via the builder-pattern.
 */
@SuppressWarnings("CodeBlock2Expr")
public class GameActionPreferences {

    private ActionPrerequisite actionPrerequisite = () -> {
    };

    private ActionPerform actionPerform = () -> {
    };

    private ActionEventCreation<BeforeActionEvent> beforeActionEventCreation = () -> {
        return new BeforeActionEvent(new GameActionParameter() {
        });
    };

    private ActionEventCreation<AfterActionEvent> afterActionEventCreation = () -> {
        return new AfterActionEvent(new GameActionParameter() {
        });
    };

    private GameActionPreferences() {
        // private constructor
    }

    /**
     * Create a new instance of {@link GameActionPreferences}
     *
     * @return a new instanceof {@link GameActionPreferences}
     */
    public static GameActionPreferences newInstance() {
        return new GameActionPreferences();
    }

    public GameActionPreferences addPrerequisite(ActionPrerequisite actionPrerequisite) {
        this.actionPrerequisite = actionPrerequisite;
        return this;
    }

    public GameActionPreferences addPerform(ActionPerform actionPerform) {
        this.actionPerform = actionPerform;
        return this;
    }

    public GameActionPreferences addBeforeActionEventCreation(ActionEventCreation<BeforeActionEvent> beforeActionEventActionEventCreation) {
        this.beforeActionEventCreation = beforeActionEventActionEventCreation;
        return this;
    }

    public GameActionPreferences addAfterActionEventCreation(ActionEventCreation<AfterActionEvent> afterActionEventActionEventCreation) {
        this.afterActionEventCreation = afterActionEventActionEventCreation;
        return this;
    }

    public ActionPrerequisite getActionPrerequisite() {
        return actionPrerequisite;
    }

    public ActionPerform getActionPerform() {
        return actionPerform;
    }

    public ActionEventCreation<BeforeActionEvent> getBeforeActionEventCreation() {
        return beforeActionEventCreation;
    }

    public ActionEventCreation<AfterActionEvent> getAfterActionEventCreation() {
        return afterActionEventCreation;
    }
}