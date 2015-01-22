package at.ahammer.boardgame.api.subject;

import at.ahammer.boardgame.api.behavior.look.Lookable;
import at.ahammer.boardgame.api.behavior.move.Moveable;
import at.ahammer.boardgame.api.cdi.GameContextBean;
import at.ahammer.boardgame.api.resource.ResourceHolder;
import at.ahammer.boardgame.api.subject.artifact.ArtifactHolder;

/**
 * A subject (i.e. hero, monster,...) in the game.
 */
public interface GameSubject extends GameContextBean, Moveable, Lookable, ResourceHolder, ArtifactHolder {

}