package at.ahammer.heroquest.subject;

import at.ahammer.boardgame.object.field.Field;
import at.ahammer.boardgame.subject.GameSubject;
import at.ahammer.boardgame.subject.look.Look;
import at.ahammer.boardgame.subject.look.LookNotPossibleException;
import at.ahammer.boardgame.subject.move.FieldsNotConnectedException;
import at.ahammer.boardgame.subject.move.Move;
import at.ahammer.boardgame.subject.move.MoveNotPossibleException;
import at.ahammer.heroquest.subject.look.LookStrategies;
import at.ahammer.heroquest.subject.move.MoveHero;
import at.ahammer.heroquest.subject.move.MoveStrategies;

/**
 * Created by andreas on 26.07.14.
 */
public abstract class Hero extends GameSubject {

    private Attribute strength;

    private Attribute intelligence;

    private Attribute vitality;

    private Move move;

    private Look look;

    private Field position;

    public Hero(String id, Field position) {
        super(id);
        this.position = position;
        move = fromGameContext(MoveStrategies.class).getMoveHero();
        look = fromGameContext(LookStrategies.class).getLookHero();
    }

    public void move(Field to) throws FieldsNotConnectedException, MoveNotPossibleException {
        if (position != null) {
            if (!position.isConnected(to)) {
                throw new FieldsNotConnectedException("fields " + position + " and " + to + " are not connected");
            }
            if (move.canMove(position, to)) {
                position = to;
            } else {
                throw new MoveNotPossibleException("unable to move from " + position + " to " + to);
            }
        } else {
            throw new IllegalStateException("position is null");
        }
    }

    public void look(Field to) throws LookNotPossibleException {
        if (!look.canLook(position, to)) {
            throw new LookNotPossibleException("unable to look from " + position + " to " + to);
        }
    }

    public Field getPosition() {
        return position;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public void setLook(Look look) {
        this.look = look;
    }

    private static class Attribute {

        private int def;
        private int max;
        private int current;

    }
}