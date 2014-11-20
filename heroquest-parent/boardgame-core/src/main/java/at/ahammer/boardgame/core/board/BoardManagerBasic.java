package at.ahammer.boardgame.core.board;

import at.ahammer.boardgame.api.board.Board;
import at.ahammer.boardgame.api.board.BoardManager;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.field.FieldConnectionObject;
import at.ahammer.boardgame.core.cdi.GameContext;
import at.ahammer.boardgame.api.cdi.GameScoped;

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
    public Set<FieldConnectionObject> getAllFieldConectionObjects(Field leftHand, Field rightHand) {
        return getBoard().getLayout().getAllFieldConnectionObjects(leftHand, rightHand);
    }
}
