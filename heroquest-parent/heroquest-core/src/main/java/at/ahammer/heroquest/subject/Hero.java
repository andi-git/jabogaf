package at.ahammer.heroquest.subject;

import at.ahammer.boardgame.subject.GameSubject;

/**
 * Created by andreas on 26.07.14.
 */
public abstract class Hero extends GameSubject {

    private Attribute strength;

    private Attribute intelligence;

    private Attribute vitality;

    public Hero(String id) {
        super(id);
    }

    private static class Attribute {

        private int def;
        private int max;
        private int current;

    }
}