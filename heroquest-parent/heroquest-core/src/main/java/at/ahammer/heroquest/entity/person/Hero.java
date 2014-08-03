package at.ahammer.heroquest.entity.person;

/**
 * Created by andreas on 26.07.14.
 */
public abstract class Hero extends GameSubject {

    private Attribute strength;

    private Attribute intelligence;

    private Attribute vitality;

    private static class Attribute {

        private int def;
        private int max;
        private int current;

    }
}