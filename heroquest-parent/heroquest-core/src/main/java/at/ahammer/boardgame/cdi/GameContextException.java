package at.ahammer.boardgame.cdi;

/**
 * Created by ahammer on 06.08.2014.
 */
public class GameContextException extends RuntimeException {

    public GameContextException() {
        super();
    }

    public GameContextException(String message) {
        super(message);
    }

    public GameContextException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameContextException(Throwable cause) {
        super(cause);
    }

    protected GameContextException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
