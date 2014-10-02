package at.ahammer.heroquest.subject.look;

import at.ahammer.boardgame.cdi.GameScoped;
import at.ahammer.boardgame.subject.look.Look;

import javax.inject.Inject;

/**
 * Created by andreas on 02.10.14.
 */
@GameScoped
public class LookStrategies {

    @Inject
    private LookAll lookAll;

    @Inject
    private LookDefault lookDefault;

    @Inject
    private LookHero lookHero;

    public Look getLokkAll() {
        return lookAll;
    }

    public Look getLookDefault() {
        return lookDefault;
    }

    public Look getLookHero() {
        return lookHero;
    }
}
