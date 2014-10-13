package at.ahammer.boardgame.action;

/**
 * Created by andreas on 08.10.14.
 */
public class ActionNotPossibleException extends Exception {

    public ActionNotPossibleException(String message) {
        super(message);
    }

    public ActionNotPossibleException(Exception e) {
        super(e);
    }
}
