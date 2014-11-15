package at.ahammer.boardgame.api.behavior.look;

/**
 * This exception will bw thrown if a look is not possible.
 */
public class LookNotPossibleException extends Exception {

    public LookNotPossibleException() {
    }

    public LookNotPossibleException(String message) {
        super(message);
    }

    public LookNotPossibleException(String message, Throwable cause) {
        super(message, cause);
    }

    public LookNotPossibleException(Throwable cause) {
        super(cause);
    }

    public LookNotPossibleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
