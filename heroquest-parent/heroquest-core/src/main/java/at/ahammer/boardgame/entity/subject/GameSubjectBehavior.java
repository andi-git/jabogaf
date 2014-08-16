package at.ahammer.boardgame.entity.subject;

import at.ahammer.boardgame.cdi.NewInstanceInGameContext;

/**
 * Created by andreas on 8/14/14.
 */
public class GameSubjectBehavior extends NewInstanceInGameContext {

    private final String name;

    public GameSubjectBehavior(String name) {
        this.name = name;
    }
}
