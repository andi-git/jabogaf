package at.ahammer.boardgame.core.action;

import at.ahammer.boardgame.api.action.ActionPerform;
import at.ahammer.boardgame.api.action.ActionPrerequisite;
import at.ahammer.boardgame.api.action.GameActionParameter;
import at.ahammer.boardgame.api.action.GameActionPreferences;
import at.ahammer.boardgame.api.action.event.ActionEventCreation;
import at.ahammer.boardgame.api.action.event.AfterActionEvent;
import at.ahammer.boardgame.api.action.event.BeforeActionEvent;

/**
 * All data or functions to perform a {@link at.ahammer.boardgame.api.action.GameAction}. An instance of this class
 * can be created via {@link #newInstance()} and add data or functions via the builder-pattern.
 */
public class GameActionPreferencesBasic implements GameActionPreferences {

    private ActionPrerequisite actionPrerequisite = () -> {
    };

    private ActionPerform actionPerform = () -> {
    };

    private ActionEventCreation<BeforeActionEvent> beforeActionEventCreation = () -> {
        return new BeforeActionEventDefault();
    };

    private ActionEventCreation<AfterActionEvent> afterActionEventCreation = () -> {
        return new AfterActionEventDefault();
    };

    private GameActionPreferencesBasic() {
        // private constructor
    }

    /**
     * Create a new instance of {@link at.ahammer.boardgame.core.action.GameActionPreferencesBasic}
     *
     * @return a new instanceof {@link at.ahammer.boardgame.core.action.GameActionPreferencesBasic}
     */
    public static GameActionPreferencesBasic newInstance() {
        return new GameActionPreferencesBasic();
    }

    @Override
    public GameActionPreferencesBasic addPrerequisite(ActionPrerequisite actionPrerequisite) {
        this.actionPrerequisite = actionPrerequisite;
        return this;
    }

    @Override
    public GameActionPreferencesBasic addPerform(ActionPerform actionPerform) {
        this.actionPerform = actionPerform;
        return this;
    }

    @Override
    public GameActionPreferencesBasic addBeforeActionEventCreation(ActionEventCreation<BeforeActionEvent> beforeActionEventCreation) {
        this.beforeActionEventCreation = beforeActionEventCreation;
        return this;
    }

    @Override
    public GameActionPreferencesBasic addAfterActionEventCreation(ActionEventCreation<AfterActionEvent> afterActionEventCreation) {
        this.afterActionEventCreation = afterActionEventCreation;
        return this;
    }

    @Override
    public ActionPrerequisite getActionPrerequisite() {
        return actionPrerequisite;
    }

    @Override
    public ActionPerform getActionPerform() {
        return actionPerform;
    }

    @Override
    public ActionEventCreation<BeforeActionEvent> getBeforeActionEventCreation() {
        return beforeActionEventCreation;
    }

    @Override
    public ActionEventCreation<AfterActionEvent> getAfterActionEventCreation() {
        return afterActionEventCreation;
    }

    private class AfterActionEventDefault extends AfterActionEventBasic<GameActionParameter> {

        public AfterActionEventDefault() {
            super(new GameActionParameter() {
            });
        }
    }

    private class BeforeActionEventDefault extends BeforeActionEventBasic<GameActionParameter> {

        public BeforeActionEventDefault() {
            super(new GameActionParameter() {
            });
        }
    }
}