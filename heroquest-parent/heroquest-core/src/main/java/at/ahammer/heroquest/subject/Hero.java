package at.ahammer.heroquest.subject;

import at.ahammer.boardgame.object.field.Field;
import at.ahammer.boardgame.subject.GameSubject;
import at.ahammer.boardgame.behavior.look.LookBehavior;
import at.ahammer.boardgame.behavior.look.LookNotPossibleException;
import at.ahammer.boardgame.behavior.move.FieldsNotConnectedException;
import at.ahammer.boardgame.behavior.move.MoveBehavior;
import at.ahammer.boardgame.behavior.move.MoveNotPossibleException;
import at.ahammer.heroquest.behavior.look.LookStrategies;
import at.ahammer.heroquest.behavior.move.MoveStrategies;

/**
 * Created by andreas on 26.07.14.
 */
public abstract class Hero extends GameSubject {

    private Attribute strength;

    private Attribute intelligence;

    private Attribute vitality;

    public Hero(String id, Field position) {
        super(id, position);
    }

    private static class Attribute {

        private int def;
        private int max;
        private int current;

    }
}