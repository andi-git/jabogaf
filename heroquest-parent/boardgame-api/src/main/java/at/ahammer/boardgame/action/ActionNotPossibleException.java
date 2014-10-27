package at.ahammer.boardgame.action;

/**
 * This exception is used within a {@link at.ahammer.boardgame.action.GameAction} if the action is not possible.
 */
public class ActionNotPossibleException extends Exception {

    public ActionNotPossibleException() {
    }

    public ActionNotPossibleException(String message) {
        super(message);
    }

    public ActionNotPossibleException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActionNotPossibleException(Throwable cause) {
        super(cause);
    }

    public ActionNotPossibleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
