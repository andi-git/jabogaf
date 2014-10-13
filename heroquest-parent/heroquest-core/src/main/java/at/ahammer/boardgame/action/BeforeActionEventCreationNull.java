package at.ahammer.boardgame.action;

/**
 * Created by andreas on 13.10.14.
 */
public class BeforeActionEventCreationNull implements ActionEventCreation<BeforeActionEvent> {

    @Override
    public BeforeActionEventNull create() {
        return new BeforeActionEventNull();
    }
}
