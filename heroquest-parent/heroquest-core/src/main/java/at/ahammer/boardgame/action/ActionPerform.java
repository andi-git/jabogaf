package at.ahammer.boardgame.action;

import at.ahammer.boardgame.behavior.move.FieldsNotConnectedException;
import at.ahammer.boardgame.behavior.move.MoveNotPossibleException;

/**
 * Created by andreas on 08.10.14.
 */
@FunctionalInterface
public interface ActionPerform {

    void perform() throws Exception;
}
