package at.ahammer.boardgame.api.artifact;

import at.ahammer.boardgame.api.cdi.GameContextBean;

/**
 * An {@link Artifact} cast an object in the board game which can be used by a {@link
 * at.ahammer.boardgame.api.subject.GameSubject}.
 */
public interface Artifact extends GameContextBean {

    HandCount getHandCount();
}
