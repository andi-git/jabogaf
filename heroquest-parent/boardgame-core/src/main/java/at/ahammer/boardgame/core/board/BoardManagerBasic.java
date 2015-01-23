package at.ahammer.boardgame.core.board;

import at.ahammer.boardgame.api.board.Board;
import at.ahammer.boardgame.api.board.BoardManager;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.field.FieldConnectionObject;
import at.ahammer.boardgame.core.cdi.GameContext;
import at.ahammer.boardgame.api.cdi.GameScoped;

import java.util.HashSet;
import java.util.Set;

@GameScoped
public class BoardManagerBasic implements BoardManager {

    @Override
    public Board getBoard() {
        return GameContext.current().getGameContextBean(Board.class);
    }

    @Override
    public Set<Field> getFields() {
        return getBoard().getLayout().getFields();
    }

    @Override
    public Set<FieldConnectionObject> getAllFieldConnectionObjects(Field leftHand, Field rightHand) {
        Set<FieldConnectionObject> result = new HashSet<>();
        if (getBoard() != null) {
            if (getBoard().getLayout() != null) {
                result.addAll(getBoard().getLayout().getAllFieldConnectionObjects(leftHand, rightHand));
            }
        }
        return result;
    }
}
