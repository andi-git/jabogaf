package at.ahammer.heroquest.behavior.look;

import at.ahammer.boardgame.cdi.GameScoped;
import at.ahammer.boardgame.behavior.look.LookBehavior;

import javax.inject.Inject;

/**
 * Created by andreas on 02.10.14.
 */
@GameScoped
public class LookStrategies {

    @Inject
    private LookBehaviorAll lookAll;

    @Inject
    private LookBehaviorDefault lookDefault;

    @Inject
    private LookBehaviorHero lookHero;

    public LookBehavior getLokkAll() {
        return lookAll;
    }

    public LookBehavior getLookDefault() {
        return lookDefault;
    }

    public LookBehavior getLookHero() {
        return lookHero;
    }
}
