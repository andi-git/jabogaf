package at.ahammer.heroquest.subject.move;

import at.ahammer.boardgame.cdi.GameScoped;
import at.ahammer.boardgame.subject.move.Move;

import javax.inject.Inject;

@GameScoped
public class MoveStrategies {

    @Inject
    private MoveAll moveAll;

    @Inject
    private MoveDefault moveDefault;

    @Inject
    private MoveHero moveHero;

    public Move getMoveAll() {
        return moveAll;
    }

    public Move getMoveDefault() {
        return moveDefault;
    }

    public Move getMoveHero() {
        return moveHero;
    }
}
