package at.ahammer.boardgame.action;

import at.ahammer.boardgame.action.ActionNotPossibleException;

/**
 * Created by andreas on 08.10.14.
 */
@FunctionalInterface
public interface ActionPrerequisite {

    void checkPrerequisite() throws ActionNotPossibleException;
}
