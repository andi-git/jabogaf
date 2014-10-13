package at.ahammer.boardgame.action;

/**
 * Created by andreas on 13.10.14.
 */
public class AfterActionEventCreationNull implements ActionEventCreation<AfterActionEvent> {

    @Override
    public AfterActionEventNull create() {
        return new AfterActionEventNull();
    }
}
