package at.ahammer.heroquest.behavior.move;

import at.ahammer.boardgame.cdi.GameScoped;
import at.ahammer.boardgame.behavior.move.MoveBehavior;

import javax.inject.Inject;

@GameScoped
public class MoveStrategies {

    @Inject
    private MoveBehaviorAll moveAll;

    @Inject
    private MoveBehaviorDefault moveDefault;

    @Inject
    private MoveBehaviorHero moveHero;

    public MoveBehavior getMoveAll() {
        return moveAll;
    }

    public MoveBehavior getMoveDefault() {
        return moveDefault;
    }

    public MoveBehavior getMoveHero() {
        return moveHero;
    }
}
