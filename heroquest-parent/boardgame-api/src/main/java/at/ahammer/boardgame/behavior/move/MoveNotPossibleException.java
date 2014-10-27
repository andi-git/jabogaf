package at.ahammer.boardgame.behavior.move;

/**
 * A move is not possible.
 */
public class MoveNotPossibleException extends Exception {

    public MoveNotPossibleException(String message) {
        super(message);
    }
}
