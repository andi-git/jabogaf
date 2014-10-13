package at.ahammer.boardgame.action;

/**
 * Created by andreas on 08.10.14.
 */
@FunctionalInterface
public interface ActionEventCreation<T extends ActionEvent> {

    T create();
}
