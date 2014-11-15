package at.ahammer.boardgame.api.behavior.move;

/**
 * A move is not possible.
 */
public class MoveNotPossibleException extends Exception {

    public MoveNotPossibleException(String message) {
        super(message);
    }
}
