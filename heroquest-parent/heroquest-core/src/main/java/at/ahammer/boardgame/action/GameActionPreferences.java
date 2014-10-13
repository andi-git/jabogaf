package at.ahammer.boardgame.action;

/**
 * Created by andreas on 13.10.14.
 */
public class GameActionPreferences {

    private ActionPrerequisite actionPrerequisite = () -> {};

    private ActionPerform actionPerform = () -> {};

    private ActionEventCreation<BeforeActionEvent> beforeActionEventCreation = new BeforeActionEventCreationNull();

    private ActionEventCreation<AfterActionEvent> afterActionEventCreation = new AfterActionEventCreationNull();

    private GameActionPreferences() {
        // private constructor
    }

    public static GameActionPreferences newInstance() {
        return new GameActionPreferences();
    }

    public GameActionPreferences actionPrerequisite(ActionPrerequisite actionPrerequisite) {
        this.actionPrerequisite = actionPrerequisite;
        return this;
    }

    public GameActionPreferences actionPerform(ActionPerform actionPerform) {
        this.actionPerform = actionPerform;
        return this;
    }

    public GameActionPreferences beforeActionEventCreation(ActionEventCreation<BeforeActionEvent> beforeActionEventActionEventCreation) {
        this.beforeActionEventCreation = beforeActionEventActionEventCreation;
        return this;
    }

    public GameActionPreferences afterActionEventCreation(ActionEventCreation<AfterActionEvent> afterActionEventActionEventCreation) {
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
